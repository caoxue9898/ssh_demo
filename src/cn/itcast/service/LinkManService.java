package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;

@Transactional
public class LinkManService {
	
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void addLinkMan(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	//��ϵ���б�ķ���
	public List<LinkMan> listLinkMan() {
		return linkManDao.list();
	}

	//����id��ѯ��ϵ��
	public LinkMan findOne(int linkid) {
		return linkManDao.findOne(linkid);
	}

	//�޸���ϵ��
	public void updateLink(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	public List<LinkMan> findCondition(LinkMan linkMan) {
		return linkManDao.findCondition(linkMan);
	}

}
