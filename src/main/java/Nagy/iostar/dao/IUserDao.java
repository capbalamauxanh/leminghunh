package Nagy.iostar.dao;

import java.util.List;

import Nagy.iostar.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	UserModel findByUserName(String username);

	boolean checkExistUsername(String username);
	
}
