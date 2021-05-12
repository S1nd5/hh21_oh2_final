package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.BalsaItem;

public class JDBCBalsaListItemDao implements BalsaListItemDao {
	
	@SuppressWarnings("unused")
	private long nextId = -1;
	private String url;
	private Connection yhteys;
	
	public JDBCBalsaListItemDao() throws ClassNotFoundException, SQLException  {
		super();
		Class.forName("org.sqlite.JDBC");
		//System.getenv("JDBC_DATABASE_URL") Otettu pois käytöstä, koska tomcatin lisäsäätö palvelimella.
		this.url = "jdbc:sqlite:C:\\databases\\lopputyo.sqlite";
		Connection yhteys = DriverManager.getConnection(this.url);
		this.yhteys = yhteys;
		if ( this.yhteys.isClosed() != true ) {
			System.out.println("[DB] Connected.");
		} else {
			System.out.println("[DB] Disconnected.");
		}
	}
	
	public Connection getConnection() {
		return this.yhteys;
	}

	public boolean getConnectionStatus() throws SQLException {
		if ( this.yhteys.isClosed() != true ) {
			return true;
		} else {
			return false;
		}
	}

	 @Override
	 public List<BalsaItem> getAllItems() throws SQLException {
	     List<BalsaItem> lista = new ArrayList<BalsaItem>();
	     PreparedStatement kysely = this.yhteys.prepareStatement("SELECT * FROM Balsalevy");
		 ResultSet tulokset = kysely.executeQuery();
		 while (tulokset.next()) {
			 long id = tulokset.getLong("id");
			 double tiheys = tulokset.getDouble("tiheys");
			 double korkeus = tulokset.getDouble("korkeus");
			 double leveys = tulokset.getDouble("leveys");
			 double paino = tulokset.getDouble("paino");
			 double pituus = tulokset.getDouble("pituus");
	 	     String grain = tulokset.getString("grain");
		     BalsaItem uusi = new BalsaItem(id, tiheys, korkeus, leveys, paino, pituus, grain);
		     lista.add(uusi);
		 }
		tulokset.close();
		return lista;
	 }

	 @Override
	 public BalsaItem getItem(long id) throws SQLException {
		PreparedStatement kysely = this.yhteys.prepareStatement("SELECT * FROM Balsalevy WHERE id=?");
		kysely.setLong(1, id);
		ResultSet tulokset = kysely.executeQuery();
		if ( tulokset.next()) {
			 double tiheys = tulokset.getDouble("tiheys");
			 double korkeus = tulokset.getDouble("korkeus");
			 double leveys = tulokset.getDouble("leveys");
			 double paino = tulokset.getDouble("paino");
			 double pituus = tulokset.getDouble("pituus");
	 	     String grain = tulokset.getString("grain");
		    BalsaItem uusi = new BalsaItem(id, tiheys, korkeus, leveys, paino, pituus, grain);
			return uusi;
		} else {
			return null;
		}
	 }

	 @Override
	 public boolean addItem(BalsaItem newItem) throws SQLException {
		 System.out.println("Adding item: " + newItem.getGrain());
	     PreparedStatement insertKysely = this.yhteys.prepareStatement("INSERT INTO Balsalevy (tiheys,korkeus,leveys,paino,pituus,grain) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
	     @SuppressWarnings("unused")
	     ResultSet rs = insertKysely.getGeneratedKeys();
	     insertKysely.setDouble(1, newItem.getTiheys());
	     insertKysely.setDouble(2, newItem.getKorkeus());
	     insertKysely.setDouble(3, newItem.getLeveys());
	     insertKysely.setDouble(4, newItem.getPaino());
	     insertKysely.setDouble(5, newItem.getPituus());
	     insertKysely.setString(6, newItem.getGrain());
	     insertKysely.executeUpdate();
	     insertKysely.close();
	     return true;
	 }

	 @Override
	 public boolean removeItem(BalsaItem item) throws SQLException {
	     PreparedStatement deleteKysely = this.yhteys.prepareStatement("DELETE FROM Balsalevy WHERE id=?");
	     deleteKysely.setLong(1, item.getId());
	     int count = deleteKysely.executeUpdate();
	     deleteKysely.close();
	     if ( count > 0 ) {
	    	 return true;
	     } else {
	    	 return false;
	     }
	 }
}
