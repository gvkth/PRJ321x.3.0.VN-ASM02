package vn.funix.ccdn.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import vn.funix.ccdn.dto.UserRegisterDTO;
import vn.funix.ccdn.service.UserService;
import vn.funix.ccdn.utilities.FlashMemory;

@Controller
@RequestMapping("/auth")
public class AuthenticateController {
	@Autowired
	UserService userService;
	
	@PostMapping(value="/register", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView registerProcess(@Valid @ModelAttribute("user_register_dto") UserRegisterDTO userRegisterDTO,BindingResult theBindingResult,HttpSession session)
	{
		System.out.println("create a new user");
		System.out.println(userRegisterDTO.toString());
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		/*
		 * Check password và passwordRe giống nhau => thay bằng custom class validator FieldsValueMatch
		if(userRegisterDTO.getPassword().equalsIgnoreCase(
				userRegisterDTO.getPasswordRe()
				)==false) {
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "Có lỗi khi tạo mới tài khoản: mật khẩu lặp lại không đúng");
			modelAndView.setViewName("redirect:/auth/register/");
			return  modelAndView;
		}
		*/
		
		if(theBindingResult.hasErrors()) { //invalidate??
			flashMemory.setVar("flash_code", "0");
			String sErrors = genErrorString(theBindingResult);
			System.out.println("CCC-Có lỗi");
			System.out.println(sErrors);
			//flashMemory.setVar("flash_mess", "CCC.Có lỗi khi tạo mới tài khoản: "+sErrors);
			//modelAndView.setViewName("redirect:/auth/register/");
			//modelAndView.addObject("user_register_dto",userRegisterDTO);
			
			modelAndView.setViewName("/public/register");
			//modelAndView.setViewName("/debugger");
			return  modelAndView;
		}
		
		try {
			int createRet = userService.create(userRegisterDTO);
			if(createRet>0) {
				flashMemory.setVar("flash_code", "1");
				flashMemory.setVar("flash_mess", "Đã tạo tài khoản mới thành công");
			}
			else {
				flashMemory.setVar("flash_code", "0");
				flashMemory.setVar("flash_mess", "AAA.Có lỗi khi tạo mới tài khoản."+String.valueOf(createRet));
			}
		} catch (Exception e) {
			// NOTE: doan catch nay khong con tac dung kiem tra email, username
			// chua co trong DB sau khi cai dat custom
			// validator UniqueEmail
			Throwable causeOne = e.getCause();
			String message = "Có lỗi khi thực hiện ("+e.getLocalizedMessage()+")";
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
			
			e.printStackTrace();
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "BBB.Có lỗi khi tạo mới tài khoản. "+message);
		}
		//modelAndView.setViewName("/public/register");
		modelAndView.setViewName("redirect:/auth/register/");
		return  modelAndView;
	}
	
	private String genErrorString(BindingResult bindingResult) {
		// TODO Auto-generated method stub
		System.out.println("genErrorString");
		String sRet="";
		for (Object object : bindingResult.getAllErrors()) {
		    if(object instanceof FieldError) {
		        FieldError fieldError = (FieldError) object;

		        sRet+=fieldError.getDefaultMessage();
		    }

		    else if(object instanceof ObjectError) {
		        ObjectError objectError = (ObjectError) object;

		        sRet+=objectError.getDefaultMessage();
		    }
		}
		System.out.println(sRet);
		return sRet;
	}

	@GetMapping("/register")
	public String register(Model model,HttpSession session) {
		UserRegisterDTO userRegisterDTO=new UserRegisterDTO();
		model.addAttribute("user_register_dto",userRegisterDTO);
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
		
		String flashMess = flashMemory.getVar("flash_mess");
		if(flashMess!=null)
			model.addAttribute("flash_mess",flashMess);
		
		String flashCode = flashMemory.getVar("flash_code");
		if(flashCode!=null) {
			model.addAttribute("flash_code",flashCode);
			if(flashCode=="0")
				model.addAttribute("msg_register_error",flashMess);
			else if (flashCode=="1")
				model.addAttribute("msg_register_success","Đăng ký thành công");
		}
		return "/public/register";
		//return "/debugger.html";
	}
}
