package com.hsm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Component;

import com.hsm.bo.Assests;
import com.hsm.bo.EmployeeBean;
import com.hsm.bo.Information;
import com.hsm.bo.UserLoginBO;
import com.hsm.security.LoginController;

@Component
@Configuration
public class ToolDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	static final Logger logger = Logger.getLogger(ToolDAO.class);

	public UserLoginBO loginData(String userName) throws Exception{

		UserLoginBO userLoginBO = getUsers(userName);
		return userLoginBO;

	}

	public List<EmployeeBean> getEmpSearch(String empId, String empFirstName, String empLastName) throws Exception {
		
		logger.debug("Entering getEmpSearch::"+empId+":"+empFirstName+":"+empLastName+":");

		String selectQuery = "Select * from employee where ";
		String firstName = "empFirstName like '%";
		String lastName = "empLastName like '%";
		String empidQuery = "empid = '";
		int andFlag = 0;
		if (null!=empId && empId.length() > 0) {
			selectQuery = selectQuery + empidQuery + empId + "'";
			andFlag++;
		}
		if (empFirstName != null && empFirstName.trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + firstName + empFirstName + "%'";
			} else {
				selectQuery = selectQuery + firstName + empFirstName + "%'";
			}
			andFlag++;
		}
		if (empLastName != null && empLastName.trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + lastName + empLastName + "%'";
			} else {
				selectQuery = selectQuery + lastName + empLastName + "%'";
			}
			andFlag++;
		}

		List<EmployeeBean> employeeBeanLst = new ArrayList<EmployeeBean>();
		if (andFlag > 0) {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectQuery);
			for (Map row : rows) {
				EmployeeBean employeeBean = new EmployeeBean();
				employeeBean.setEmpId((String) row.get("empId"));
				employeeBean.setEmpFirstName((String) row.get("empFirstName"));
				employeeBean.setEmpLastName((String) row.get("empLastName"));
				employeeBean.setTilte((String) row.get("tilte"));
				employeeBean.setLocation((String) row.get("location"));
				employeeBean.setSupervisor((String) row.get("supervisor"));
				employeeBean.setPhiAccess((String) row.get("phiAccess"));
				employeeBean.setStatus((String) row.get("status"));
				employeeBean.setUserName((String) row.get("userName"));
				employeeBean.setSystem((String) row.get("system"));

				employeeBeanLst.add(employeeBean);
			}
		}

		logger.debug("End of getEmpSearch::"+empId+":"+empFirstName+":"+empLastName+":");
		return employeeBeanLst;

	}

	public UserLoginBO getUsers(String userName) throws Exception {
		
		logger.debug("Entering getUsers::"+userName);

		String query = "select * from users  where user_name='" + userName + "'";
		return jdbcTemplate.queryForObject(query, (resultSet, i) -> {

			return new UserLoginBO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
		});
	}

	public int addEmployee(EmployeeBean employeeBean) throws Exception {
		logger.debug("Entering addEmployee Method::");
		String queryCount = "select empId from employee where empId='";
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); // not

		String empID=String.valueOf(Math.round(Math.random()*100000000));

		boolean duplicateId=true;
		do{
			String newQueryCount=queryCount+empID+"'";
			jdbcTemplate.query(newQueryCount, countCallback);
			int rowCount = countCallback.getRowCount();
			if(rowCount==0){
				duplicateId=false;
			}else{
				empID=String.valueOf((Math.random()*100000 + 3333300000L));
			}
		}while(duplicateId);
		employeeBean.setEmpId(empID);
		// employeeBean.setEmpId("HSM"+(rowCount++));
		String query = "INSERT INTO hsmDatabase.employee(empId,empFirstName,empLastName,tilte,location,supervisor,phiAccess,status) VALUES (?,?,?,?,?,?,?,?)";
		int status = jdbcTemplate.update(query, employeeBean.getEmpId(), employeeBean.getEmpFirstName(),
				employeeBean.getEmpLastName(), employeeBean.getTilte(), employeeBean.getLocation(),
				employeeBean.getSupervisor(), employeeBean.getPhiAccess(), employeeBean.getStatus());
		logger.debug("End Of addEmployee Method::"+status);
		return status;
	}

	public int editEmployee(EmployeeBean employeeBean) throws Exception{
		logger.debug("Entering editEmployee Method::");
		String query = "update `hsmDatabase`.`employee` set empFirstName=?, empLastName=?,tilte=?,location=?,supervisor=?,phiAccess=?,status=?"
				+ "where empId=?";
		int status = jdbcTemplate.update(query, employeeBean.getEmpFirstName(), employeeBean.getEmpLastName(),
				employeeBean.getTilte(), employeeBean.getLocation(), employeeBean.getSupervisor(),
				employeeBean.getPhiAccess(), employeeBean.getStatus(), employeeBean.getEmpId());
		
		logger.debug("End of editEmployee Method::");
		return status;
	}

	public int deleteEmployee(EmployeeBean employeeBean) throws Exception{
		logger.debug("Entering deleteEmployee Method::");
		String query = "delete from hsmDatabase.employee where empId=?";
		int status = jdbcTemplate.update(query, employeeBean.getEmpId());
		logger.debug("End Of deleteEmployee Method::"+status);
		return status;
	}

	

	/**
	 * Get results based on EmpID,SerialNumber and ComputerName
	 * 
	 * @param assests
	 * @return
	 */	
	public int addInfo(Information information) throws Exception {
		logger.debug("Entering addInfo Method::");
		String queryCount = "select adminID from sysadmin where adminID='";
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); // not

		String adminID=String.valueOf(Math.round(Math.random()*100000000));

		boolean duplicateId=true;
		do{
			String newQueryCount=queryCount+adminID+"'";
			jdbcTemplate.query(newQueryCount, countCallback);
			int rowCount = countCallback.getRowCount();
			logger.debug("Entering addInfo Method::"+adminID);
			if(rowCount==0){
				duplicateId=false;
			}else{
				adminID=String.valueOf((Math.random()*100000 + 3333300000L));
			}
		}while(duplicateId);
		information.setAdminID(adminID);
		// employeeBean.setEmpId("HSM"+(rowCount++));
		String query = "INSERT INTO hsmDatabase.sysadmin(adminID,empId,sysName,purposeDet,administrator,bkpAdministrator) VALUES (?,?,?,?,?,?)";
		int status = jdbcTemplate.update(query, information.getAdminID(), information.getEmpId(),
				information.getSysName(), information.getPurposeDet(), information.getAdministrator(),
				information.getBackupAdministrator());
		logger.debug("End of addInfo Method::"+status);
		return status;
	}
	
	
	public List<Information> getInfoSearch(Information informationSys) throws Exception {
		
		logger.debug("Entering getInfoSearch Method::");
		String selectQuery = "Select * from sysadmin where ";

		String sysName = "sysName like '%";
		String administrator = "administrator like '%";
		String empId = "empid = '";
		String bkpAdministrator = "bkpAdministrator = '";
		int andFlag = 0;
		if (null!=informationSys.getEmpId() && informationSys.getEmpId().length() > 0) {
			selectQuery = selectQuery + empId + informationSys.getEmpId() + "'";
			andFlag++;
		}
		if (informationSys.getSysName() != null && informationSys.getSysName().trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + sysName + informationSys.getSysName() + "%'";
			} else {
				selectQuery = selectQuery + sysName + informationSys.getSysName() + "%'";
			}
			andFlag++;
		}
		if (informationSys.getAdministrator() != null && informationSys.getAdministrator().trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + administrator + informationSys.getAdministrator() + "%'";
			} else {
				selectQuery = selectQuery + administrator + informationSys.getAdministrator() + "%'";
			}
			andFlag++;
		}
		
		if (informationSys.getBackupAdministrator() != null && informationSys.getBackupAdministrator().trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + bkpAdministrator + informationSys.getBackupAdministrator() + "%'";
			} else {
				selectQuery = selectQuery + bkpAdministrator + informationSys.getBackupAdministrator() + "%'";
			}
			andFlag++;
		}
		List<Information> informationBeanLst = new ArrayList<Information>();
		if (andFlag > 0) {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectQuery);
			for (Map row : rows) {
				Information information = new Information();
				
				information.setAdminID((String) row.get("adminID"));
				information.setEmpId((String) row.get("empID"));
				information.setSysName((String) row.get("sysName"));
				information.setPurposeDet((String) row.get("purposeDet"));
				information.setAdministrator((String) row.get("administrator"));
				information.setBackupAdministrator((String) row.get("bkpAdministrator"));
				informationBeanLst.add(information);
			}
		}

		logger.debug("End of getInfoSearch Method::");
		return informationBeanLst;

	}
	
	public int editInfo(Information information) throws Exception {
		logger.debug("Entering editInfo Method::");
		String query = "update `hsmDatabase`.`sysadmin` set sysName=?, purposeDet=?,administrator=?,bkpAdministrator=?"
				+ "where adminID=?";
		int status = jdbcTemplate.update(query,information.getSysName(),information.getPurposeDet(),information.getAdministrator()
				,information.getBackupAdministrator(),information.getAdminID());
		logger.debug("End of editInfo Method::");
		return status;
	}
	
	public int deleteInfo(Information information) {
		logger.debug("Entering deleteInfo Method::");
		String query = "delete from hsmDatabase.sysadmin where adminID=?";
		int status = jdbcTemplate.update(query, information.getAdminID());
		logger.debug("End of deleteInfo Method::"+status);
		return status;
	}
	public int addAsset(Assests assests) throws Exception{
		logger.debug("Entering addAsset Method::");
		String queryCount = "select assetkey from asset where assetkey='";
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); // not

		String assetkey=String.valueOf(Math.round(Math.random()*100000000));

		boolean duplicateId=true;
		do{
			String newQueryCount=queryCount+assetkey+"'";
			jdbcTemplate.query(newQueryCount, countCallback);
			int rowCount = countCallback.getRowCount();
			if(rowCount==0){
				duplicateId=false;
			}else{
				assetkey=String.valueOf((Math.random()*100000 + 3333300000L));
			}
		}while(duplicateId);
		assests.setAssetkey(assetkey);
		// employeeBean.setEmpId("HSM"+(rowCount++));
		String query = "INSERT INTO hsmDatabase.asset(assetkey,empID,function,assetName,assetLocation,"
				+ "serialNo,adminAccount,adminPassword,operatingSys,IPAddress1,IPAddress2,description,comments) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int status = jdbcTemplate.update(query, assests.getAssetkey(),assests.getEmpId(),assests.getFunction(),
				assests.getAssetName(),assests.getAssetLocation(),assests.getSerialNo(),assests.getAdminAccount(),
				assests.getAdminPassword(),assests.getOperatingSys(),assests.getIpAddress1(),assests.getIpAddress2(),
				assests.getDescription(),assests.getComments());
		logger.debug("End of addAsset Method::");
		return status;
	}
	public List<Assests> getAssetSearch(Assests assests) throws Exception{
		logger.debug("Entering getAssetSearch Method::");
		String selectQuery = "Select * from asset where ";

		String assetName = "assetName like '%";
		String operatingSys = "operatingSys like '%";
		String empId = "empID = '";
		String serialNo = "serialNo = '";
		int andFlag = 0;
		if (null!=assests.getEmpId() && assests.getEmpId().length() > 0) {
			selectQuery = selectQuery + empId + assests.getEmpId() + "'";
			andFlag++;
		}
		if (assests.getAssetName() != null && assests.getAssetName().trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + assetName + assests.getAssetName() + "%'";
			} else {
				selectQuery = selectQuery + assetName + assests.getAssetName() + "%'";
			}
			andFlag++;
		}
		if (assests.getOperatingSys() != null && assests.getOperatingSys().trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + operatingSys + assests.getOperatingSys() + "%'";
			} else {
				selectQuery = selectQuery + operatingSys + assests.getOperatingSys() + "%'";
			}
			andFlag++;
		}
		
		if (assests.getSerialNo()!= null && assests.getSerialNo().trim().length() > 0) {
			if (andFlag > 0) {
				selectQuery = selectQuery + " and " + serialNo + assests.getSerialNo() + "%'";
			} else {
				selectQuery = selectQuery + serialNo + assests.getSerialNo() + "%'";
			}
			andFlag++;
		}
		List<Assests> assestsBeanLst = new ArrayList<Assests>();
		if (andFlag > 0) {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectQuery);
			for (Map row : rows) {
				Assests assestsBean = new Assests();				
				assestsBean.setAssetkey((String) row.get("assetkey"));
				assestsBean.setEmpId((String) row.get("empID"));
				assestsBean.setFunction((String) row.get("function"));
				assestsBean.setAssetName((String) row.get("assetName"));
				assestsBean.setAssetLocation((String) row.get("assetLocation"));
				assestsBean.setSerialNo((String) row.get("serialNo"));
				assestsBean.setAdminAccount((String) row.get("adminAccount"));
				assestsBean.setAdminPassword((String) row.get("adminPassword"));
				assestsBean.setOperatingSys((String) row.get("operatingSys"));
				assestsBean.setIpAddress1((String) row.get("IPAddress1"));
				assestsBean.setIpAddress2((String) row.get("IPAddress2"));
				assestsBean.setDescription((String) row.get("description"));
				assestsBean.setComments((String) row.get("comments"));
				assestsBeanLst.add(assestsBean);
			}
		}

		
		logger.debug("End of getAssetSearch Method::");
		return assestsBeanLst;

	}
	
	public int editAsset(Assests assests) throws Exception {
		logger.debug("Entering editAsset Method::");
		String query = "update hsmDatabase.asset set function=?, assetName=?,assetLocation=?,serialNo=?,"
				+ "adminAccount=?,adminPassword=?,operatingSys=?,IPAddress1=?,IPAddress2=?,description=?,comments=?"
				+ "where assetkey=?";
		int status = jdbcTemplate.update(query,assests.getFunction(),assests.getAssetName(),assests.getAssetLocation(),
				assests.getSerialNo(),assests.getAdminAccount(),assests.getAdminPassword(),assests.getOperatingSys(),assests.getIpAddress1(),assests.getIpAddress2(),
				assests.getDescription(),assests.getComments(),assests.getAssetkey());
		logger.debug("End of editAsset Method::"+status);
		return status;
	}
	
	public int deleteAsset(Assests assests)throws Exception {
		logger.debug("Entering deleteAsset Method::");
		String query = "delete from hsmDatabase.asset where assetkey=?";
		int status = jdbcTemplate.update(query, assests.getAssetkey());
		logger.debug("End of deleteAsset Method::");
		return status;
	}
}
