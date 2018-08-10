package com.qfedu.SSHDemo.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qfedu.SSHDemo.controller.MainController;
import com.qfedu.SSHDemo.dao.RoleDao;
import com.qfedu.SSHDemo.po.Role;
import com.qfedu.SSHDemo.vo.DataTable;

@Repository
public class RoleDaoImpl implements RoleDao {

	private final static Logger LOG = LogManager.getLogger(RoleDaoImpl.class);

	/**
	 * 引入sessionfactory
	 */
	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Role> findAllBy(Integer start, Integer length, String search, String nameDir) {

		String hql = "from Role r where r.name like :name order by r.name "+nameDir;
		
		return sessionFactory.getCurrentSession().createQuery(hql,Role.class).setParameter("name", "%" +search+ "%")
				.setFirstResult(start).setMaxResults(length).list();
	}


	@Override
	public Long countAllBy(String search) {
		String hql = "select count(*) from Role r where r.name like:name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%"+search+"%").uniqueResult();
		
		return count;
	}


	@Override
	public void create(Role role) {

		sessionFactory.getCurrentSession().save(role);
	}


	@Override
	public Role faindById(Integer id) {

	 	return sessionFactory.getCurrentSession().get(Role.class, id);
	}


	@Override
	public void update(Role po) {
		sessionFactory.getCurrentSession().update(po);
		
	}


	@Override
	public void delete(Role po) {
		sessionFactory.getCurrentSession().delete(po);
	}
}
