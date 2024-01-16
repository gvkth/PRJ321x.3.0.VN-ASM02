package vn.funix.ccdn.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import vn.funix.ccdn.validator.FieldsValueMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldsValueMatch.List({ 
    @FieldsValueMatch(
      field = "password", 
      fieldMatch = "passwordRe", 
      message = "Mật khẩu nhập lại không khớp."
    ), 
    
})

public class UserRegisterDTO {
	@Email(message="Định dạng email không hợp lệ.")
	@NotEmpty(message="email không được để trống.")
	@NotNull(message="email không được để trống.")
	private String email;
	
	@NotNull(message="Tên người dùng không để trống.")
	@NotBlank(message="Tên người dùng không để trống.")
	@NotEmpty(message="Tên người dùng không để trống.")
	@Size(min=3, message="Tên người dùng ít nhất 3 ký tự.")
	private String fullName;
	
	@NotNull
	@Size(min=2,message="Mật khẩu phải nhiều hơn 1 ký tự.")
	private String password;
	
	@NotNull
	@Size(min=2,message="Mật khẩu (lặp lại) phải nhiều hơn 1 ký tự.")
	private String passwordRe;
	
	@NotNull(message="Chưa chọn role.")
	@Digits(integer = 1, fraction = 0,message="Chọn role chưa đúng.")
	@Min(value=1,message="Chọn role chưa đúng.")
	@Max(value=2,message="Chọn role chưa đúng.")
	private int roleId;
	
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordRe() {
		return passwordRe;
	}
	public void setPasswordRe(String passwordRe) {
		this.passwordRe = passwordRe;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserRegisterDTO [email=" + email + ", fullName=" + fullName + ", password=" + password + ", passwordRe="
				+ passwordRe + ", roleId=" + roleId + "]";
	}
	public UserRegisterDTO(String email, String fullName, String password, String passwordRe, int roleId) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.passwordRe = passwordRe;
		this.roleId = roleId;
	}
	public UserRegisterDTO() {
		super();
		this.email = "useremail@something";
		this.fullName = "Họ và tên";
		this.password = "1234@abcD";
		this.passwordRe = "1234@abcD";
		this.roleId = 1;
	}
	
	
}
