package vn.funix.ccdn.dao;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import vn.funix.ccdn.entity.User;
/*
public class CustomUserDetails implements UserDetails {
	
	private User user;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = "ROLE_" + user.getRole().getRoleName();
		System.out.println("Role----------------"+role);
		return null;
	}
	
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
*/