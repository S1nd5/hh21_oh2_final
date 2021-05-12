package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.JDBCBalsaListItemDao;
import model.BalsaItem;

@SuppressWarnings("serial")
@WebServlet("")
public class IndexServlet extends HttpServlet {
	
	private JDBCBalsaListItemDao dao;
	
	public IndexServlet() throws ClassNotFoundException, SQLException {
		this.dao = new JDBCBalsaListItemDao();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<BalsaItem> lista = this.dao.getAllItems();
	        // pass the time string to the JSP page as an attribute
	        req.setAttribute("balsaItems", lista);
	        // forward the request to the index.jsp page
	        req.getRequestDispatcher("/WEB-INF/balsa.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendError(500, "SQL-virhe.");
		}
    }
    
    public double calculateVolume(double paksuus, double pituus, double leveys) {
    	return paksuus * pituus * leveys;
    }
    
    public double calculateDensity(double paino, double volume) {
    	return (paino / 1000.0) / (volume / 1000000000.0);
    }
    
    public double checkForComma(String value) {
    	if ( value.contains(",")) {
    		String[] pieces = value.split(",");
    		double newVal = Double.valueOf(pieces[0] + "." + pieces[1]);
    		return newVal;
    	} else {
    		return Double.valueOf(value);
    	}
    }
    
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
		HttpSession session = req.getSession(true);
		DecimalFormat decimal = new DecimalFormat("0.00");
		
		double paksuus = checkForComma(req.getParameter("paksuus"));
		double pituus = checkForComma(req.getParameter("pituus"));
		double leveys = checkForComma(req.getParameter("leveys"));
		double paino = checkForComma(req.getParameter("paino"));
		String grain = String.valueOf(req.getParameter("grain"));
		
		double volume = calculateVolume(paksuus, pituus, leveys);
		double density_km = calculateDensity(paino, volume);
		double density_ft = (density_km*0.062427961);
		
		//long id, double tiheys, double korkeus, double leveys, double paino, double pituus, String grain
		BalsaItem uusi = new BalsaItem(0, density_km, paksuus, leveys, paino, pituus, grain);
		boolean authenticated = false;
		if ( session.getAttribute("authenticated") != null) {
			authenticated = (boolean) session.getAttribute("authenticated") || false;
		}
		if ( authenticated) {
			System.out.println("ID is: " + uusi.getId());
			try {
				this.dao.addItem(uusi);
				System.out.println("Item added.");
				req.setAttribute("density_km", decimal.format(density_km));
				req.setAttribute("density_ft", decimal.format(density_ft));
	    		session.setAttribute("message", "Tieto lisätty tietokantaan.");
	    		session.setAttribute("messageStatus", "success");
				List<BalsaItem> lista = this.dao.getAllItems();
		        req.setAttribute("balsaItems", lista);
		        req.getRequestDispatcher("/WEB-INF/balsa.jsp").forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
				resp.sendError(500, "SQL-virhe.");
			}
		} else {
    		session.setAttribute("message", "Laskutoimitus suoritettu.");
    		session.setAttribute("messageStatus", "success");
			req.setAttribute("density_km", decimal.format(density_km));
			req.setAttribute("density_ft", decimal.format(density_ft));
			System.out.println("Density: " + density_km);
			req.getRequestDispatcher("/WEB-INF/balsa.jsp").forward(req, resp);
		}
	}
	
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		long id = Long.parseLong(req.getParameter("id"));
		boolean authenticated = false;
		if ( session.getAttribute("authenticated") != null) {
			authenticated = (boolean) session.getAttribute("authenticated") || false;
		}
		if ( authenticated ) {
			System.out.println("ID to delete: " + id);
			try {
				BalsaItem thisItem = dao.getItem(id);
				this.dao.removeItem(thisItem);
	    		session.setAttribute("message", "Poisto id:lle " + id + " onnistui.");
	    		session.setAttribute("messageStatus", "success");
				List<BalsaItem> lista = this.dao.getAllItems();
		        req.setAttribute("balsaItems", lista);
		        System.out.println("Deleted..");
		        resp.setStatus(200);
			} catch ( SQLException e ) {
				e.printStackTrace();
				resp.sendError(500, "SQL-virhe.");
			}
		} else {
    		session.setAttribute("message", "Poistoa ei voida suorittaa, koska et ole tunnistautunut.");
    		session.setAttribute("messageStatus", "danger");
			resp.setStatus(403);
		}
		
	}
}
