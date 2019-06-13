package cn.itcast.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	//模型驱动封装
	private LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}
	
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//在一个action类中可以注入多个对象
	//注入客户的service对象//也可以直接new CustomerService的对象
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//多条件查询
		public String moreCondition() {
			//调用方法得到条件结果
			List<LinkMan> list = linkManService.findCondition(linkMan);//可能有多条记录
			ServletActionContext.getRequest().setAttribute("list", list);
			return "moreCondition";
		}
	
	//到联系人添加页面
	public String toSelectPage(){
		//在页面中要得到所有的客户
		//查询所有客户，把他们传递到页面下拉列表中
		List<Customer> list = customerService.findAll();//customerService已经注入 直接调用
		//把list放到域对象中然后传到页面中
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectPage";
	}
	
	//5 修改联系人
	public String updateLinkMan(){
		linkManService.updateLink(linkMan);
		return "updateLinkMan";
	}
	
	//4 到修改联系人页面的方法
	public String showLinkMan(){
		//使用模型驱动直接得到linkid值
		int linkid = linkMan.getLinkid();
		//根据id查询联系人对象
		LinkMan link = linkManService.findOne(linkid);//1-->查出联系人对象
		
		//需要所有客户的list集合
		List<Customer> listCustomer = customerService.findAll();
		
		//把联系人信息最终传递到页面中-->需要放到域对象中
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", link);
		request.setAttribute("listCustomer", listCustomer);//2-->所有客户的list
		
		return "showLinkMan";
	}
	
	//3 联系人页表的方法
	public String list(){
		List<LinkMan> list = linkManService.listLinkMan();
		//放到域对象中
		ServletActionContext.getRequest().setAttribute("list", list);//把list放到页面中去  使用域对象
		return "list";
	}
	
	//以下为得到上传文件的信息
	/*
	 * 需要上传文件（流）
	 * 需要上传文件名称
	 * （1）在action里面成员变量位置定义变量（命名规范）  成员变量-->方法的外面
	 * - 一个表示上传文件
	 * - 一个表示文件名称
	 * （2）生成变量的get和set方法
	 * 
	 * 还有一个变量，上传文件的mime类型
	 * mime指     让服务区识别文件扩展名
	 * */
	//1 上传文件
	//变量的名称需要是表单里面文件上传项的name值
	private File upload;
	
	//2 上传文件名称  表单里面文件上传项的name值FileName
	private String uploadFileName;
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	//2 添加数据到数据库的方法
	public String addLinkMan() throws Exception{
		
		//判断是否需要上传文件
		if(upload!=null){
			//写上传代码
			//在服务器的文件夹里创建文件
			File serverFile = new File("E:\\sshimg"+"/"+uploadFileName);//转义
			//把上传的文件复制到服务器里面
			FileUtils.copyFile(upload, serverFile);//把什么什么复制到什么什么 本地文件 服务器文件
		}
		
		//要在action得到add.jsp中表单提交的数据-->可以使用模型驱动或属性封装
		//这里使用模型驱动
		/*
		 * 可以封装联系人基本信息
		 * 但是有cid是客户id值不能直接封装的
		 * 把cid封装LinkMan实体类里面customer对象里面
		 * 
		 * */
		/*//原始方式实现
		//首先得到cid值
		String scid = ServletActionContext.getRequest().getParameter("cid");
		int cid = Integer.parseInt(scid);
		
		//创建customer对象
		Customer c = new Customer();
		c.setCid(cid);//把cid放到customer对象中
		linkMan.setCustomer(c);//把customer对象放到linkMan中
*/		
		
		//调用方法把数据加到数据库中
		linkManService.addLinkMan(linkMan);//此时数据已经到封装到linkMan中
		return "addLinkMan";
	}

	//1 到新增联系人页面的方法
	public String toAddPage(){
		//1.1 查询所有客户，把所有客户list集合传递到页面中显示（放到域对象）
	    //调用客户service里面的方法得到所有客户-->可以再写一个，但是调用之前写过的更简单
		List<Customer> listCustomer = customerService.findAll();//要把这个list集合传到页面中去，首先放到域对象中
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
}
