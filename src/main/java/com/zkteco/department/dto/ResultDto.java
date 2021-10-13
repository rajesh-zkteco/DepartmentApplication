package com.zkteco.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {

	private String code;
	private String message;
	private Object data;
}
