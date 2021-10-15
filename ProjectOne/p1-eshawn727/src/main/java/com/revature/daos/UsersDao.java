package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.utils.HibernateUtil;

public class UsersDao implements UsersDaoI {
	
	public void addUser(Users user) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(user);
		
		HibernateUtil.closeSession();	
	}

	@Override
	public List<Users> getAllUsers() {
		
		Session ses = HibernateUtil.getSession();
		
		List<Users> usersList = ses.createQuery("FROM Users").list();
		
		HibernateUtil.closeSession();
		
		return usersList;	
	}


	@Override
    public boolean getUserLogin(String username, String password) {
        
        Session ses = HibernateUtil.getSession();
        
        String HQL = "from Users user where user.username=:name and user.password=:pass";
        
        Query q = ses.createQuery(HQL);
        
        q.setParameter("name", username);
        q.setParameter("pass", password);

        List result = q.list();
        
        if((result != null) && (result.size() > 0)) {
        	
            HibernateUtil.closeSession();
            return true;
            
        } else {
        	
            return false;
        }
    }

	@Override
	public Users getUserByLogin(String username) {
		
		Session ses = HibernateUtil.getSession();
		
		String HQL = "FROM Users where username = '" + username + "'";
		
		Query q = ses.createQuery(HQL);
		
		Object user = q.uniqueResult();
		
		HibernateUtil.closeSession();
		
		return (Users) user;
	}

	@Override
	public Users getUserById(int id) {
		
		Session ses = HibernateUtil.getSession();
		
		String HQL = "FROM Users WHERE user_id = '" + id + "'";
		
		Query q = ses.createQuery(HQL);
		
		Object user = q.uniqueResult();
		
		HibernateUtil.closeSession();
		
		return (Users) user;
	}
}
