package com.qfedu.SSHDemo.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.qfedu.SSHDemo.dao.UserDao;
import com.qfedu.SSHDemo.po.User;

@Repository
public class UserDaoImpl implements UserDao{

	private final static Logger LOG = LogManager.getLogger(UserDaoImpl.class);

	/**
	 * 引入sessionFactory
	 */
	@Resource
	private SessionFactory sessionFactory;
	
	/**
	 * 查询所有用户
	 */
	public List<User> getAll() {
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User",User.class).list();
		
		return users;
	}

	/**
	 * 通过姓名查询用户
	 */
	@Override
	public User getByName(String loginName) {
		
		User user = (User) sessionFactory.getCurrentSession().createQuery("from User u where u.loginName=:loginName",User.class)
				.setParameter("loginName", loginName).uniqueResult();
		LOG.info(user);
		return user;
	}

	/**
	 * 保存要创建的用户
	 */
	@Override
	public void save(User u) {

		sessionFactory.getCurrentSession().save(u);

	}
	
	@Override
	public User findById(Integer id) {
		
		return sessionFactory.getCurrentSession().get(User.class, id);
	}


	@Override
	public void update(User po) {
		 sessionFactory.getCurrentSession().update(po);		
	}

	@Override
	public List<User> findAll(Integer start, Integer length, String search, String loginDir) {

		String hql = "from User u where u.name like :name or u.loginName like :name order by u.loginName " + loginDir;
		
		 List<User> users = sessionFactory.getCurrentSession().createQuery(hql,User.class).setParameter("name", "%"+search+"%")
				.setFirstResult(start).setMaxResults(length).list();
		 return users;
	}

	@Override
	public Long countAll(String search) {

		String hql = "select count(*) from User u where u.name like :name or u.loginName like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%"+search+"%").uniqueResult();
		
		return count;
	}

	/**
	 * 通过手机号来查询用户，人员查询对应的用户
	 */
	@Override
	public User findByMobile(String mobile) {
		
		String hql = "from User u where u.loginName like :name";
		
		User user = sessionFactory.getCurrentSession().createQuery(hql,User.class).setParameter("name", mobile).uniqueResult();
		return user;
	}

	@Override
	public void delete(User po) {
		sessionFactory.getCurrentSession().delete(po);
	}

	
}