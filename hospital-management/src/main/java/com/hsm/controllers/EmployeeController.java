package com.hsm.controllers;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsm.bo.EmployeeBean;
import com.hsm.dao.ToolDAO;
import com.hsm.security.JwtAuthFilter;

@RestController
public class EmployeeController {

	@Autowired
	ToolDAO hsmLoginDAO;

	static final Logger logger = Logger.getLogger(EmployeeController.class);

	@RequestMapping(value = "hsm/secure/employeesearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeBean>  employeeSearch(@RequestBody final EmployeeBean employeeBean) {

		logger.debug("Entering the employeeSearch method::"+employeeBean.getEmpId());
		List<EmployeeBean> employeeBeanValue=null;
		try{
			employeeBeanValue =hsmLoginDAO.getEmpSearch(employeeBean.getEmpId(),
					employeeBean.getEmpFirstName(),employeeBean.getEmpLastName());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End the employeeSearch method::"+employeeBean.getEmpId());
		return employeeBeanValue;
	}

	@RequestMapping(value = "/hsm/secure/employeeAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  employeeAdd(@RequestBody final EmployeeBean employeeBean) {
		int status=0;
		logger.debug("Entering the employeeAdd method::"+employeeBean.getEmpId());
		try{
			status =hsmLoginDAO.addEmployee(employeeBean);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of employeeAdd method::"+employeeBean.getEmpId());
		return String.valueOf(status);
	}

	@RequestMapping(value = "/hsm/secure/employeeEdit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  employeeEdit(@RequestBody final EmployeeBean employeeBean) {
		int status=0;
		logger.debug("Entering the employeeEdit method::"+employeeBean.getEmpId());
		try{
			status =hsmLoginDAO.editEmployee(employeeBean);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of  employeeAdd method::"+employeeBean.getEmpId());
		return String.valueOf(status);
	}


	@RequestMapping(value = "/hsm/secure/employeeDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  employeeDelete(@RequestBody final EmployeeBean employeeBean) {
		int status=0;
		logger.debug("Entering the employeeDelete method::"+employeeBean.getEmpId());
		try{
			status =hsmLoginDAO.deleteEmployee(employeeBean);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of  employeeDelete method::"+employeeBean.getEmpId());
		return String.valueOf(status);
	}

}
