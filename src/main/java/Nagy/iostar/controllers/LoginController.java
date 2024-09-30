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

@WebServlet(urlPatterns = { "/login"})
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// kiểm tra có tồn tại sesion không
		HttpSession session = req.getSession(false);
		// req.getSession(false) sẽ trả về null nếu không có session nào tồn tại
		/* nếu có session tồn tại và có user trong sesion thì chuyển hướng sang trang
		 * waiting Controller */
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}
		// check Cookie
		// trả về một mảng các đối tượng Cookies
		Cookie[] cookie = req.getCookies();
		// nếu mảng Cookies không rỗng thì tìm đối tượng trong mảng cookies
		if (cookie != null) {
			for (Cookie c : cookie) {
				if (c.getName().equals("username")) {
					// tạo một phiên mới
					session = req.getSession(true);
					session.setAttribute("username", c.getValue());
					resp.sendRedirect(req.getContextPath() + "/waiting");
					return;
				}
			}
		}
		req.getRequestDispatcher("view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// mã hóa dữ liệu tiếng Việt
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		// lấy dữ liệu của tham số từ view
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String alertMsg = "";
		boolean isRememberMe = false;
		String remember = req.getParameter("remember");
		
		//Kiểm tra Remember
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		// nếu username hoặc password rỗng thì thông báo
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			return;
		}

		// gọi Service để lấy toàn bộ các hàm trong Service
		IUserService service = new UserService();
		// gọi hàm user login
		UserModel user = service.login(username, password);
		if (user != null) {
			// tạo session để lưu thông tin login (lưu trên server)
			HttpSession session = req.getSession();
			session.setAttribute("account", user);
			// thiết lập thời gian tồn tại của session
			session.setMaxInactiveInterval(30);

			if (isRememberMe) {
				saveRemeberMe(resp, username);
			}
			
			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}
		
	}

	private void saveRemeberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(60*2);
		response.addCookie(cookie);
	}

}