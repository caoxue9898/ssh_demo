package cn.itcast.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.Customer;
import cn.itcast.entity.User;
import cn.itcast.entity.Visit;
import cn.itcast.service.CustomerService;
import cn.itcast.service.UserService;
import cn.itcast.service.VisitService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	
	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	//ע��ͻ���service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//ע���û���service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//3 �ݷ��б�ķ���
	public String list(){
		//ֱ�ӵ�������ѯ
		List<Visit> list = visitService.findAll();
		//�õ�list��list����ҳ����ȥ
		//�ŵ��������
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//2 ��������
	public String addVisit(){
		//��������ģ��������ȡ��add.jsp���ύ������
		//���÷��������ݼӵ����ݿ���
		visitService.addVisit(visit);
		return "addVisit";
	}

	//1 ������ҳ��
	public String toAddPage(){
		//��ѯ���пͻ�
		List<Customer> listCustomer = customerService.findAll();
		
		//��ѯ�����û�
		List<User> listUser = userService.findAll();
		
		//������ֵ�ŵ�������У����մ���ҳ����-->Ҳ���Էֲ���д
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		ServletActionContext.getRequest().setAttribute("listUser", listUser);
		return "toAddPage";
	}
}
