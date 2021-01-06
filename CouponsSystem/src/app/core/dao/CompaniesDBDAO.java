package app.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.core.beans.Company;
import app.core.db.ConnectionPool;
import app.core.exceptions.CouponSystemConnectionException;
import app.core.exceptions.CouponSystemDAOException;

public class CompaniesDBDAO implements CompaniesDAO {
	
	private static ConnectionPool connectionPool;
	
	/**
	 * @throws CouponSystemConnectionException 
	 * 
	 */
	public CompaniesDBDAO() throws CouponSystemConnectionException {
		connectionPool = ConnectionPool.getInstance();
	}

	/**
	 *
	 */
	@Override
	public boolean isCompanyExists(String email, String password) throws CouponSystemDAOException  {
		
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
		} catch (CouponSystemConnectionException | SQLException e) {
			e.printStackTrace();
			throw new CouponSystemDAOException("DAO Error: checking existence failed", e);
		}
		
		connectionPool.restoreConnection(con);
		return isExist;
	}

	/**
	 *
	 */
	@Override
	public void addCompany(Company company) throws CouponSystemDAOException {
		
		Connection con;
		
		try {
			con = connectionPool.getConnection();
			String sql = "insert into companies values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, company.getId());
			pstmt.setString(2, company.getName());
			pstmt.setString(3, company.getEmail());
			pstmt.setString(4, company.getPassword());
			pstmt.executeUpdate();
		} catch (CouponSystemConnectionException | SQLException e) {
			e.printStackTrace();
			throw new CouponSystemDAOException("DAO Error: adding company failed", e);
		}
		
		connectionPool.restoreConnection(con);
	
	}

	/**
	 * @throws CouponSystemDAOException 
	 *
	 */
	@Override
	public void updateCompany(Company company) throws CouponSystemDAOException {
		
		Connection con;
		
		try {
			con = connectionPool.getConnection();
			String sql = "update companies set name=?, email=?, password=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.setInt(4, company.getId());
			
			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				throw new CouponSystemDAOException("DAO Error: failed to find the requiered company, updating failed");
			}

		} catch (CouponSystemConnectionException | SQLException e) {
			e.printStackTrace();
			throw new CouponSystemDAOException("DAO Error: updating company failed", e);
		}
		
		connectionPool.restoreConnection(con);
	}

	/**
	 * @throws CouponSystemDAOException 
	 *
	 */
	@Override
	public void deleteCompany(int companyID) throws CouponSystemDAOException {
		
		Connection con;
		
		try {
			con = connectionPool.getConnection();
			String sql = "delete from companies where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyID);
			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				throw new CouponSystemDAOException("DAO Error: failed to find the requiered company, deleting failed");
			}

		} catch (CouponSystemConnectionException | SQLException e) {
			e.printStackTrace();
			throw new CouponSystemDAOException("DAO Error: deleting company failed", e);
		}
		
		connectionPool.restoreConnection(con);
		
	}

	/**
	 * @throws CouponSystemDAOException 
	 *
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws CouponSystemDAOException {
		
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
			
		} catch (CouponSystemConnectionException | SQLException e) {
			e.printStackTrace();
			throw new CouponSystemDAOException("DAO Error: getting all companies failed", e);
		}
		
		return companies;
	}

	/**
	 * @throws CouponSystemDAOException 
	 *
	 */
	@Override
	public Company getOneCompany(int companyID) throws CouponSystemDAOException {
		
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
			}else {
				throw new CouponSystemDAOException("DAO Error: failed to find the requiered company, getting company failed");
			}
			
		} catch (CouponSystemConnectionException | SQLException e) {
			e.printStackTrace();
			throw new CouponSystemDAOException("DAO Error: getting company failed", e);
		}
		
		return company;
		
	}

	
}
