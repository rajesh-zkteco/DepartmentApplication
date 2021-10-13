package com.zkteco.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.zkteco.department.entity.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class RestRepositoryEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorMessage> EmployeeNotFoundException(DepartmentNotFoundException exception,
			WebRequest request) {

		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

	}
}
