package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.JournalDao;
import ua.lviv.lgs.domain.Journal;
import ua.lviv.lgs.utils.ConnectionUtils;

public class JournalDaoImpl implements JournalDao {

	private static String READ_ALL = "select * from journal";
	private static String CREATE = "insert into journal(`journal_name`, `journal_description`, `price`, `isbn`, `quantity`) values (?,?,?,?,?)";
	private static String READ_BY_ID = "select * from journal where id =?";
	private static String UPDATE_BY_ID = "update journal set journal_name=?, journal_description = ?, price = ?, isbn = ?, quantity = ? where id = ?";
	private static String DELETE_BY_ID = "delete from journal where id=?";

	private static Logger LOGGER = Logger.getLogger(JournalDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public JournalDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public Journal create(Journal journal) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, journal.getJournalName());
			preparedStatement.setString(2, journal.getJournalDescription());
			preparedStatement.setDouble(3, journal.getPrice());
			preparedStatement.setString(4, journal.getIsbn());
			preparedStatement.setInt(5, journal.getQuantity());
			preparedStatement.executeUpdate();

			ResultSet result = preparedStatement.getGeneratedKeys();
			result.next();
			journal.setId(result.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return journal;
	}

	@Override
	public Journal read(int id) {
		Journal journal = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			Integer journalId = result.getInt("id");
			String journalName = result.getString("journal_name");
			String journalDescription = result.getString("journal_description");
			Double price = result.getDouble("price");
			String isbn = result.getString("isbn");
			Integer quantity = result.getInt("quantity");

			journal = new Journal(journalId, journalName, journalDescription, price, isbn, quantity);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return journal;
	}

	@Override
	public Journal update(Journal journal) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, journal.getJournalName());
			preparedStatement.setString(2, journal.getJournalDescription());
			preparedStatement.setDouble(3, journal.getPrice());
			preparedStatement.setString(4, journal.getIsbn());
			preparedStatement.setInt(5, journal.getQuantity());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return journal;
	}

	@Override
	public void delete(int id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<Journal> readAll() {
		List<Journal> journalRecords = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Integer journalId = result.getInt("id");
				String journalName = result.getString("journal_name");
				String journalDescription = result.getString("journal_description");
				Double price = result.getDouble("price");
				String isbn = result.getString("isbn");
				Integer quantity = result.getInt("quantity");

				journalRecords.add(new Journal(journalId, journalName, journalDescription, price, isbn, quantity));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return journalRecords;
	}
}