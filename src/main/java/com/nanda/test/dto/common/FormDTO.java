package com.nanda.test.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class FormDTO<T> {

	private boolean success;
	private T data;
	private ErrorMessage errorMessage;
	private SuccessMessage successMessage;

	public FormDTO() {
		this.success = true;
	}

	public FormDTO(String successMessage) {
		this.success = true;
		this.successMessage = new SuccessMessage(successMessage);
	}

	public FormDTO(String successMessage, T data) {
		this.success = true;
		this.successMessage = new SuccessMessage(successMessage);
		this.data = data;
	}

	public FormDTO(String errorCode, String errorMessage, T data) {
		this.errorMessage = new ErrorMessage(errorCode, errorMessage);
		this.data = data;
	}

	@Data
	@JsonInclude(Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ErrorMessage {
		private String code;
		private String message;

		public ErrorMessage(String code, String message) {
			this.code = code;
			this.message = message;
		}
	}

	@Data
	@JsonInclude(Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SuccessMessage {
		private String message;

		public SuccessMessage(String message) {
			this.message = message;
		}
	}

}
