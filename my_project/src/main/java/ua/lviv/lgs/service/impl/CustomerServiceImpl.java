  
package ua.lviv.lgs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.CustomerDao;
import ua.lviv.lgs.dao.impl.CustomerDaoImpl;
import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private static Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
	private static CustomerService customerServiceImpl;
	private CustomerDao customerDao;

	private CustomerServiceImpl() {
		try {
			customerDao = new CustomerDaoImpl();
		} catch (InstantiationException e) {
			LOGGER.error(e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	
	public static CustomerService getCustomerService() {
		if (customerServiceImpl == null) {
			customerServiceImpl = new CustomerServiceImpl();
		}
		return customerServiceImpl;
	} 

	@Override
	public Customer create(Customer t) {
		return customerDao.create(t);
	}

	@Override
	public Customer read(int id) {
		return customerDao.read(id);
	}

	@Override
	public Customer update(Customer t) {
		return customerDao.update(t);
	}

	@Override
	public void delete(int id) {
		customerDao.delete(id);
	}

	@Override
	public List<Customer> readAll() {
		return customerDao.readAll();
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerDao.getCustomerByEmail(email);
	}

}