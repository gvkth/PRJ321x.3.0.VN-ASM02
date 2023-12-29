package vn.funix.ccdn.dto;

public class DonationDTO {
	private int id;
	private String code;
	private String created;
	private String description;
	private String endDate;
	private String name;
	private String organizationName;
	private String phoneNumber;
	private String startDate;
	private String donationStatusCode;
	private int donationStatusId;
	private int moneyTotalConfirmed;
	private int moneyTotal;
	
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
	public String getDonationStatusCode() {
		return donationStatusCode;
	}
	public void setDonationStatusCode(String donationStatusCode) {
		this.donationStatusCode = donationStatusCode;
	}
	public int getDonationStatusId() {
		return donationStatusId;
	}
	public void setDonationStatusId(int donationStatusId) {
		this.donationStatusId = donationStatusId;
	}
	public int getMoneyTotalConfirmed() {
		return moneyTotalConfirmed;
	}
	public void setMoneyTotalConfirmed(int moneyTotalConfirmed) {
		this.moneyTotalConfirmed = moneyTotalConfirmed;
	}
	public int getMoneyTotal() {
		return moneyTotal;
	}
	public void setMoneyTotal(int moneyTotal) {
		this.moneyTotal = moneyTotal;
	}
	public DonationDTO(int id, String code, String created, String description, String endDate, String name,
			String organizationName, String phoneNumber, String startDate, String donationStatusCode,
			int donationStatusId, int moneyTotalConfirmed, int moneyTotal) {
		super();
		this.id = id;
		this.code = code;
		this.created = created;
		this.description = description;
		this.endDate = endDate;
		this.name = name;
		this.organizationName = organizationName;
		this.phoneNumber = phoneNumber;
		this.startDate = startDate;
		this.donationStatusCode = donationStatusCode;
		this.donationStatusId = donationStatusId;
		this.moneyTotalConfirmed = moneyTotalConfirmed;
		this.moneyTotal = moneyTotal;
	}
	
	
	
}
