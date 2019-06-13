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

	//��¼�ķ���-->����ʹ��ģ������ ���Է�װ request����
	
	//���Է�װ  ������������������е�����Ҫһ��
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

	//��¼�ķ���
	public String login(){	
		//��װ��ʵ�������
		User user = new User();
		user.setUsername(username);//��������set����
		user.setPassword(password);
		User userExist = userService.login(user);
		//�ж�
		if(userExist!=null){//�ɹ�
			//ʹ��session���ֵ�¼״̬
			HttpServletRequest request = ServletActionContext.getRequest();//���ȵõ�request
			//ͨ��request�õ�ssession�������ֵ
			request.getSession().setAttribute("user",userExist);
			return "loginsuccess";
		}
		else{//ʧ��
			return "login";
		}
	}

}
