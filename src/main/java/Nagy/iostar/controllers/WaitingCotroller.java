package Nagy.iostar.controllers;

import java.io.IOException;

import Nagy.iostar.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/waiting"})
public class WaitingCotroller extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession();
		if(session != null && session.getAttribute("account") != null) {
		UserModel u=(UserModel) session.getAttribute("account");
		req.setAttribute("username", u.getUsername());
		
		// thanh cong thi cai Path no xet theo roleid o ham string getRedirectPath
        String redirectPath = getRedirectPath(u.getRoleid());
        resp.sendRedirect(req.getContextPath() + redirectPath);
    } else {
    	// that bai thi ve trang chu login 
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}

		// xét đieu kien
		private String getRedirectPath(int roleId) {
	        switch (roleId) {
	            case 1: 
	                return "/admin/home";
	            case 2: 
	                return "/admin/manager/home";
	            case 3: 
	                return "/user/home";
	            default: 
	                return "/login";
	        }
	    
		


	}
		


}
