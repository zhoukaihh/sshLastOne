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
	
	/**
	 *查询职位列表内容
	 */
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

	/**
	 * 通过id查询职位
	 */
	@Override
	public Position findById(Integer id) {
		String sql = "from Position p where p.id like :name";
		return sessionFactory.getCurrentSession().createQuery(sql,Position.class).setParameter("name", id).uniqueResult();
	}

	/**
	 * 创建职位
	 */
	@Override
	public void save(Position po) {
		sessionFactory.getCurrentSession().save(po);
	}
	
	/**
	 * 修改职位信息
	 */
	@Override
	public void update(Position po) {
		sessionFactory.getCurrentSession().update(po);
		
	}
	
	/**
	 * 删除职位
	 */
	@Override
	public void delete(Position po) {
		sessionFactory.getCurrentSession().delete(po);
	}


}
