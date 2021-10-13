package com.zkteco.department.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", length = 50, nullable = false)
	private String departmentId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate = new Date();
	
	@NotNull(message="name must be required")
	private String departmentName;
	
	@NotNull(message="address must be required")
	private String departmentAddress;
	
	private String departmentCode;
	private int departmentMembers;
	private String departmentType;
	
	
}
