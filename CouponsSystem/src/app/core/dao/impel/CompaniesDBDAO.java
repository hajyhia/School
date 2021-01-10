package app.core.dao.impel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.core.beans.Company;
import app.core.dao.CompaniesDAO;
import app.core.db.ConnectionPool;
import app.core.exceptions.ConnectionPoolException;
import app.core.exceptions.DAOException;

public class CompaniesDBDAO implements CompaniesDAO {

	private ConnectionPool connectionPool;

	/**
	 * @throws DAOException 
	 * @throws ConnectionPoolException
	 * 
	 */
	public CompaniesDBDAO() throws DAOException {
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: initializing companies failed", e);
		}		
	}



	@Override
	public boolean isCompanyExists(String email, String password) throws DAOException {

		boolean isExist = false;
		Connection con;

		try {
			con = connectionPool.getConnection();
			String sql = "select * from companies where email = ? AND password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: checking existence failed: failed to aquire connection from connection pool", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: checking existence failed: failed to aquire company from SQL", e);
		}

		connectionPool.restoreConnection(con);
		return isExist;
		
	}
	
	@Override
	public boolean isCompanyEmailExist(String companyEmail) throws DAOException {
		boolean isExist = false;
		Connection con;

		try {
			con = connectionPool.getConnection();
			String sql = "select * from companies where email = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, companyEmail);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: checking existence failed", e);
		}

		connectionPool.restoreConnection(con);
		return isExist;
	}
	
	@Override
	public boolean isCompanyNameExist(String companyName) throws DAOException {
		boolean isExist = false;
		Connection con;

		try {
			con = connectionPool.getConnection();
			String sql = "select * from companies where name = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, companyName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: checking existence failed", e);
		}

		connectionPool.restoreConnection(con);
		return isExist;
	}

	/**
	 *
	 */
	@Override
	public void addCompany(Company company) throws DAOException {

		Connection con;

		try {
			con = connectionPool.getConnection();
			String sql = "insert into companies values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "default");
			pstmt.setString(2, company.getName());
			pstmt.setString(3, company.getEmail());
			pstmt.setString(4, company.getPassword());
			pstmt.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: adding company failed", e);
		}

		connectionPool.restoreConnection(con);

	}

	/**
	 * @throws DAOException
	 *
	 */
	@Override
	public void updateCompany(Company company) throws DAOException {

		Connection con;

		try {
			con = connectionPool.getConnection();
			String sql = "update companies set email=?, password=? where id=? and name=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company.getEmail());
			pstmt.setString(2, company.getPassword());
			pstmt.setInt(3, company.getId());
			pstmt.setString(4, company.getName());

			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				throw new DAOException("DAO Error: failed to find the requiered company, updating failed");
			}

		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: updating company failed", e);
		}

		connectionPool.restoreConnection(con);
	}

	/**
	 * @throws DAOException
	 *
	 */
	@Override
	public void deleteCompany(int companyID) throws DAOException {

		Connection con;

		try {
			con = connectionPool.getConnection();
			String sql = "delete from companies where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyID);
			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				throw new DAOException("DAO Error: failed to find the requiered company, deleting failed");
			}

		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: deleting company failed", e);
		}

		connectionPool.restoreConnection(con);

	}

	/**
	 * @throws DAOException
	 *
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws DAOException {

		Connection con;
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			con = connectionPool.getConnection();
			String sql = "select * from companies";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				companies.add(company);
			}

		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: getting all companies failed", e);
		}

		connectionPool.restoreConnection(con);
		return companies;
	}

	/**
	 * @throws DAOException
	 *
	 */
	@Override
	public Company getOneCompany(int companyID) throws DAOException {

		Connection con;
		Company company;

		try {
			con = connectionPool.getConnection();
			String sql = "select * from companies where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				company = new Company(companyID);
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			} else {
				throw new DAOException(
						"DAO Error: failed to find the requiered company, getting company failed");
			}

		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: getting company failed", e);
		}

		connectionPool.restoreConnection(con);
		return company;

	}



	@Override
	public Company getLogedInCompany(String email, String password) throws DAOException {

		Connection con;
		Company company;

		try {
			con = connectionPool.getConnection();
			String sql = "select * from companies where email = ? AND password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			} else {
				throw new DAOException(
						"DAO Error: failed to find the requiered company, getting company failed");
			}

		} catch (ConnectionPoolException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("DAO Error: getting company failed", e);
		}

		connectionPool.restoreConnection(con);
		return company;
	}

}
