package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.User;

@Transactional
public class UserService {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//��¼�ķ���
	public User login(User user) {
		//����dao�еķ���
		return userDao.loginUser(user);
	}

	//��ѯ�����û��ķ���
	public List<User> findAll() {
		return userDao.findAll();
	}
	
}
