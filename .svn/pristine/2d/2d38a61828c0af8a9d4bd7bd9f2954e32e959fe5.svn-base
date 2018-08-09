package com.qfedu.SSHDemo.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qfedu.SSHDemo.dao.MenuDao;
import com.qfedu.SSHDemo.po.Menu;


@Repository
public class MenuDaoImpl implements MenuDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Menu> getTopMenuParentIdIsNull() {
		String hql = "from Menu m where m.parent.id is null";
		List<Menu> pos = sessionFactory.getCurrentSession().createQuery(hql,Menu.class).list();
		
		return pos;
	}


	@Override
	public List<Menu> findAll(Integer start, Integer length, String search, String noDir) {
		String hql = "from Menu m where m.name like :name or m.no like :name order by m.no " + noDir;
		
		 List<Menu> menus = sessionFactory.getCurrentSession().createQuery(hql,Menu.class).setParameter("name", "%"+search+"%")
				.setFirstResult(start).setMaxResults(length).list();
		 return menus ;
	}


	@Override
	public Long countAll(String search) {
		String hql = "select count(*) from Menu m where m.name like :name or m.no like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%"+search+"%").uniqueResult();
		
		return count;
	}


	@Override
	public void save(Menu po) {
		sessionFactory.getCurrentSession().save(po);
	}


	@Override
	public void deleteById(Integer i) {
		Menu m = findById(i);
		sessionFactory.getCurrentSession().delete(m);
		
	}


	@Override
	public Menu findById(Integer id) {
		return sessionFactory.getCurrentSession().get(Menu.class, id);
	}


	@Override
	public void update(Menu po) {

		sessionFactory.getCurrentSession().update(po);
	}

}
