package com.zkteco.department.service;

import org.springframework.stereotype.Component;
import com.zkteco.department.dto.DepartmentDto;
import com.zkteco.department.dto.ResultDto;
import com.zkteco.department.entity.Department;
import com.zkteco.department.exception.DepartmentNotFoundException;

@Component
public interface DepartmentService {

	//save department entity
	public ResultDto saveDepartment(DepartmentDto department);
	//fetch by id
	public ResultDto findDeptById(String deptid)throws DepartmentNotFoundException;
	//delete by id
	public ResultDto departmentDeleteById(String deptid)throws DepartmentNotFoundException;
	//update department
	public ResultDto updateDepartment(String deptid, Department department)throws DepartmentNotFoundException;
	public DepartmentDto entityToDto(Department dpt);
	public Department dtoToEntity(DepartmentDto dto);
	public ResultDto getAllDepartment(int page, int size);
	
	//public List<DepartmentDto> entityToDto(List<Department> emp);

	//public List<Department> dtoToEntity(List<DepartmentDto> dto);

	
	
}
