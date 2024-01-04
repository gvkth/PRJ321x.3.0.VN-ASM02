package vn.funix.ccdn.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.funix.ccdn.dao.UserDAOImpl;
import vn.funix.ccdn.entity.CustomUserDetails;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
    private UserDAOImpl userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		final vn.funix.ccdn.entity.User user = userRepository.findByEmail(email);
		if (user == null) {
            throw new UsernameNotFoundException(email);
        }
		/*
		UserDetails user1 = org.springframework.security.core.userdetails.User.withUsername(
				user.getUserName())
				.password(user.getPassword())
				.accountLocked(user.getLocked())
				
				//.authorities("USER")
				.authorities("ROLE_"+user.getRole().getRoleName())
				.build();
		return user1;
				*/
		return new CustomUserDetails(user);
		
        
	}
	
	

}
