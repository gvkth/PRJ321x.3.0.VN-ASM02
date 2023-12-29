package vn.funix.ccdn.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.funix.ccdn.dto.UserDTO;
import vn.funix.ccdn.entity.CustomUserDetails;
import vn.funix.ccdn.entity.User;
import vn.funix.ccdn.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public void  showHome(HttpServletRequest request,HttpServletResponse response) {
		
		
		try {
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 System.out.println("username: "+auth.getName());
			 response.sendRedirect("index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GetMapping("/index")
	public String showIndex(Model model,Authentication authentication) {
		if(authentication!=null) {
			org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
			
			
			if (userDetails!=null) {
				CustomUserDetails userCustomDetails = (CustomUserDetails)userDetails;
				User userInPage = userCustomDetails.getUser();
				model.addAttribute("user_inpage",userInPage);
				//userDonationDTO.setFullName(userInPage.getFullName());
			}
		} else {
			User anonymousUser = userService.getAnonymousUser();
			model.addAttribute("user_inpage",anonymousUser);
			//userDonationDTO.setFullName(anonymousUser.getFullName());
		}
		model.addAttribute("msg_register_success","1");
		model.addAttribute("numberCandidate",1011);
		model.addAttribute("numberCompany",101);
		model.addAttribute("numberRecruitment",99);
		return "public/home";
		//return "debugger";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/public/login.html";
		//return "/debugger.html";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "/public/access-denied";
	}
	
	@GetMapping("/user/info/{userId}")
	public String showUserInfo(@PathVariable Integer userId,Model model,Authentication authentication) {
		if(authentication!=null) {
			org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
			
			
			if (userDetails!=null) {
				CustomUserDetails userCustomDetails = (CustomUserDetails)userDetails;
				User userInPage = userCustomDetails.getUser();
				model.addAttribute("user_inpage",userInPage);
				//userDonationDTO.setFullName(userInPage.getFullName());
			}
		} else {
			User anonymousUser = userService.getAnonymousUser();
			model.addAttribute("user_inpage",anonymousUser);
			//userDonationDTO.setFullName(anonymousUser.getFullName());
		}
		
		User user = userService.get(userId);
		if(user!=null) {
			UserDTO userDTO = user.getDTO();
			model.addAttribute("userDTO",userDTO);
		}
		return "/public/user-info";
	}
}
