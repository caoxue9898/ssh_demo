package cn.itcast.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	//��ӿͻ��ݷ�
	public void add(Visit visit) {
		this.getHibernateTemplate().save(visit);
	}

	//�ݷ��б�ķ���
	@SuppressWarnings("unchecked")
	public List<Visit> findAll() {		
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}
}
