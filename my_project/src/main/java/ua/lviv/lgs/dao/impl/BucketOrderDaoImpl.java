  
package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.BucketOrderDao;
import ua.lviv.lgs.domain.BucketOrder;
import ua.lviv.lgs.utils.ConnectionUtils;

public class BucketOrderDaoImpl implements BucketOrderDao {

	private static String READ_ALL = "select * from bucket_order";
	private static String CREATE = "insert into bucket_order(`customer_id`, `book_id`, `purchase_date`) values (?,?,?)";
	private static String READ_BY_ID = "select * from bucket_order where id =?";
	private static String DELETE_BY_ID = "delete from bucket_order where id=?";

	private static Logger LOGGER = Logger.getLogger(BucketOrderDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public BucketOrderDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public BucketOrder create(BucketOrder bucketOrder) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, bucketOrder.getCustomerId());
			preparedStatement.setInt(2, bucketOrder.getBookId());
			preparedStatement.setDate(3, new Date(bucketOrder.getPurchaseDate().getTime()));
			preparedStatement.executeUpdate();
			
			ResultSet result = preparedStatement.getGeneratedKeys();
			result.next();
			bucketOrder.setId(result.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return bucketOrder;
	}

	@Override
	public BucketOrder read(int id) {

		BucketOrder bucketOrder = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			Integer bucketOrderId = result.getInt("id");
			Integer customerId = result.getInt("customer_id");
			Integer bookId = result.getInt("book_id");
			Date purchaseDate = result.getDate("purchase_date");

			bucketOrder = new BucketOrder(bucketOrderId, customerId, bookId, purchaseDate);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return bucketOrder;
	}

	@Override
	public BucketOrder update(BucketOrder t) {
		throw new IllegalStateException("No update for bucket_order");
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
	public List<BucketOrder> readAll() {
		List<BucketOrder> bucketOrderRecords = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Integer bucketOrderId = result.getInt("id");
				Integer customerId = result.getInt("customer_id");
				Integer bookId = result.getInt("book_id");
				Date purchaseDate = result.getDate("purchase_date");
				bucketOrderRecords.add(new BucketOrder(bucketOrderId, customerId, bookId, purchaseDate));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return bucketOrderRecords;
	}

}