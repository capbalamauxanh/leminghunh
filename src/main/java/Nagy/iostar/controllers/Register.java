package Nagy.iostar.controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import Nagy.iostar.models.UserModel;
import Nagy.iostar.services.impl.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/register"})
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
		resp.sendRedirect(req.getContextPath() + "/admin");
		return;
		}
		req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		 String username = req.getParameter("username");
	        String email = req.getParameter("email");
	        String password = req.getParameter("password");
	        String fullname = req.getParameter("fullname");
	        String images = req.getParameter("images"); // có thể để trống
	        String phone = req.getParameter("phone");
	        int roleid = Integer.parseInt(req.getParameter("roleId")); // Nhận giá trị từ dropdown
	        Date createDate = new Date(0);
	        
	       // String hashedPassword = hashPassword(password);

	        UserModel user = new UserModel(0, username, email, fullname , password , images, phone, roleid, createDate);
	        
	        boolean isSuccess = UserService.register(user);

	        if (isSuccess) {
	            resp.sendRedirect(req.getContextPath() + "/login"); 
	        } else {
	            req.setAttribute("error", "Đăng ký không thành công!"); 
	            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
	        }
	        
	        
	}


	private String hashPassword(String password) {
		 try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashedBytes = md.digest(password.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for (byte b : hashedBytes) {
	                sb.append(String.format("%02x", b));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
		
		
		
		return null;
	        }
	}
}
