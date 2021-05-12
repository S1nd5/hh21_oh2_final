package database;
import java.sql.SQLException;
import java.util.List;
import model.BalsaItem;

public interface BalsaListItemDao {
	    public List<BalsaItem> getAllItems() throws SQLException;

	    public BalsaItem getItem(long id) throws SQLException;

	    public boolean addItem(BalsaItem newItem) throws SQLException;

	    public boolean removeItem(BalsaItem item) throws SQLException;
}
