package vn.funix.ccdn.dto;

import vn.funix.ccdn.entity.User;

public class UserDTO {
	private int id;
	private String address;
	private String email;
	private String fullName;
	private String note;
	private String phoneNumber;
	private int status;
	private String userName;
	private String createdAt;
	private boolean isLocked;
	private int roleId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public UserDTO(int id, String address, String email, String fullName, String note, String phoneNumber, int status,
			String userName, String createdAt, boolean isLocked, int roleId) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.fullName = fullName;
		this.note = note;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.userName = userName;
		this.createdAt = createdAt;
		this.isLocked = isLocked;
		this.roleId = roleId;
	}
	
	
	
}
