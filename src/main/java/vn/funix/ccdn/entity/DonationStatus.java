package vn.funix.ccdn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="donation_status")
public class DonationStatus {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String statusCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public DonationStatus() {
		this.id = 0;
		this.statusCode = "Mới tạo";
	}

	public DonationStatus(int id) {
		super();
		this.id = id;
		if (id==0)
			statusCode="Mới tạo";
		else if(id==1)
			statusCode="Đang quyên góp";
		else if (id==2)
			statusCode = "Kết thúc quyên góp";
		else if (id==3)
			statusCode = "Đóng quyên góp";
	}
	
	
	
	
	
	
}
