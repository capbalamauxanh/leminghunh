package Nagy.iostar.services;

import Nagy.iostar.models.UserModel;

public interface IUserService {
	
	UserModel login (String username, String password);
	
	
	UserModel FindByUserName(String username);
	
	
	
	
	
	

}
