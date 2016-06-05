package com.hsm.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asset")
public class Assests implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private String assetkey;


	@Column(nullable = false)
	private String empId;	

	@Column(nullable = false)
	private String assetName;

	@Column(nullable = true)
	private String description;

	@Column(nullable = true)
	private String function;

	@Column(nullable = false)
	private String assetLocation;

	@Column(nullable = false)
	private String serialNo;

	@Column(nullable = false)
	private String adminAccount;

	@Column(nullable = false)
	private String adminPassword;

	@Column(nullable = true)
	private String operatingSys;

	@Column(nullable = true)
	private String ipAddress1;
	@Column(nullable = true)
	private String ipAddress2;

	@Column(nullable = true)
	private String comments;


	public String getAssetkey() {
		return assetkey;
	}

	public void setAssetkey(String assetkey) {
		this.assetkey = assetkey;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getAssetLocation() {
		return assetLocation;
	}

	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getOperatingSys() {
		return operatingSys;
	}

	public void setOperatingSys(String operatingSys) {
		this.operatingSys = operatingSys;
	}

	public String getIpAddress1() {
		return ipAddress1;
	}

	public void setIpAddress1(String iPAddress1) {
		ipAddress1 = iPAddress1;
	}

	public String getIpAddress2() {
		return ipAddress2;
	}

	public void setIpAddress2(String iPAddress2) {
		ipAddress2 = iPAddress2;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}



}
