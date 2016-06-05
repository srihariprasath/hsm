package com.hsm.bo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "empId",nullable = false)
	private String empId;
	
	@Column(nullable = false)
	private String empFirstName;
	@Column(nullable = false)
	private String empLastName;
	@Column(nullable = true)
	private String tilte;
	@Column(nullable = true)
	private String location;
	@Column(nullable = true)
	private String supervisor;
	@Column(nullable = true)
	private String phiAccess;
	@Column(nullable = false)
	private String status;
	@Column(nullable = true)
	private String system;
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = true)
	private Date createDt;
	
	@Column(nullable = true)
	private Date updatedDt;
	
	@Column(nullable = true)
	private String updatedId;	
	
	
	
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public Date getUpdatedDt() {
		return updatedDt;
	}
	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	public String getUpdatedId() {
		return updatedId;
	}
	public void setUpdatedId(String updatedId) {
		this.updatedId = updatedId;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getPhiAccess() {
		return phiAccess;
	}
	public void setPhiAccess(String phiAccess) {
		this.phiAccess = phiAccess;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
