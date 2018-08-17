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
	
	/**
	 * 查询部门列表信息
	 */
	@Override
	public List<Dept> findAllBy(Integer start, Integer length, String search, String noDir) {
		String hql = "from Dept d where d.name like :name or d.no like :name order by d.no " + noDir;
		
		 List<Dept> depts = sessionFactory.getCurrentSession().createQuery(hql,Dept.class).setParameter("name", "%"+search+"%")
				.setFirstResult(start).setMaxResults(length).list();
		 return depts ;
	}

	/**
	 * 计数
	 */
	@Override
	public Long count(String search) {
		String hql = "select count(*) from Dept d where d.name like :name or d.no like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%"+search+"%").uniqueResult();
		return count;
	}

	/**
	 * 创建部门
	 */
	@Override
	public void save(Dept po) {
		sessionFactory.getCurrentSession().save(po);
	}

	/**
	 * 通过id查找部门
	 */
	@Override
	public Dept findById(Integer id) {
		String sql = "from Dept d where d.id like :name";
		return sessionFactory.getCurrentSession().createQuery(sql,Dept.class).setParameter("name", id).uniqueResult();
	}

	/**
	 * 修改部门信息
	 */
	@Override
	public void update(Dept po) {
		sessionFactory.getCurrentSession().update(po);
	}

	/**
	 * 删除选中部门
	 */
	@Override
	public void delete(Dept po) {
		sessionFactory.getCurrentSession().delete(po);
	}

}
