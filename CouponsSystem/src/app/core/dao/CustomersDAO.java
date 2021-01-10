package app.core.dao;

import java.util.ArrayList;

import app.core.beans.Customer;
import app.core.exceptions.ConnectionPoolException;
import app.core.exceptions.DAOException;

/**
 * @author Nour
 *
 */
public interface CustomersDAO {


	/**
	 * @param email
	 * @param password
	 * @return
	 * @throws ConnectionPoolException 
	 * @throws DAOException 
	 */
	boolean isCustomerExists(String email, String password) throws DAOException;
	
	
	public boolean isCustomerEmailExists(String email) throws DAOException;
	
	
	
	void addCustomer(Customer customer) throws DAOException;
	
	

	void updateCustomer(Customer customer) throws DAOException;
	
	

	void deleteCustomer(int customerID) throws DAOException;
	
	

	ArrayList<Customer> getAllCustomers() throws DAOException;
	
	

	Customer getOneCustomer(int customerID) throws DAOException;
	
	
}
