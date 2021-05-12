package database;

import static org.junit.Assert.*;

import model.BalsaItem;
import database.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

@SuppressWarnings("unused")
public class JDBCBalsaListItemDaoTest {
	
	private JDBCBalsaListItemDao dao;
	private BalsaItem item;
	
	public JDBCBalsaListItemDaoTest() throws ClassNotFoundException, SQLException {
		this.dao = new JDBCBalsaListItemDao();
	}
	
	@Before
	 public void setUp() throws Exception {
		this.dao.getConnectionStatus();
		this.dao.addItem(this.item);
		this.item = this.dao.getItem(0);
	}
	
	@Test
	public void testaaLista() throws Exception {
		List<BalsaItem> lista = this.dao.getAllItems();
		assertNotNull(lista);
	}
	
	/*@Test
	public void testaaLisays() throws Exception {
		assertTrue(this.dao.addItem(new BalsaItem(1, "Testi 2")));
	}
	
	@Test
	public void testaaHaku() throws Exception {
		assertTrue(this.dao.getItem(1) != null);
	}
	
	@Test
	public void testaaPoisto() throws Exception {
		BalsaItem uusi = new BalsaItem(2, "Testi 3");
		this.dao.addItem(uusi);
		if ( this.dao.removeItem(uusi)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}*/

}
