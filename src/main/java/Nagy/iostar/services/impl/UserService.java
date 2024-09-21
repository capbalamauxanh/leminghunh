package Nagy.iostar.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Nagy.iostar.configs.DBConnectMySQL;
import Nagy.iostar.dao.IUserDao;
import Nagy.iostar.dao.Imlp.UserDaoImpl;
import Nagy.iostar.models.UserModel;
import Nagy.iostar.services.IUserService;

public class UserService implements IUserService {
	IUserDao userDao = new UserDaoImpl();
	
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
		return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		
		
		return userDao.findByUserName(username);
	}

	public static boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        try (Connection connection = DBConnectMySQL.getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            return statement.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public static boolean register(UserModel user) {
		 String sql = "INSERT INTO user (username, email, fullname , password , images, phone, roleid, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try (Connection connection = DBConnectMySQL.getDatabaseConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, user.getUsername());
	            statement.setString(2, user.getEmail());
	            statement.setString(3, user.getPassword());
	            statement.setString(4, user.getFullname());
	            statement.setString(5, user.getImages());
	            statement.setString(6, user.getPhone());
	            statement.setInt(7, user.getRoleid());
	            statement.setDate(8, new java.sql.Date(user.getCreateDate().getTime()));

	            return statement.executeUpdate() > 0; 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }

	}
	
}
	
	
	
	

