package com.hsm.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsm.bo.Information;
import com.hsm.dao.ToolDAO;

@RestController
public class InfoSystemController {

	@Autowired
	ToolDAO hsmLoginDAO;

	static final Logger logger = Logger.getLogger(InfoSystemController.class);

	@RequestMapping(value = "/hsm/secure/infoAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  infoAdd(@RequestBody final Information information) {
		int status=0;
		logger.debug("Entering infoAdd:"+information.getEmpId());
		try{
			 status =hsmLoginDAO.addInfo(information);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End Of infoAdd:"+information.getEmpId());
		return String.valueOf(status);
	}

	@RequestMapping(value = "/hsm/secure/infoSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Information>  infoSearch(@RequestBody final Information information) {
		List<Information> informationLst=null;
		logger.debug("Entering infoSearch:");
		try{
			informationLst =hsmLoginDAO.getInfoSearch(information);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of infoSearch:");
		return informationLst;
	}

	@RequestMapping(value = "/hsm/secure/infoEdit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  infoEdit(@RequestBody final Information information) {
		int status=0;
		logger.debug("Entering infoEdit:"+information.getEmpId());
		try{
			status =hsmLoginDAO.editInfo(information);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of infoEdit:"+information.getEmpId());
		return String.valueOf(status);
	}

	@RequestMapping(value = "/hsm/secure/infoDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  infoDelete(@RequestBody final Information information) {
		int status=0;
		logger.debug("Entering infoDelete:"+information.getAdminID());
		try{
			status =hsmLoginDAO.deleteInfo(information);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End Of infoDelete:"+information.getAdminID());
		return String.valueOf(status);
	}


}
