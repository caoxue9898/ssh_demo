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

	//分页的方法
	public String listpage(){
		//首先要得到当前页是哪一页即currentPage的值
		//模型驱动不可以
		//使用属性封装
		
		//调用service的方法实现封装
		PageBean pageBean = customerService.listpage(currentPage);
		
		//调用方法后得到pageBean对象
		//放到域对象中，传到页面中
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//到查询客户信息的页面
	public String toSelectCustomerPage(){
		return "toSelectCustomerPage";
	}
	
	//多条件查询
	public String moreCondition(){
		//由模型驱动得到表单提交的数据  customer中有这些值
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);//把list集合放在域对象中
		return "moreCondition";
	}
	
	//条件查询的方法
	public String listcondition(){
		//如果输入客户名称，根据客户名称查询
	    //如果不输入任何内容，查询所有
		if(customer.getCustName()!=null&&!"".equals(customer.getCustName())){
			//不为空
			List<Customer> list = customerService.findCondition(customer);
			//把list集合放到域对象中
			ServletActionContext.getRequest().setAttribute("list", list);
		}else{
			//为空 不输入任何内容 查询所有
			//list();//直接调方法
			//List<Customer> list = customerService.findAll();
			list = customerService.findAll();//???
		}
		return "listcondition";
	}
	
	//使用模型驱动的前提-->属性的名字与表单输入项中name的保持一致
	private Customer customer = new Customer();//创建对象
	//使用模型驱动获取表单提交数据
	public Customer getModel() {
		return customer;//返回这个对象
	}
	
	//1 到添加页面
	public String toAddPage(){
		return "toAddPage";
	}
	
	//2 添加的放法
	public String add(){
		//添加逻辑
		customerService.add(customer);
		return "add";
	}
	
	//放入值栈的步骤
	//1-->定义list变量
	private List<Customer> list;
	//2-->生成变量的get方法
	public List<Customer> getList() {
		return list;
	}
	
	//3 客户列表的方法
	public String list(){
		List<Customer> list = customerService.findAll();
		//1-->放到域对象中-->最终传入到页面中
		ServletActionContext.getRequest().setAttribute("list", list);
		
		//2-->返回list放在值栈里面
		//现在list在值栈中
		//list = customerService.findAll();//把数据放入值栈
		return "list";
	}
	
	//4 删除
	public String delete(){
		//使用模型驱动获取表单提交cid的值
		int cid = customer.getCid();
		
		//删除规范写法 先根据id查询 再删除
		//1-->根据id查询
		Customer c = customerService.findOne(cid);
		//2-->调用方法删除
		//判断根据id查询对象是否为空
		if(c!=null){
		customerService.delete(c);}
		return "delete";
	}
	
	//5 修改-->根据id查询
	public String showCustomer(){
		int cid = customer.getCid();//使用模型驱动可以获得cid的值
		Customer c = customerService.findOne(cid);//根据id查询
		//把返回的对象放到域对象中，并传到页面中
		ServletActionContext.getRequest().setAttribute("customer", c);
		return "showCustomer";
	}
	
	//6 修改的方法
	public String update(){
		customerService.update(customer);
		return "update";
	}
}
