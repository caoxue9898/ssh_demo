package cn.itcast.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.itcast.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	/*	
		private HibernateTemplate hibernateTemplate;
		public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
			this.hibernateTemplate = hibernateTemplate;
	}*/

	//��¼�ķ���
	@SuppressWarnings("all")
	public User loginUser(User user) {
		//���÷����õ�hibernateTemplate����
		//HibernateTemplate hibernateTemplate = this.getHibernateTemplate();//thisָ��ǰ�����
		//��¼�Ĳ�ѯ����
		//�����û����������ѯ
		List<User> list = (List<User>) this.getHibernateTemplate().
				find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		//����user����
		//�����ѯ֮��û�н�� ��list����û��ֵ������get(�±�)��ȡ����ֵ�������±�Խ���쳣
		//�ж�
		if(list != null && list.size()!=0) {
			User u = list.get(0);
			return u;
		}
		return null;
	}

	//��ѯ���е��û�
	public List<User> findAll() {
		return (List<User>) this.getHibernateTemplate().find("from User");
	}
	


}
