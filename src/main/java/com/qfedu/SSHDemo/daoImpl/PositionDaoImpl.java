package com.qfedu.SSHDemo.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qfedu.SSHDemo.dao.PositionDao;
import com.qfedu.SSHDemo.po.Position;

@Repository
public class PositionDaoImpl implements PositionDao {

	
	/**
	 *引入 sessionFactory
	 */
	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Position> findAllBy(Integer start, Integer length, String search, String levelDir) {
		String hql = "from Position p where p.name like :name order by p.level " + levelDir;
		
		 List<Position> positions = sessionFactory.getCurrentSession().createQuery(hql,Position.class).setParameter("name", "%"+search+"%")
				.setFirstResult(start).setMaxResults(length).list();
		 return positions ;
	}


	@Override
	public Long countAllBy(String search) {
		String hql = "select count(*) from Position p where p.name like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%"+search+"%").uniqueResult();
		return count;
	}

}
