package app.core.dao;

import java.util.ArrayList;

import app.core.beans.Category;
import app.core.beans.Coupon;
import app.core.exceptions.CouponSystemDAOException;


public interface CouponsDAO {



	boolean isCouponExists(int couponID) throws CouponSystemDAOException;
	
	

	void addCoupon(Coupon coupon) throws CouponSystemDAOException;
	
	

	void updateCoupon(Coupon coupon) throws CouponSystemDAOException;
	
	

	void deleteCoupon(int couponID) throws CouponSystemDAOException;
	
	

	ArrayList<Coupon> getAllCoupons() throws CouponSystemDAOException;
	
	

	Coupon getOneCoupon(int couponID) throws CouponSystemDAOException;
	
	
	
	void addCategory(Category category) throws CouponSystemDAOException;
	
	
	
	boolean isCategoryExists(Category category) throws CouponSystemDAOException;
	
	
	
	void addCouponPurchase(int customerID, int couponID) throws CouponSystemDAOException;
	
	
	
	void deleteCouponPurchase(int customerID, int couponID) throws CouponSystemDAOException;
	
	
	
	
	
	
}
