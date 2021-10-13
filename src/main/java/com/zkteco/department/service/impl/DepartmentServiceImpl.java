package com.zkteco.department.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zkteco.department.count.CountError;
import com.zkteco.department.count.CountSuccess;
import com.zkteco.department.dto.DepartmentDto;
import com.zkteco.department.dto.ResultDto;
import com.zkteco.department.entity.Department;
import com.zkteco.department.exception.DepartmentNotFoundException;
import com.zkteco.department.repository.DepartmentRepository;
import com.zkteco.department.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	ResultDto result;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	DepartmentService departmentService;

	public DepartmentDto entityToDto(Department department) {
		DepartmentDto dto=new DepartmentDto();
		dto.setDepartmentCode(department.getDepartmentCode());
		dto.setDepartmentAddress(department.getDepartmentAddress());
		dto.setDepartmentMembers(department.getDepartmentMembers());
		dto.setDepartmentName(department.getDepartmentName());
		dto.setDepartmentType(department.getDepartmentType());


		return dto;
	}
	public Department dtoToEntity(DepartmentDto result) {
		Department dept=new Department();
		dept.setDepartmentAddress(result.getDepartmentAddress());
		dept.setDepartmentMembers(result.getDepartmentMembers());
		dept.setDepartmentCode(result.getDepartmentCode());
		dept.setDepartmentType(result.getDepartmentType());
		dept.setDepartmentName(result.getDepartmentName());

		return dept;
	}
	public List<DepartmentDto> entityToDto(List<Department> department){
		return department.stream().map(x->entityToDto(x)).collect(Collectors.toList());
	}



	//Save the department
	@Override
	public ResultDto saveDepartment(DepartmentDto dto) {
		ResultDto result=new ResultDto();
		if(dto.getDepartmentAddress()==null) {
			result.setCode("1111");
			result.setMessage("address required");

		}
		else {
			Department department = departmentService.dtoToEntity(dto);
			department=departmentRepository.save(department);
			//		DepartmentDto dto1=new DepartmentDto();

			result.setCode("1011");
			result.setMessage("successfull");
			result.setData(department);
		}
		return result; 

	}

	//Get one department by id
	@Override
	public ResultDto findDeptById(String deptid)throws DepartmentNotFoundException  {
		//Department dpt=departmentRepository.findById(deptid).orElse(null);
		Optional<Department> dept = departmentRepository.findById(deptid);
		if(!dept.isPresent()) {
			throw new DepartmentNotFoundException("Department Not Available");
		}
		Department dep=dept.get();
		DepartmentDto dto=departmentService.entityToDto(dep);
		ResultDto result=new ResultDto();
		result.setCode("1O07");
		result.setMessage("fetched");
		result.setData(dept);
		return result;

	}
	//update department by id
	@Override
	public ResultDto updateDepartment(String deptid, Department department)throws DepartmentNotFoundException {
		Optional<Department>department1 =departmentRepository.findById(deptid);
		if(!department1.isPresent()) {
			throw new DepartmentNotFoundException("Department not available");
		}
		Department depDb=department1.get();
		if(Objects.nonNull(department.getDepartmentName())&& 
				!"".equalsIgnoreCase(department.getDepartmentName())) {
			depDb.setDepartmentName(department.getDepartmentName());
		}

		if(Objects.nonNull(department.getDepartmentCode())&& 
				!"".equalsIgnoreCase(department.getDepartmentCode())) {
			depDb.setDepartmentCode(department.getDepartmentCode());
		}
		if(Objects.nonNull(department.getDepartmentAddress())&& 
				!"".equalsIgnoreCase(department.getDepartmentAddress())) {
			depDb.setDepartmentAddress(department.getDepartmentAddress());
		}
		departmentRepository.save(depDb);
		ResultDto result =new ResultDto ();
		result.setCode("1O07");
		result.setMessage("Document udated Successfully");
		result.setData(depDb);
		return result;
	}


	//Delete department by id
	@Override
	public ResultDto departmentDeleteById(String deptid)throws DepartmentNotFoundException {
		ResultDto dto = new ResultDto();
		CountError ecount=new CountError();
		CountSuccess ccount=new CountSuccess();
		String[] ids=deptid.split(",");
		//System.out.println(ids);
		List<CountError>ce=new ArrayList<CountError>();
		int successCount1=0;
		int errorCount1=0;
		ArrayList<String>str=new ArrayList<String>();
		List<ResultDto> rd=new ArrayList<ResultDto>();
		for(String idm:ids) {
			if(departmentRepository.existsById(idm)) {
				departmentRepository.deleteById(idm);
				str.add(idm);
				successCount1++;
				ccount.setSuccessCount(successCount1);
				ccount.setCount(ids);
				List<CountSuccess>cs=new ArrayList<CountSuccess>();
				cs.add(ccount);
				//			dto.setCode("dept0011");
				//			dto.setMessage("successfully deleted");
				//			dto.setData("SuccessCount:"+successCount1+  "ErrorCount"+errorCount);
			}
			else {
				errorCount1++;
				ecount.setErrorCount(errorCount1);
				ResultDto res=new ResultDto();
				res.setCode("dept0012");
				res.setMessage("id not exist ");
				res.setData(idm);
				rd.add(res);
				ecount.setError1(rd);
			}
		}
		List<Object> obj = new ArrayList<Object>();
		obj.add(ccount);
		obj.add(ecount);
		dto.setCode("dep001");
		dto.setMessage("One or more objects are not processed");
		dto.setData(obj);

		return dto;

	}
	@Override
	public ResultDto getAllDepartment(int page, int size) {
		Pageable page1 = PageRequest.of(page, size);
		Page<Department> page2 = departmentRepository.findAll(page1);
		List<Department> dept = new ArrayList<Department>();
		for(Department d:page2) {
			dept.add(d);
		}
		ResultDto res = new ResultDto();
		res.setCode("dept001");
		res.setMessage("succesfully fetched");
		res.setData(dept);
		return res;
	}
}
