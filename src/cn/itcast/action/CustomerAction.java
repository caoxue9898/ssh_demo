package cn.itcast.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.Customer;
import cn.itcast.entity.PageBean;
import cn.itcast.service.CustomerService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	//��ҳ�ķ���
	public String listpage(){
		//����Ҫ�õ���ǰҳ����һҳ��currentPage��ֵ
		//ģ������������
		//ʹ�����Է�װ
		
		//����service�ķ���ʵ�ַ�װ
		PageBean pageBean = customerService.listpage(currentPage);
		
		//���÷�����õ�pageBean����
		//�ŵ�������У�����ҳ����
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//����ѯ�ͻ���Ϣ��ҳ��
	public String toSelectCustomerPage(){
		return "toSelectCustomerPage";
	}
	
	//��������ѯ
	public String moreCondition(){
		//��ģ�������õ����ύ������  customer������Щֵ
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);//��list���Ϸ����������
		return "moreCondition";
	}
	
	//������ѯ�ķ���
	public String listcondition(){
		//�������ͻ����ƣ����ݿͻ����Ʋ�ѯ
	    //����������κ����ݣ���ѯ����
		if(customer.getCustName()!=null&&!"".equals(customer.getCustName())){
			//��Ϊ��
			List<Customer> list = customerService.findCondition(customer);
			//��list���Ϸŵ��������
			ServletActionContext.getRequest().setAttribute("list", list);
		}else{
			//Ϊ�� �������κ����� ��ѯ����
			//list();//ֱ�ӵ�����
			//List<Customer> list = customerService.findAll();
			list = customerService.findAll();//???
		}
		return "listcondition";
	}
	
	//ʹ��ģ��������ǰ��-->���Ե����������������name�ı���һ��
	private Customer customer = new Customer();//��������
	//ʹ��ģ��������ȡ���ύ����
	public Customer getModel() {
		return customer;//�����������
	}
	
	//1 �����ҳ��
	public String toAddPage(){
		return "toAddPage";
	}
	
	//2 ��ӵķŷ�
	public String add(){
		//����߼�
		customerService.add(customer);
		return "add";
	}
	
	//����ֵջ�Ĳ���
	//1-->����list����
	private List<Customer> list;
	//2-->���ɱ�����get����
	public List<Customer> getList() {
		return list;
	}
	
	//3 �ͻ��б�ķ���
	public String list(){
		List<Customer> list = customerService.findAll();
		//1-->�ŵ��������-->���մ��뵽ҳ����
		ServletActionContext.getRequest().setAttribute("list", list);
		
		//2-->����list����ֵջ����
		//����list��ֵջ��
		//list = customerService.findAll();//�����ݷ���ֵջ
		return "list";
	}
	
	//4 ɾ��
	public String delete(){
		//ʹ��ģ��������ȡ���ύcid��ֵ
		int cid = customer.getCid();
		
		//ɾ���淶д�� �ȸ���id��ѯ ��ɾ��
		//1-->����id��ѯ
		Customer c = customerService.findOne(cid);
		//2-->���÷���ɾ��
		//�жϸ���id��ѯ�����Ƿ�Ϊ��
		if(c!=null){
		customerService.delete(c);}
		return "delete";
	}
	
	//5 �޸�-->����id��ѯ
	public String showCustomer(){
		int cid = customer.getCid();//ʹ��ģ���������Ի��cid��ֵ
		Customer c = customerService.findOne(cid);//����id��ѯ
		//�ѷ��صĶ���ŵ�������У�������ҳ����
		ServletActionContext.getRequest().setAttribute("customer", c);
		return "showCustomer";
	}
	
	//6 �޸ĵķ���
	public String update(){
		customerService.update(customer);
		return "update";
	}
}
