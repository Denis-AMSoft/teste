package com.example.demo.util;

import com.example.demo.model.enums.StatusReserva;

public class ScheduleUtil {

	public static String getClass(StatusReserva statusReserva) {
		switch (statusReserva) {
		case RESERVADO:
			return "event-RESERVADO";
		case PEDENTE:
			return "event-PEDENTE";
		case CONCLUIDO:
			return "event-CONCLUIDO";
		case EXCUIDO:
			return "event-EXCUIDO";
		default:
			return "event-default";
		}
		
	}

}
