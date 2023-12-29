package vn.funix.ccdn.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import vn.funix.ccdn.dto.DonationDTO;
import vn.funix.ccdn.dto.UserDTO;
import vn.funix.ccdn.utilities.Utility;

@Entity
@Table(name="donation")
public class Donation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="created")
	private String created;
	
	@Column(name="description")
	private String description;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="money")
	private int money;
	
	@Column(name="name")
	private String name;
	
	@Column(name="organization_name")
	private String organizationName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="start_date")
	private String startDate;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private DonationStatus statusDonation;
	
	@ManyToMany(fetch=FetchType.EAGER,
			mappedBy = "donationBeDonated",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH}
			)
	private List<UserDonation> users;
	
	/*
	 * Tính tổng số tiền quyên góp có trạng thái đã chuyển, và chốt vào field money
	 * */
	public int reCalculateMoney() {
		int ret = 0;
		for (UserDonation userDonation : users) {
			if(userDonation.getStatusID()==UserDonationStatus.STATUS_DAQUYENGOP)
				ret+=userDonation.getMoney();
		}
		money = ret;
		return ret;
	}
	
	/*
	 * Tính tất cả số tiền quyên góp không cần quan tâm trạng thái đã chuyển hay chưa
	 * */
	public int calculateMoneyAllStatus() {
		int ret=0;
		for (UserDonation userDonation : users) {
			ret+=userDonation.getMoney();
		}
		money = ret;
		return ret;
	}

	

	public List<UserDonation> getUsers() {
		return users;
	}

	public void setUsers(List<UserDonation> users) {
		this.users = users;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	

	public DonationStatus getStatusDonation() {
		return statusDonation;
	}
	
	public String getStatus() {
		if(statusDonation!=null)
			return statusDonation.getStatusCode();
		return "Không xác định";
	}
	
	public int getStatusID() {
		if(statusDonation!=null)
			return statusDonation.getId();
		return -1;
	}
	
	

	public Donation() {
		super();
		this.statusDonation=new DonationStatus();
		this.description = "Dự án mới";
		this.startDate = Utility.getCurrentTimeString();
		this.endDate = Utility.getNextMonthDateString();
		this.organizationName = "Tên tổ chức mới";
		this.phoneNumber = "0911231231";
		this.code="DAxxx";
		this.name = "Tên đợt quyên góp mới";
	}

	public void setStatusDonation(DonationStatus statusDonation) {
		this.statusDonation = statusDonation;
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", code=" + code + ", created=" + created + ", description=" + description
				+ ", endDate=" + endDate + ", money=" + money + ", name=" + name + ", organizationName="
				+ organizationName + ", phoneNumber=" + phoneNumber + ", startDate=" + startDate + ", status=" + statusDonation.getStatusCode()
				+ "]";
	}

	
	public DonationDTO getDTO() {

		return new DonationDTO(
				id, code, created, description, endDate, name, 
				organizationName, phoneNumber, startDate, 
				statusDonation.getStatusCode(), 
				statusDonation.getId(), 
				this.reCalculateMoney(), 
				this.calculateMoneyAllStatus());
	}
    
    
	
}
