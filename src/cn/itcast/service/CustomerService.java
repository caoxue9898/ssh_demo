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

	// ���
	public void add(Customer customer) {
		customerDao.add(customer);
	}

	// ��ѯȫ��
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	// ����id��ѯ
	public Customer findOne(int cid) {
		return customerDao.findOne(cid);
	}

	//ɾ��
	public void delete(Customer c) {
		customerDao.delete(c);
	}

	//�޸�
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	// serviceҵ���߼��� ����дҵ���߼�
	// ��װ��ҳ�����ݵ�pageBean��������
	public PageBean listpage(Integer currentPage) {
		// ����pageBean����
		PageBean pageBean = new PageBean();
		// ��ǰҳ
		pageBean.setCurrentPage(currentPage);

		// �ܼ�¼��
		int totalCount = customerDao.findCount();// �ܼ�¼���ɲ�ѯ�õ�����ѯ��dao�еķ���-->�����Ժ�����
		pageBean.setTotalCount(totalCount);

		// ÿҳ��ʾ�ļ�¼��
		int pageSize = 3;

		// ��ҳ��
		// �ܼ�¼������ÿҳ��ʾ�ļ�¼��
		// �ܹ�����
		int totalPage = 0;
		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}
		pageBean.setTotalPage(totalPage);

		// ��ʼ��λ��
		int begin = (currentPage - 1) * pageSize;

		// ÿҳ��¼��list����
		List<Customer> list = customerDao.findPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//������ѯ
	public List<Customer> findCondition(Customer customer) {
		return customerDao.findCondition(customer);
	}

	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
	}

}
