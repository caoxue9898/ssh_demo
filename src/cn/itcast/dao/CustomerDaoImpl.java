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

	// 添加客户功能
	// 调用hibernate中的save方法进行添加

	/*
	 * public void add(Customer customer) {
	 * this.getHibernateTemplate().save(customer); }
	 */

	@SuppressWarnings("all")
	// 客户列表的功能(查询所有)
	/*
	 * public List<Customer> findAll() { return (List<Customer>)
	 * this.getHibernateTemplate().find( "from Customer"); }
	 */
	// 根据id查询
	/*
	 * public Customer findOne(int cid) { return
	 * this.getHibernateTemplate().get(Customer.class, cid); }
	 */
	// 删除功能
	/*
	 * public void delete(Customer c) { this.getHibernateTemplate().delete(c); }
	 */
	// 修改的功能
	/*
	 * public void update(Customer customer) {
	 * this.getHibernateTemplate().update(customer);//根据id值做修改 }
	 */
	// 查询记录数
	public int findCount() {
		List<Object> list = (List<Object>) this.getHibernateTemplate().find(
				"select count(*)from Customer");
		// 从list中得到我们需要的值
		if (list != null && list.size() != 0) {
			Object obj = list.get(0);
			// 变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	// 分页查询操作
	public List<Customer> findPage(int begin, int pageSize) {

		// 第一种 使用hibernate底层代码实现（了解）
		// 得到sessionFactory
		// SessionFactory sessionFactory =
		// this.getHibernateTemplate().getSessionFactory();
		// 得到session对象
		// Session session = sessionFactory.getCurrentSession();
		// 设置分页信息
		// Query query = session.createQuery("from Customer");
		// query.setFirstResult(begin);
		// query.setMaxResults(pageSize);
		// List<Customer> list = query.list();

		// 第二种 使用离线对象和hibernateTemplate的方法实现
		// 1 创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

		// criteria.setProjection(Projections.rowCount());//利用离线对象实现

		// 调用hibernateTemplate的方法实现
		// 第一个参数是离线对象
		// 第二个参数是开始位置
		// 第三个参数是每页记录数
		List<Customer> list = (List<Customer>) this.getHibernateTemplate()
				.findByCriteria(criteria, begin, pageSize);
		return list;
	}

	// 条件查询
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {
		// 第一种方式：
		// SessionFactory sessionFactory =
		// this.getHibernateTemplate().getSessionFactory();
		// 得到session对象
		// Session session = sessionFactory.getCurrentSession();
		// Query query =
		// session.createQuery("from Customer where custName like ?");
		// query.setParameter(0, "%"+customer.getCustName()+"%");
		// List<Customer> list = query.list();

		// 第二种方式 ： 调用hibernateTemplate的find方法实现
		// List<Customer> list = (List<Customer>) this.getHibernateTemplate().
		// find("from Customer where custName like ?",
		// "%"+customer.getCustName()+"%");//模糊查询 做百分号的拼接
		// 拼接hql语句实现

		// 第三种方式
		// 1 创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 2 设置对实体类哪个属性
		// 第一个参数 属性名
		// 第二个参数 百分号的拼接
		criteria.add(Restrictions.like("custName", "%" + customer.getCustName()
				+ "%"));
		// 3 调用hibernateTemplate里面的方法得到list集合
		List<Customer> list = (List<Customer>) this.getHibernateTemplate()
				.findByCriteria(criteria);// 传入的参数为离线的对象

		return list;
	}

	// 多条件组合查询

	@SuppressWarnings("unchecked")
	/*public List<Customer> findMoreCondition(Customer customer) { //
		// 使用hibernate模板里面find方法实现 // 拼接hql语句
		String hql = "from Customer where 1=1 ";
		// 肯定成立的条件
		// 创建list集合，如果值不为空，把值设置到list里面
		List<Object> p = new ArrayList<Object>();
		// 判断条件值是否为空，如果不为空拼接hql语句
		if (customer.getCustName() != null
				&& !"".equals(customer.getCustName())) {
			// 拼接hql hql += " and custName=?";//注意拼接时的空格 // 把值设置到list里面
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

	// 使用离线对象方式实现多条件组合查询(主要使用)
	public List<Customer> findMoreCondition(Customer customer) {

		// 创建离线对象，指定对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 判断条件值是否为空
		if (customer.getCustName() != null
				&& !"".equals(customer.getCustName())) {
			// 设置对属性，设置值
			criteria.add(Restrictions.eq("custName", customer.getCustName()));// 既配置语句，也设置值
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
