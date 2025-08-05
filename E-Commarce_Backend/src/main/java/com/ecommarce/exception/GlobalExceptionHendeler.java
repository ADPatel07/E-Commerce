package com.ecommarce.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommarce.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHendeler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception exception) {

		ErrorResponseDTO errorResponseDto = ErrorResponseDTO.builder()
															.code(500)
															.status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
															.message(exception.getMessage())
															.timestamp(new Date())
															.build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
	}	

	
}
