package cn.itcast.entity;

//拜访实体类
public class Visit {
	
	private Integer vid;
	private String vaddress;//拜访地址
	private String vcontent;//拜访内容
	
	//表示每个拜访记录所属的用户
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//表示每个拜访记录所属的客户
	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getVaddress() {
		return vaddress;
	}
	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}
	public String getVcontent() {
		return vcontent;
	}
	public void setVcontent(String vcontent) {
		this.vcontent = vcontent;
	}

}
