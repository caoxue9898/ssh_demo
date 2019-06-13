package cn.itcast.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class pClass;

	// ���ɹ��췽��
	public BaseDaoImpl() {// ���󴴽�ִ�й��췽��
		// ��һ�� �õ���ǰ������Class
		Class clazz = this.getClass();
		// System.out.println("****************"+clazz);

		// �ڶ��� �õ�������� ����Ĳ��������� BaseDaoImpl<Customer>
		// Type getGenericSuperclass()
		Type type = clazz.getGenericSuperclass();
		// ʹ��Type�ӽӿ� ParameterizedType
		ParameterizedType ptype = (ParameterizedType) type;// ǿת
		// System.out.println("***********"+ptype);

		// ������ �õ�ʵ�����Ͳ���<Customer>����Customer
		// Type[] getActualTypeArguments()
		Type[] types = ptype.getActualTypeArguments();
		// Type�ӿ�ʵ����Class
		Class tclass = (Class) types[0];
		// System.out.println("*****"+pclass);
		this.pClass = tclass;
	}

	// ����id��ѯ
	@SuppressWarnings("unchecked")
	public T findOne(int id) {
		return (T) this.getHibernateTemplate().get(pClass, id);
	}

	// ��ѯ����
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		//ʹ��Class����getSimpleName() �õ�������
		return (List<T>) this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}

	// ���
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	// �޸�
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	// ɾ��
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

}
