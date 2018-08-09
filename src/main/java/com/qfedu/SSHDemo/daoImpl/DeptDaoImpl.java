package com.qfedu.SSHDemo.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qfedu.SSHDemo.po.Dept;
import com.qfedu.SSHDemo.po.Menu;

@Repository
public class DeptDaoImpl implements com.qfedu.SSHDemo.dao.DeptDao {

	/**
	 * 引入sessionFactory
	 */
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public List<Dept> findAllBy(Integer start, Integer length, String search, String noDir) {
		String hql = "from Dept d where d.name like :name or d.no like :name order by d.no " + noDir;
		
		 List<Dept> depts = sessionFactory.getCurrentSession().createQuery(hql,Dept.class).setParameter("name", "%"+search+"%")
				.setFirstResult(start).setMaxResults(length).list();
		 return depts ;
	}

	@Override
	public Long count(String search) {
		String hql = "select count(*) from Dept d where d.name like :name or d.no like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%"+search+"%").uniqueResult();
		return count;
	}

}
