package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//�����ϵ�˵ķ���
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	//��ϵ��ҳ��ķ���
	@SuppressWarnings("all")
	public List<LinkMan> list() {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");//��ѯ����
	}

	//����id��ѯ��ϵ��
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}

	//�޸���ϵ�˵ķ���
	public void update(LinkMan linkMan) {
        this.getHibernateTemplate().update(linkMan);//��linkMan�������ҵ�idֵ,���ݶ���ֵ�����޸�
	}

	//��������ϲ�ѯ--hql���ƴ�ӷ�ʽʵ��
//	public List<LinkMan> findCondition(LinkMan linkMan) {
		String hql = "from LinkMan where 1=1 ";
//		List<Object> p = new ArrayList<Object>();
//		//�ж�����ֵ�Ƿ�Ϊ��
//		if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {
//			hql += " and lkmName=?";
//			p.add(linkMan.getLkmName());
//		}
//		//�ж��Ƿ�ѡ��ͻ�
//		if(linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
//			//�жϿͻ�����cidֵ
//			hql += " and customer.cid=?";
//			p.add(linkMan.getCustomer().getCid());
//		}
//		
//		return (List<LinkMan>) this.getHibernateTemplate().find(hql, p.toArray());
//	}
	
		//��������ϲ�ѯ-���߶���ʽʵ��
		@SuppressWarnings("all")
		public List<LinkMan> findCondition(LinkMan linkMan) {
			//�������߶���
			DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
			if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {//��һ������
				criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
			}
			if(linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
				criteria.add(Restrictions.eq("customer.cid", linkMan.getCustomer().getCid()));
			}
			
			return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
		}

}
