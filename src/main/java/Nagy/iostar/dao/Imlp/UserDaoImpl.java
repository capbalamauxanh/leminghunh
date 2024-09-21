package Nagy.iostar.dao.Imlp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import Nagy.iostar.configs.DBConnectMySQL;
import Nagy.iostar.dao.IUserDao;
import Nagy.iostar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * FROM user ";
		try {
			new DBConnectMySQL();
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				List<UserModel> list = new ArrayList<UserModel>();
				list.add(new UserModel(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("email"), 
						rs.getString("fullname"), 
						rs.getString("images"), 
						rs.getString("password"), 
						rs.getString("phone"),
						rs.getInt("roleid"), 
						rs.getDate("createDate")));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ? ";
		try {
			new DBConnectMySQL();
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setImages(rs.getString("images"));
				user.setPassword(rs.getString("password"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(username, email, fullname, images, password, phone, roleid, createDate ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getImages());
			ps.setString(6, user.getPassword());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getRoleid());
			ps.setDate(9, new Date(user.getCreateDate().getTime()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM user WHERE username = ? ";
		try {
			new DBConnectMySQL();
			conn = DBConnectMySQL.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setImages(rs.getString("images"));
				user.setPassword(rs.getString("password"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from [User] where username = ?";
		try {
		new DBConnectMySQL();
		Connection conn = DBConnectMySQL.getDatabaseConnection();
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;

	}

	public static void main1(String[] args) {
		try {
			IUserDao userDao = new UserDaoImpl();
			System.out.println(userDao.findByUserName("van"));
			//System.out.println(userDao.checkExistUsername("van"));

			UserModel user = new UserModel();
			user.setUsername("van");
			user.setPassword("1234555");

			//userDao.reset_password(user);

			//IUserDao userDao2 = new UserDaoImpl();
			//UserModel user2 = userDao2.findByUserName("van");
			//System.out.println("mk muon doi: " + user.getPassword() + " mk trong database: " + user2.getPassword());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
