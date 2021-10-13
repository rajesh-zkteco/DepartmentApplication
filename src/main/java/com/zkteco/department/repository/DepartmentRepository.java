package com.zkteco.department.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zkteco.department.entity.Department;
import com.zkteco.department.exception.DepartmentNotFoundException;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

	public Department findByDepartmentName(String departmentName);
	
@Query(value="select t from Department t where t.departmentName like ?1 OR t.departmentAddress like ?1 OR t.departmentCode like ?1 OR t.departmentType like ?1 OR t.departmentMembers like ?1")
	public Page<Department> departmentContaining(String search, Pageable paging)throws DepartmentNotFoundException;

}
