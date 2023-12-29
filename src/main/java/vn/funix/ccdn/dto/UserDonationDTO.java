package vn.funix.ccdn.dto;

import vn.funix.ccdn.entity.User;

public class UserDonationDTO {
	private String fullName;
	private Integer money;
	private Integer idDonation;
	private Integer idUser;
	private String text;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getIdDonation() {
		return idDonation;
	}
	public void setIdDonation(Integer idDonation) {
		this.idDonation = idDonation;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "UserDonationDTO [fullName=" + fullName + ", money=" + money + ", idDonation=" + idDonation + ", idUser="
				+ idUser + ", text=" + text + "]";
	}
	public UserDonationDTO() {
		fullName="Dấu tên";
		money=0;
		idDonation=0;
		idUser=1000;
		text="Hãy gửi lời nhắn của bạn đến BTC chương trình";
	}
	
	
	
	
}
