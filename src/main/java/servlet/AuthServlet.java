package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/auth")

public class AuthServlet extends HttpServlet {
	protected String authPassword = "42";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     req.getRequestDispatcher("/WEB-INF/auth.jsp").forward(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession(false);
    	String password = req.getParameter("salasana");
    	System.out.println("[Auth] Password given: " + password);
    	if ( password.equals(authPassword) ) {
    		System.out.println("[Auth] Authentication succesful -> 200");
    		session.setAttribute("authenticated", true);
    		session.setAttribute("message", "Tunnistautuminen onnistui.");
    		session.setAttribute("messageStatus", "success");
    		resp.sendRedirect("./");
    	} else {
    		System.out.println("[Auth] Authentication failed -> 403");
    		session.setAttribute("authenticated", false);
    		session.setAttribute("message", "Tunnistautuminen epäonnistui, virheellinen salasana.");
    		session.setAttribute("messageStatus", "danger");
    		resp.sendRedirect("./");
    	}
    }
}
