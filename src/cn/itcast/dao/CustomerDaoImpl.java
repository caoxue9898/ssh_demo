package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements
		CustomerDao {

	// ��ӿͻ�����
	// ����hibernate�е�save�����������

	/*
	 * public void add(Customer customer) {
	 * this.getHibernateTemplate().save(customer); }
	 */

	@SuppressWarnings("all")
	// �ͻ��б�Ĺ���(��ѯ����)
	/*
	 * public List<Customer> findAll() { return (List<Customer>)
	 * this.getHibernateTemplate().find( "from Customer"); }
	 */
	// ����id��ѯ
	/*
	 * public Customer findOne(int cid) { return
	 * this.getHibernateTemplate().get(Customer.class, cid); }
	 */
	// ɾ������
	/*
	 * public void delete(Customer c) { this.getHibernateTemplate().delete(c); }
	 */
	// �޸ĵĹ���
	/*
	 * public void update(Customer customer) {
	 * this.getHibernateTemplate().update(customer);//����idֵ���޸� }
	 */
	// ��ѯ��¼��
	public int findCount() {
		List<Object> list = (List<Object>) this.getHibernateTemplate().find(
				"select count(*)from Customer");
		// ��list�еõ�������Ҫ��ֵ
		if (list != null && list.size() != 0) {
			Object obj = list.get(0);
			// ���int����
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	// ��ҳ��ѯ����
	public List<Customer> findPage(int begin, int pageSize) {

		// ��һ�� ʹ��hibernate�ײ����ʵ�֣��˽⣩
		// �õ�sessionFactory
		// SessionFactory sessionFactory =
		// this.getHibernateTemplate().getSessionFactory();
		// �õ�session����
		// Session session = sessionFactory.getCurrentSession();
		// ���÷�ҳ��Ϣ
		// Query query = session.createQuery("from Customer");
		// query.setFirstResult(begin);
		// query.setMaxResults(pageSize);
		// List<Customer> list = query.list();

		// �ڶ��� ʹ�����߶����hibernateTemplate�ķ���ʵ��
		// 1 �������߶������ö��ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

		// criteria.setProjection(Projections.rowCount());//�������߶���ʵ��

		// ����hibernateTemplate�ķ���ʵ��
		// ��һ�����������߶���
		// �ڶ��������ǿ�ʼλ��
		// ������������ÿҳ��¼��
		List<Customer> list = (List<Customer>) this.getHibernateTemplate()
				.findByCriteria(criteria, begin, pageSize);
		return list;
	}

	// ������ѯ
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {
		// ��һ�ַ�ʽ��
		// SessionFactory sessionFactory =
		// this.getHibernateTemplate().getSessionFactory();
		// �õ�session����
		// Session session = sessionFactory.getCurrentSession();
		// Query query =
		// session.createQuery("from Customer where custName like ?");
		// query.setParameter(0, "%"+customer.getCustName()+"%");
		// List<Customer> list = query.list();

		// �ڶ��ַ�ʽ �� ����hibernateTemplate��find����ʵ��
		// List<Customer> list = (List<Customer>) this.getHibernateTemplate().
		// find("from Customer where custName like ?",
		// "%"+customer.getCustName()+"%");//ģ����ѯ ���ٷֺŵ�ƴ��
		// ƴ��hql���ʵ��

		// �����ַ�ʽ
		// 1 �������߶������ö��ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 2 ���ö�ʵ�����ĸ�����
		// ��һ������ ������
		// �ڶ������� �ٷֺŵ�ƴ��
		criteria.add(Restrictions.like("custName", "%" + customer.getCustName()
				+ "%"));
		// 3 ����hibernateTemplate����ķ����õ�list����
		List<Customer> list = (List<Customer>) this.getHibernateTemplate()
				.findByCriteria(criteria);// ����Ĳ���Ϊ���ߵĶ���

		return list;
	}

	// ��������ϲ�ѯ

	@SuppressWarnings("unchecked")
	/*public List<Customer> findMoreCondition(Customer customer) { //
		// ʹ��hibernateģ������find����ʵ�� // ƴ��hql���
		String hql = "from Customer where 1=1 ";
		// �϶�����������
		// ����list���ϣ����ֵ��Ϊ�գ���ֵ���õ�list����
		List<Object> p = new ArrayList<Object>();
		// �ж�����ֵ�Ƿ�Ϊ�գ������Ϊ��ƴ��hql���
		if (customer.getCustName() != null
				&& !"".equals(customer.getCustName())) {
			// ƴ��hql hql += " and custName=?";//ע��ƴ��ʱ�Ŀո� // ��ֵ���õ�list����
			p.add(customer.getCustName());
		}
		if (customer.getCustLevel() != null
				&& !"".equals(customer.getCustLevel())) {
			hql += " and custLevel=?";
			p.add(customer.getCustLevel());
		}
		if (customer.getCustSource() != null
				&& !"".equals(customer.getCustSource())) {
			hql += " and custSource=?";
			p.add(customer.getCustSource());
		} // System.out.println("hql: "+hql); //
		System.out.println("list: " + p);
		return (List<Customer>) this.getHibernateTemplate().find(hql,
				p.toArray());
	}*/

	// ʹ�����߶���ʽʵ�ֶ�������ϲ�ѯ(��Ҫʹ��)
	public List<Customer> findMoreCondition(Customer customer) {

		// �������߶���ָ�����ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// �ж�����ֵ�Ƿ�Ϊ��
		if (customer.getCustName() != null
				&& !"".equals(customer.getCustName())) {
			// ���ö����ԣ�����ֵ
			criteria.add(Restrictions.eq("custName", customer.getCustName()));// ��������䣬Ҳ����ֵ
		}
		// if(customer.getCustLevel()!=null &&
		// !"".equals(customer.getCustLevel())) {
		// criteria.add(Restrictions.eq("custLevel", customer.getCustLevel()));
		// }
		if (customer.getCustSource() != null
				&& !"".equals(customer.getCustSource())) {
			criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
		}

		return (List<Customer>) this.getHibernateTemplate().findByCriteria(
				criteria);
	}
}
