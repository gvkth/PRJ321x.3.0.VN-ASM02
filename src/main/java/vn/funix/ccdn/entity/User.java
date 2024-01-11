package vn.funix.ccdn.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.funix.ccdn.dto.UserDTO;
import vn.funix.ccdn.dto.UserRegisterDTO;
import vn.funix.ccdn.utilities.Utility;

@Entity
@Table(name="user")
public class User{
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="note")
	private String note;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="status")
	private int status;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="created")
	private String createdAt;
	
	@Column(name="deleted")
	private int deleted;
	

	
	public boolean getLocked() {
		return status==0;
	}




	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToMany(mappedBy = "userMakeADonate")
	private List<UserDonation> userDonations;
	
	
	

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




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<GrantedAuthority> getAuthorities(){
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+this.getRole().getRoleName()));
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




	public void setCreatedAt(String created) {
		this.createdAt = created;
	}




	public Role getRole() {
		return role;
	}


	public Integer getRoleId() {
		if(role!=null)
			return role.getId();
		return null;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setRoleByRoleID(int roleId) {
		this.role=new Role(roleId);
	}
	
	

	




	public int getDeleted() {
		return deleted;
	}




	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}




	public User(String address, String email, String fullName, String note, String password, String phoneNumber,
			int status, String userName, String created, Role role) {
		super();
		this.address = address;
		this.email = email;
		this.fullName = fullName;
		this.note = note;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.userName = userName;
		this.createdAt = created;
		this.role = role;
		this.deleted = 0;
	}

	
	public User(UserRegisterDTO theNewUserRegister) {
		
		super();
		
		System.out.println("create by UserRegister");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		this.address = "address";
		this.email = theNewUserRegister.getEmail();
		this.fullName = theNewUserRegister.getFullName();
		this.note = "note";
		this.password = "{bcrypt}"+bCryptPasswordEncoder.encode(theNewUserRegister.getPassword());
		
		this.phoneNumber = "";
		this.status = 1 ;
		this.userName = theNewUserRegister.getEmail();
		this.createdAt = Utility.getCurrentTimeString();
		this.role = new Role(theNewUserRegister.getRoleId());
		this.deleted = 0;
	}



	public User() {
		super();
		this.address = "Địa chỉ người dùng";
		this.email = "your.email@google.com";
		this.fullName  =  "Tên đầy đủ của người dùng";
		this.note = "Thông tin ghi chú của người dùng";
		this.phoneNumber = "Số điện thoại";
		this.status = 1;
		this.userName = "Tên đăng nhập";
		this.password = "test123";
		this.createdAt = Utility.getCurrentTimeString();
		this.role = new Role(Role.ROLE_RECRUITER);
		this.deleted = 0;
				
		
	}

	public UserDTO getDTO() {
		return new UserDTO(
				id, address, email, fullName, note, 
				phoneNumber, status, userName, 
				createdAt, getLocked(), id);
	}




	@Override
	public String toString() {
		String ret = "User [id=" + id + ", address=" + address + ", email=" + email + ", fullName=" + fullName + ", note="
				+ note + ", password=" + password + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", userName=" + userName + ", createdAt=" + createdAt + ", deleted=" + deleted + ", " ;
		if(role!=null) {
			ret+=", role="+role.getRoleName();
		}
		return ret;
	}


	
	
	
}
