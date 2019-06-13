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
	
	//ģ��������װ
	private LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}
	
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//��һ��action���п���ע��������
	//ע��ͻ���service����//Ҳ����ֱ��new CustomerService�Ķ���
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//��������ѯ
		public String moreCondition() {
			//���÷����õ��������
			List<LinkMan> list = linkManService.findCondition(linkMan);//�����ж�����¼
			ServletActionContext.getRequest().setAttribute("list", list);
			return "moreCondition";
		}
	
	//����ϵ�����ҳ��
	public String toSelectPage(){
		//��ҳ����Ҫ�õ����еĿͻ�
		//��ѯ���пͻ��������Ǵ��ݵ�ҳ�������б���
		List<Customer> list = customerService.findAll();//customerService�Ѿ�ע�� ֱ�ӵ���
		//��list�ŵ��������Ȼ�󴫵�ҳ����
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectPage";
	}
	
	//5 �޸���ϵ��
	public String updateLinkMan(){
		linkManService.updateLink(linkMan);
		return "updateLinkMan";
	}
	
	//4 ���޸���ϵ��ҳ��ķ���
	public String showLinkMan(){
		//ʹ��ģ������ֱ�ӵõ�linkidֵ
		int linkid = linkMan.getLinkid();
		//����id��ѯ��ϵ�˶���
		LinkMan link = linkManService.findOne(linkid);//1-->�����ϵ�˶���
		
		//��Ҫ���пͻ���list����
		List<Customer> listCustomer = customerService.findAll();
		
		//����ϵ����Ϣ���մ��ݵ�ҳ����-->��Ҫ�ŵ��������
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", link);
		request.setAttribute("listCustomer", listCustomer);//2-->���пͻ���list
		
		return "showLinkMan";
	}
	
	//3 ��ϵ��ҳ��ķ���
	public String list(){
		List<LinkMan> list = linkManService.listLinkMan();
		//�ŵ��������
		ServletActionContext.getRequest().setAttribute("list", list);//��list�ŵ�ҳ����ȥ  ʹ�������
		return "list";
	}
	
	//����Ϊ�õ��ϴ��ļ�����Ϣ
	/*
	 * ��Ҫ�ϴ��ļ�������
	 * ��Ҫ�ϴ��ļ�����
	 * ��1����action�����Ա����λ�ö�������������淶��  ��Ա����-->����������
	 * - һ����ʾ�ϴ��ļ�
	 * - һ����ʾ�ļ�����
	 * ��2�����ɱ�����get��set����
	 * 
	 * ����һ���������ϴ��ļ���mime����
	 * mimeָ     �÷�����ʶ���ļ���չ��
	 * */
	//1 �ϴ��ļ�
	//������������Ҫ�Ǳ������ļ��ϴ����nameֵ
	private File upload;
	
	//2 �ϴ��ļ�����  �������ļ��ϴ����nameֵFileName
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

	//2 ������ݵ����ݿ�ķ���
	public String addLinkMan() throws Exception{
		
		//�ж��Ƿ���Ҫ�ϴ��ļ�
		if(upload!=null){
			//д�ϴ�����
			//�ڷ��������ļ����ﴴ���ļ�
			File serverFile = new File("E:\\sshimg"+"/"+uploadFileName);//ת��
			//���ϴ����ļ����Ƶ�����������
			FileUtils.copyFile(upload, serverFile);//��ʲôʲô���Ƶ�ʲôʲô �����ļ� �������ļ�
		}
		
		//Ҫ��action�õ�add.jsp�б��ύ������-->����ʹ��ģ�����������Է�װ
		//����ʹ��ģ������
		/*
		 * ���Է�װ��ϵ�˻�����Ϣ
		 * ������cid�ǿͻ�idֵ����ֱ�ӷ�װ��
		 * ��cid��װLinkManʵ��������customer��������
		 * 
		 * */
		/*//ԭʼ��ʽʵ��
		//���ȵõ�cidֵ
		String scid = ServletActionContext.getRequest().getParameter("cid");
		int cid = Integer.parseInt(scid);
		
		//����customer����
		Customer c = new Customer();
		c.setCid(cid);//��cid�ŵ�customer������
		linkMan.setCustomer(c);//��customer����ŵ�linkMan��
*/		
		
		//���÷��������ݼӵ����ݿ���
		linkManService.addLinkMan(linkMan);//��ʱ�����Ѿ�����װ��linkMan��
		return "addLinkMan";
	}

	//1 ��������ϵ��ҳ��ķ���
	public String toAddPage(){
		//1.1 ��ѯ���пͻ��������пͻ�list���ϴ��ݵ�ҳ������ʾ���ŵ������
	    //���ÿͻ�service����ķ����õ����пͻ�-->������дһ�������ǵ���֮ǰд���ĸ���
		List<Customer> listCustomer = customerService.findAll();//Ҫ�����list���ϴ���ҳ����ȥ�����ȷŵ��������
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
}
