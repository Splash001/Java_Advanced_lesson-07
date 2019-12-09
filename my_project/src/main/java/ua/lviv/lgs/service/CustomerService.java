package ua.lviv.lgs.service;

import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.shared.AbstractCrud;

public interface CustomerService extends AbstractCrud<Customer> {

	Customer getCustomerByEmail(String email);
	
}