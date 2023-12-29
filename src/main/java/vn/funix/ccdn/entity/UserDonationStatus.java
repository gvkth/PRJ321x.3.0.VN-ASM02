package vn.funix.ccdn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_donation_status")
public class UserDonationStatus {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String statusCode;

	public int getId() {
		return id;
	}
	
	public static final int STATUS_CHOXACNHAN = 0;
	public static final int STATUS_DAQUYENGOP = 1;
	public static final int STATUS_KHONGXACDINH = 2;

	public void setId(int id) {
		this.id = id;
		if (id==0)
			statusCode="Chờ xác nhận";
		else if(id==1)
			statusCode="Đã quyên góp";
		else {
			statusCode="Không xác định";
		}
	}

	public String getStatusCode() {
		return statusCode;
	}

	

	public UserDonationStatus(int id) {
		super();
		setId(id);
	}

	public UserDonationStatus() {
		this.id = 0;
		this.statusCode  = "Chờ xác nhận";
	}
	
	
	
	
}
