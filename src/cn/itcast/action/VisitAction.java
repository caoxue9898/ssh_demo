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
	
	//注入客户的service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//注入用户的service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//3 拜访列表的方法
	public String list(){
		//直接调方法查询
		List<Visit> list = visitService.findAll();
		//得到list把list传到页面中去
		//放到域对象中
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//2 新增方法
	public String addVisit(){
		//首先利用模型驱动获取表单add.jsp中提交的数据
		//调用方法把数据加到数据库中
		visitService.addVisit(visit);
		return "addVisit";
	}

	//1 到新增页面
	public String toAddPage(){
		//查询所有客户
		List<Customer> listCustomer = customerService.findAll();
		
		//查询所有用户
		List<User> listUser = userService.findAll();
		
		//这两个值放到域对象中，最终传到页面中-->也可以分步来写
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		ServletActionContext.getRequest().setAttribute("listUser", listUser);
		return "toAddPage";
	}
}
