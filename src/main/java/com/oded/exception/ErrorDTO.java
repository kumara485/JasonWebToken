package com.oded.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDTO {
	
	private int status;
	public ErrorDTO(int status, String messageString) {
		super();
		this.status = status;
		this.messageString = messageString;
	}
	private String messageString;

}
