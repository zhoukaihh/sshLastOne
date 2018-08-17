package com.qfedu.SSHDemo.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qfedu.SSHDemo.dao.StaffDao;
import com.qfedu.SSHDemo.po.Position;
import com.qfedu.SSHDemo.po.Staff;

@Repository
public class StaffDaoImpl implements StaffDao {

	private final static Logger LOG = LogManager.getLogger(StaffDaoImpl.class);

	/**
	 *引入 sessionFactory
	 */
	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Staff> findBySearch(Integer start, Integer length, String search, String nameDir) {
		String hql = "from Staff s where s.name like :name or s.mobile like :name order by s.name " + nameDir;
		
		 List<Staff> staffs = sessionFactory.getCurrentSession().createQuery(hql,Staff.class).setParameter("name", "%"+search+"%")
				.setFirstResult(start).setMaxResults(length).list();
		 return staffs ;
	}


	@Override
	public Long count(String search) {
		String hql = "select count(*) from Staff s where s.name like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%"+search+"%").uniqueResult();
		return count;
	}

	/**
	 * 创建人员
	 */
	@Override
	public void save(Staff po) {
		sessionFactory.getCurrentSession().save(po);
	}


	@Override
	public Staff findById(Integer id) {
		String hql = "from Staff s where s.id like :name";
		return sessionFactory.getCurrentSession().createQuery(hql,Staff.class).setParameter("name", id).uniqueResult();
		
	}

	/**
	 * 修改人员信息
	 */
	@Override
	public void update(Staff po) {
		sessionFactory.getCurrentSession().update(po);
	}

	/**
	 * 删除人员信息
	 */
	@Override
	public void delete(Staff po) {
		sessionFactory.getCurrentSession().delete(po);
	}

}
