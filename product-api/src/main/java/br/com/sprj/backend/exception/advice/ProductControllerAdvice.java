package br.com.sprj.backend.exception.advice;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.sprj.backend.dto.ErrorDTO;
import br.com.sprj.backend.exception.CategoryNotFoundException;
import br.com.sprj.backend.exception.ProductNotFoundException;

@ControllerAdvice(basePackages = "br.com.sprj.backend.controller")
public class ProductControllerAdvice {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public ErrorDTO handleProductNotFound(ProductNotFoundException productNotFoundException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Product not found.");
		errorDTO.setTimeStamp(new Date());
		return errorDTO;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CategoryNotFoundException.class)
	public ErrorDTO handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Category not found.");
		errorDTO.setTimeStamp(new Date());
		return errorDTO;
	}

	public ErrorDTO processValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
		BindingResult result = methodArgumentNotValidException.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuilder stringBuilder = new StringBuilder("Invalid valur for field(s): ");

		for (FieldError fieldError : fieldErrors) {
			stringBuilder.append(" ");
			stringBuilder.append(fieldError.getField());
		}
		errorDTO.setMessage(stringBuilder.toString());
		errorDTO.setTimeStamp(new Date());
		return errorDTO;
	}

}
