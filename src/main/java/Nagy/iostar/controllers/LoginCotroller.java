package Nagy.iostar.controllers;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Nagy.iostar.models.UserModel;
import Nagy.iostar.services.IUserService;
import Nagy.iostar.services.impl.UserService;
import Nagy.iostar.utils.Constant;

@WebServlet(urlPatterns = { "/login" })
public class LoginCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// lấy tất cả các hàm của service
	IUserService service = new UserService();

	@Override // lấy form login
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
		resp.sendRedirect(req.getContextPath()+ "/waiting");
		return;
		}
		
		 // Kiểm tra cookie để tự động điền tên người dùng
        String username = getCookieValue(req, Constant.COOKIE_REMEMBER);
        if (username != null) {
            req.setAttribute("uname", username);
        }
		req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
	}

	// post dữ liệu lên
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// mã hóa nội dung bằng tiếng Việt
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// lấy tham số
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		boolean isRememberMe = false;
		// kiểm tra checkbox
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		// kiểm tra đăng nhập thành công hay thất bại
		String alertMsg = "";
		// mật khẩu và tài khoản không được phép rỗng
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			return;
		}
		// kiểm tra đăng nhập
		UserModel user = service.login(username, password);
		if (user != null) {
			// tạo session lưu thông tin đăng nhập trên server lưu trên trình duyệt mới là
			// cockie
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if (isRememberMe) {
				saveRemeberMe(resp, username);
			}
			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}
	}

	private void saveRemeberMe(HttpServletResponse resp, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 24 * 60 * 60); // cookie tồn tại 30 ngày
		cookie.setPath("/"); // Đặt đường dẫn cho cookie
		resp.addCookie(cookie);
	}
	 private String getCookieValue(HttpServletRequest req, String cookieName) {
	        Cookie[] cookies = req.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (cookie.getName().equals(cookieName)) {
	                    return cookie.getValue();
	                }
	            }
	        }
	        return null;
	    }
}
