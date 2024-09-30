package Nagy.iostar.services;

import Nagy.iostar.models.UserModel;

public interface IUserService {
	
	UserModel login (String username, String password);
	
	
	UserModel FindByUserName(String username);
	
	void insertUser(UserModel user);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistPhone(String phone);
	
	void updatePassword(String username, String newPassword);
	
	
	
	
	

}
