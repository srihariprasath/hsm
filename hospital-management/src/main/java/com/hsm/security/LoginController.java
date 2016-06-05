package com.hsm.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsm.bo.UserLoginBO;
import com.hsm.bo.Users;
import com.hsm.dao.ToolDAO;

@RestController
public class LoginController {

	@Autowired
	ToolDAO hsmLoginDAO;

	@Autowired
	TokenAuthorization tokenAuthorization;

	private static String ISSUER="HSMMANAGEMENT";

	static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/hsm/login", method =RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserLoginBO  loginTokenGenerator(@RequestBody final Users users) {
		logger.debug("Entering Login Token generator:");
		UserLoginBO userLoginBO=null;
		try{
			userLoginBO= hsmLoginDAO.loginData(users.getUserName());
			if(userLoginBO!=null){
				if(null!=users.getPassword() && users.getPassword().equals(userLoginBO.getPassword())){
					String jwtToken= tokenAuthorization.createJWT(userLoginBO.getUser_name(), ISSUER,
							users.getUserName(),3000000);
					userLoginBO.setJwtToken(jwtToken);
					logger.debug("LoginTokenGenerator::::"+ userLoginBO.getLoginFailure());
					userLoginBO.setLoginMessage("Welcome to HSM "+userLoginBO.getFirst_name()+","+userLoginBO.getLast_name());
					userLoginBO.setLoginFailure("N");
					if(userLoginBO.getRole().equalsIgnoreCase("ADMIN")){
						userLoginBO.setAdmin(true);
					}else{
						userLoginBO.setAdmin(false);
					}
				}else{
					userLoginBO.setLoginMessage("Password Doesn't Match Our Records");
					userLoginBO.setLoginFailure("Y");
				}
			}else{
				logger.debug("LoginTokenGenerator::::"+ "UserName Doesn't Match Our Records");
				userLoginBO.setLoginMessage("UserName Doesn't Match Our Records");
				userLoginBO=new UserLoginBO();
				userLoginBO.setLoginFailure("N");

			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		logger.debug("Exiting Login Token generator:"+ userLoginBO.getLoginFailure());
		return userLoginBO;
	}	
}
