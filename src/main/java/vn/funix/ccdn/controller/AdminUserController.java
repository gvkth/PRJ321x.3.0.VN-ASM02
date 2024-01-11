/**
 * Class AdminUserController
 * Controller cho các trang quản trị về tài khoản người dùng
 */
package vn.funix.ccdn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import vn.funix.ccdn.entity.Role;
import vn.funix.ccdn.entity.User;
import vn.funix.ccdn.service.RoleService;
import vn.funix.ccdn.service.UserService;
import vn.funix.ccdn.utilities.FlashMemory;
import vn.funix.ccdn.utilities.Utility;

@Controller
@RequestMapping("/admin/account")
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/getAll_")
	public String listAll_(Model model) {
		List<User> users = userService.getUsers(0,100);
		model.addAttribute("users",users);
		
		
		//return "admin/account";
		return "debugger.html";
	}
	
	@GetMapping({"/",""})
	public String listAll(Model model,HttpSession session) {
		//List<User> users = userService.getAllUsers();
		List<User> users = userService.getAllUsersNotDump();
		User new_user = new User();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
		List<Role> roles = roleService.getAll();
		int pageSize_DT = getIntProperty("frontend.paging.qluser");
		model.addAttribute("users",users);
		model.addAttribute("new_user",new_user);
		model.addAttribute("roleList",roles);
		model.addAttribute("dataTable_init_pagesize",pageSize_DT);
		
		String flashMess = flashMemory.getVar("flash_mess");
		if(flashMess!=null)
			model.addAttribute("flash_mess",flashMess);
		
		String flashCode = flashMemory.getVar("flash_code");
		if(flashCode!=null)
			model.addAttribute("flash_code",flashCode);
		
		return "admin/account";
		//return "admin/test";
	}
	
	@PostMapping(value="/new", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView createAccount(@ModelAttribute("new_user") User theNewUser,HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		theNewUser.setCreatedAt(Utility.getCurrentTimeString());
		String encodedPassword = "{bcrypt}"+bCryptPasswordEncoder.encode(theNewUser.getPassword());
		theNewUser.setPassword(encodedPassword);
		try {
			int id = userService.create(theNewUser);
			flashMemory.setVar("flash_code", "1");
			flashMemory.setVar("flash_mess", "Đã tạo mới account "+theNewUser.getFullName()+" thành công");
		}
		catch(Exception  e) {
			//System.out.println("Exception================");
			//e.printStackTrace();
			Throwable causeOne = e.getCause();
			String message = "Có lỗi khi thực hiện ("+e.getLocalizedMessage()+")";
			System.out.println(message);
			if(causeOne instanceof MySQLIntegrityConstraintViolationException) {
				MySQLIntegrityConstraintViolationException constraintViolationException = (MySQLIntegrityConstraintViolationException) causeOne;
				//System.out.println("MySQLIntegrityConstraintViolationException");
		        String constraintMessage = constraintViolationException.getMessage();
		        
		        //System.out.println(constraintMessage);
		        if(constraintMessage.contains("email_unique"))
		        	message = "Email đã tồn tại";
		        else if (constraintMessage.contains("user_name_unique"))
		        	message = "Username đã tồn tại";			
			}
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "Có lỗi khi tạo mới account: "+ message);
			
		}
		modelAndView.setViewName("redirect:/admin/account/");
		return  modelAndView;
	}
	
	private static String ensureUTF8(String data) {
		return Utility.ensureUTF8(data);
	}
	
	@PostMapping(value="/update", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView updateAccount(@ModelAttribute("user") User theUser,HttpSession session,
			@RequestParam(name="idRole") int roleId
			){
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		System.out.println(theUser.toString());
		
		User currentUser = userService.get(theUser.getId());
		if(currentUser!=null) {
			System.out.println("currentUser-ori");
			System.out.println(currentUser.toString());
			try {
				currentUser.setFullName(ensureUTF8(theUser.getFullName()));
				currentUser.setPhoneNumber(theUser.getPhoneNumber());
				currentUser.setAddress(ensureUTF8(theUser.getAddress()));
				
				if((roleId==Role.ROLE_CANDIDATE) || (roleId==Role.ROLE_RECRUITER)
						&& (currentUser.getRoleId()!=null && (new Integer(roleId))!=currentUser.getRoleId()))
				currentUser.setRoleByRoleID(roleId);
				System.out.println("currentUser-updated");
				System.out.println(currentUser.toString());
				
				userService.save(currentUser);	
				
				
				flashMemory.setVar("flash_code", "1");
				flashMemory.setVar("flash_mess", "Đã lưu account "+currentUser.getFullName()+" thành công");
			}
			catch(Exception e) {
				e.printStackTrace();
				flashMemory.setVar("flash_code", "0");
				flashMemory.setVar("flash_mess", "Có lỗi khi lưu account: "+ e.getMessage());
			}
		}
		modelAndView.setViewName("redirect:/admin/account/");
		return  modelAndView;
	}
	
	@PostMapping(value="/delete", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView deleteAccount( @RequestParam(value ="id", required = false) Integer idUser,HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		User theUser = userService.get(idUser);
		if(theUser!=null) {
			theUser.setDeleted(1);
			try {
				userService.save(theUser);
				flashMemory.setVar("flash_code", "1");
				flashMemory.setVar("flash_mess", "Đã xóa account "+theUser.getFullName()+" thành công");
			}
			catch(Exception e) {
				flashMemory.setVar("flash_code", "0");
				flashMemory.setVar("flash_mess", "Có lỗi khi xóa account: "+ e.getMessage());
			}
		}
		else {
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "Không tìm thầy account cần xóa");
		}
		modelAndView.setViewName("redirect:/admin/account/");
		return  modelAndView;
	}
	
	@PostMapping(value="/lock", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView lockAccount( @RequestParam(value ="id", required = false) Integer idUser,HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		User theUser = userService.get(idUser);
		if(theUser!=null) {
			theUser.setStatus(0);
			try {
				userService.save(theUser);
				flashMemory.setVar("flash_code", "1");
				flashMemory.setVar("flash_mess", "Đã khóa account "+theUser.getFullName()+" thành công");
			}
			catch(Exception e) {
				flashMemory.setVar("flash_code", "0");
				flashMemory.setVar("flash_mess", "Có lỗi khi khóa account: "+ e.getMessage());
			}
		}
		else {
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "Không tìm thầy account cần khóa");
		}
		modelAndView.setViewName("redirect:/admin/account/");
		return  modelAndView;
	}
	
	@PostMapping(value="/unlock", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView unLockAccount( @RequestParam(value ="id", required = false) Integer idUser,HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		User theUser = userService.get(idUser);
		if(theUser!=null) {
			theUser.setStatus(1);
			try {
				userService.save(theUser);
				flashMemory.setVar("flash_code", "1");
				flashMemory.setVar("flash_mess", "Đã mở khóa account "+theUser.getFullName()+" thành công");
			}
			catch(Exception e) {
				flashMemory.setVar("flash_code", "0");
				flashMemory.setVar("flash_mess", "Có lỗi khi mở khóa account: "+ e.getMessage());
			}
		}
		else {
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "Không tìm thầy account cần mở khóa");
		}
		modelAndView.setViewName("redirect:/admin/account/");
		return  modelAndView;
	}
	
	@PostMapping(value="/savetest/{id}", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView updateAccountTest(@PathVariable("id") Long id,
			@ModelAttribute("entity") User theUser,
			HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		System.out.println("==========************+++++++++++");
		System.out.println(theUser.toString());
		System.out.println(id);
		
		
		modelAndView.setViewName("redirect:/admin/account/");
		return  modelAndView;
	}

	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
}
