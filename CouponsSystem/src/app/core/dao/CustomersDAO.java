package app.core.dao;

import java.util.ArrayList;

import app.core.beans.Customer;
import app.core.exceptions.CouponSystemConnectionException;
import app.core.exceptions.CouponSystemDAOException;

/**
 * @author Nour
 *
 */
public interface CustomersDAO {


	/**
	 * @param email
	 * @param password
	 * @return
	 * @throws CouponSystemConnectionException 
	 * @throws CouponSystemDAOException 
	 */
	boolean isCustomerExists(String email, String password) throws CouponSystemDAOException;
	
	

	void addCustomer(Customer customer) throws CouponSystemDAOException;
	
	

	void updateCustomer(Customer customer) throws CouponSystemDAOException;
	
	

	void deleteCustomer(int customerID) throws CouponSystemDAOException;
	
	

	ArrayList<Customer> getAllCustomers() throws CouponSystemDAOException;
	
	

	Customer getOneCustomer(int customerID) throws CouponSystemDAOException;
	
	
}
