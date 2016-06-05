package com.hsm.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsm.bo.Assests;
import com.hsm.dao.ToolDAO;

@RestController
public class AssetController {

	@Autowired
	ToolDAO hsmLoginDAO;

	static final Logger logger = Logger.getLogger(AssetController.class);

	@RequestMapping(value = "/hsm/secure/assetSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Assests>  assetSearch(@RequestBody final Assests assests) {
		List<Assests> assestsLst=null;
		logger.debug("Entering the assetSearch method::"+assests.getAssetName());
		try{
			assestsLst =hsmLoginDAO.getAssetSearch(assests);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of assetSearch method::"+assests.getAssetName());
		return assestsLst;
	}

	@RequestMapping(value = "/hsm/secure/assetEdit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  assetEdit(@RequestBody final Assests assests) {
		int status=0;
		logger.debug("Entering the assetEdit method::"+assests.getAssetName());
		try{
			status =hsmLoginDAO.editAsset(assests);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of assetEdit method::"+assests.getAssetName());
		return String.valueOf(status);
	}

	@RequestMapping(value = "/hsm/secure/assetAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  assetAdd(@RequestBody final Assests assests) {
		int status=0;
		try{
			logger.debug("Entering the assetAdd method::"+assests.getAssetName());
			status =hsmLoginDAO.addAsset(assests);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of assetAdd method::"+assests.getAssetName());
		return String.valueOf(status);
	}

	@RequestMapping(value = "/hsm/secure/assetDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  assetDelete(@RequestBody final Assests assests) {
		int status=0;

		logger.debug("Entering the assetDelete method::"+assests.getAssetName());
		try{
			status =hsmLoginDAO.deleteAsset(assests);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("End of assetDelete method::"+assests.getAssetName());
		return String.valueOf(status);
	}

}
