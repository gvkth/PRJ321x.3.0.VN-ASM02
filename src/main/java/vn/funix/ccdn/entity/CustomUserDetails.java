package vn.funix.ccdn.entity;

public class CustomUserDetails  extends org.springframework.security.core.userdetails.User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final vn.funix.ccdn.entity.User user;
	
	public CustomUserDetails(vn.funix.ccdn.entity.User user) {
        super(user.getUserName(), user.getPassword(), user.getAuthorities());
        
        this.user = user;
    }
	
    @Override
	public boolean isAccountNonLocked() {
		return !this.user.getLocked();
	}

	public vn.funix.ccdn.entity.User getUser() {
        return user;
    }
}
