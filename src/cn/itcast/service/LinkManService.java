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

	//联系人列表的方法
	public List<LinkMan> listLinkMan() {
		return linkManDao.list();
	}

	//根据id查询联系人
	public LinkMan findOne(int linkid) {
		return linkManDao.findOne(linkid);
	}

	//修改联系人
	public void updateLink(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	public List<LinkMan> findCondition(LinkMan linkMan) {
		return linkManDao.findCondition(linkMan);
	}

}
