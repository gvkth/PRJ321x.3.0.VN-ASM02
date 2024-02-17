package vn.funix.ccdn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="description")
	private String description;
	
	@Column(name="email")
	private String email;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="name_company")
	private String nameCompany;
	
	@Column(name="phone_number")
	private String nameNumber;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="user_id")
	private Integer userId;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLogo() {
		return logo;
	}



	public void setLogo(String logo) {
		this.logo = logo;
	}



	public String getNameCompany() {
		return nameCompany;
	}



	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}



	public String getNameNumber() {
		return nameNumber;
	}



	public void setNameNumber(String nameNumber) {
		this.nameNumber = nameNumber;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "Company [id=" + id + ", address=" + address + ", description=" + description + ", email=" + email
				+ ", logo=" + logo + ", nameCompany=" + nameCompany + ", nameNumber=" + nameNumber + ", status="
				+ status + ", userId=" + userId + "]";
	}
	
	
}
