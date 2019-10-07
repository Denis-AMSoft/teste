package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan({ "com.example.demo.*" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
		jsfLoginEntry.setLoginFormUrl("/Login.xhtml");
		jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());

		JsfAccessDeniedHandler jsfDeniedEntry = new JsfAccessDeniedHandler();
		jsfDeniedEntry.setLoginPath("/AcessoNegado.xhtml");
		jsfDeniedEntry.setContextRelative(true);

		http.csrf().disable().headers().frameOptions().sameOrigin().and()

				.authorizeRequests()
				.antMatchers("/Login.xhtml", "/resources/**","/cadastroUsuario.xhtml",  "/redefinirSenha.xhtml","/AcessoNegado.xhtml",  "/Erro.xhtml","/javax.faces.resource/**").permitAll()
				.antMatchers("/index.xhtml","/cadastroEvento.xhtml" ,"/dialogos/**").authenticated()
				.antMatchers("/listaUsuarios.xhtml").hasAnyRole("SINDICO")
				.antMatchers("/listaUnidadeMoradia.xhtml").hasAnyRole("SINDICO")
				.antMatchers("/listaEventos.xhtml").hasAnyRole("SINDICO")
				.and()
				.formLogin().loginPage("/Login.xhtml")
				.failureUrl("/Login.xhtml?invalid=true")
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()
				.exceptionHandling().accessDeniedPage("/AcessoNegado.xhtml").accessDeniedHandler(jsfDeniedEntry);
	}

}