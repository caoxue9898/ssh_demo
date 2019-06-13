package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import cn.itcast.entity.PageBean;

@Transactional
public class CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	// 添加
	public void add(Customer customer) {
		customerDao.add(customer);
	}

	// 查询全部
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	// 根据id查询
	public Customer findOne(int cid) {
		return customerDao.findOne(cid);
	}

	//删除
	public void delete(Customer c) {
		customerDao.delete(c);
	}

	//修改
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	// service业务逻辑层 负责写业务逻辑
	// 封装分页的数据到pageBean对象里面
	public PageBean listpage(Integer currentPage) {
		// 创建pageBean对象
		PageBean pageBean = new PageBean();
		// 当前页
		pageBean.setCurrentPage(currentPage);

		// 总记录数
		int totalCount = customerDao.findCount();// 总记录数由查询得到，查询调dao中的方法-->方法以后完善
		pageBean.setTotalCount(totalCount);

		// 每页显示的记录数
		int pageSize = 3;

		// 总页数
		// 总记录数除以每页显示的记录数
		// 能够整除
		int totalPage = 0;
		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);

		// 开始的位置
		int begin = (currentPage - 1) * pageSize;

		// 每页记录的list集合
		List<Customer> list = customerDao.findPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//条件查询
	public List<Customer> findCondition(Customer customer) {
		return customerDao.findCondition(customer);
	}

	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
	}

}
