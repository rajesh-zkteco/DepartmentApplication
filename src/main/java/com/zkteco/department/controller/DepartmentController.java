package com.zkteco.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.department.dto.DepartmentDto;
import com.zkteco.department.dto.ResultDto;
import com.zkteco.department.entity.Department;
import com.zkteco.department.exception.DepartmentNotFoundException;
import com.zkteco.department.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/v1/department")
@Api(value="This is Department Project",description = "show department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

//	@GetMapping("/page/{pageNo}")
//	public ResponseEntity<Map<String, Object>> findPaginated(@RequestParam(required = false) String search,
//	@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
//	@RequestParam(defaultValue = "departmentId,asc") String[] sort)throws DepartmentNotFoundException {
//	ResponseEntity<Map<String, Object>> res = departmentService.getAllDepartment(search, page, size, sort);
//	return res;
//	}
	
	@GetMapping("/page")
	@ApiOperation(value="to Fetch detail pagination")
	public ResultDto findPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)throws DepartmentNotFoundException {
	ResultDto res = departmentService.getAllDepartment( page, size);
	return res;
	}
	//get department by id
	@GetMapping("/{id}")
	@ApiOperation("Fetch department by id")
	public ResultDto findDeptById(@PathVariable("id")String deptid)throws DepartmentNotFoundException {
		return departmentService.findDeptById(deptid);	 
	}
		
	//create department
	@PostMapping()
	public ResultDto saveDepartment(@RequestBody DepartmentDto department) {
		return departmentService.saveDepartment(department);
		}
	
	//update department by id
		@PutMapping("/{id}")
		@ApiOperation("Update department by id")
		public ResultDto updateDepartment(@PathVariable("id") String deptid,@RequestBody Department department)throws DepartmentNotFoundException {
			return departmentService.updateDepartment(deptid,department);
		}

	//delete by id
	@DeleteMapping
	@ApiOperation("Delete department by id")
	public ResultDto departmentDeleteById(@RequestParam("id")String id)throws DepartmentNotFoundException {
		return departmentService.departmentDeleteById(id);
	}
	
}
