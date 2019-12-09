package ua.lviv.lgs.dao;

import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.shared.AbstractCrud;

public interface CustomerDao extends AbstractCrud<Customer> {
	
	Customer getCustomerByEmail(String email);

}