package com.hsm.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "information")
public class Information implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String empId;
	@Column(nullable = false)
	private String sysName;
	@Column(nullable = true)
	private String backupAdministrator;
	@Column(nullable = true)
	private String purposeDet;
	@Column(nullable = false)
	private String administrator;

	@Id
	private String adminID;
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empID) {
		this.empId = empID;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getBackupAdministrator() {
		return backupAdministrator;
	}
	public void setBackupAdministrator(String backupAdministrator) {
		this.backupAdministrator = backupAdministrator;
	}
	public String getPurposeDet() {
		return purposeDet;
	}
	public void setPurposeDet(String purposeDet) {
		this.purposeDet = purposeDet;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	


}
