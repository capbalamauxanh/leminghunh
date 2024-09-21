package Nagy.iostar.controllers;

import java.io.IOException;

import Nagy.iostar.models.UserModel;
import Nagy.iostar.services.impl.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
    private UserService userService = new UserService();

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/view/admin/home.jsp").forward(req, resp);
		HttpSession session = req.getSession(false);
        
        if (session != null && session.getAttribute("account") != null) {
            UserModel user = (UserModel) session.getAttribute("account");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
        } else {
            // Kiểm tra cookie nếu session không tồn tại
            Cookie[] cookies = req.getCookies();
            String username = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        username = cookie.getValue();
                    }
                }
            }

            if (username != null) {
				UserModel user = userService.FindByUserName(username);
                if (user != null) {
                    session = req.getSession(true);
                    session.setAttribute("account", user);
                    req.setAttribute("username", user.getUsername());
                    req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
                    return;
                }
            }
            
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
		
		
		
		
		
		
		
	}


