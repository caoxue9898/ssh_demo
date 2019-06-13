package cn.itcast.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//登录的方法-->可以使用模型驱动 属性封装 request对象
	
	//属性封装  属性名字与表单输入项中的名字要一致
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//登录的方法
	public String login(){	
		//封装到实体类对象
		User user = new User();
		user.setUsername(username);//调用其中set方法
		user.setPassword(password);
		User userExist = userService.login(user);
		//判断
		if(userExist!=null){//成功
			//使用session保持登录状态
			HttpServletRequest request = ServletActionContext.getRequest();//首先得到request
			//通过request得到ssession向里面放值
			request.getSession().setAttribute("user",userExist);
			return "loginsuccess";
		}
		else{//失败
			return "login";
		}
	}

}
