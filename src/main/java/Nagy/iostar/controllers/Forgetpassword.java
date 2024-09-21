package Nagy.iostar.controllers;

import java.io.IOException;

import Nagy.iostar.services.impl.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/forgot-password"})
public class Forgetpassword extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    String username = req.getParameter("username");
	        String newPassword = req.getParameter("newPassword");
	        
	        if (username == null || username.isEmpty() || newPassword == null || newPassword.isEmpty()) {
	            req.setAttribute("error", "Tên đăng nhập và mật khẩu mới không được để trống!");
	            req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
	            return;
	         }
			boolean isUpdated = false;
			try {
				isUpdated = UserService.updatePassword(username, newPassword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (isUpdated) {
	            req.setAttribute("message", "Mật khẩu đã được cập nhật thành công!");
	            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	
	
	}else {
        req.setAttribute("error", "Không tìm thấy tài khoản hoặc cập nhật không thành công!");
        req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
   	}	        
  }
}