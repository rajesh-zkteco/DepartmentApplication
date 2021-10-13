package com.zkteco.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {

	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	private int departmentMembers;
	private String departmentType;
}
