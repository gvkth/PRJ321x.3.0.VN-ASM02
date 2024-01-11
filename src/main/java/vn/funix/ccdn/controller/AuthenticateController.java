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

import vn.funix.ccdn.dto.UserRegisterDTO;
import vn.funix.ccdn.service.UserService;
import vn.funix.ccdn.utilities.FlashMemory;

@Controller
@RequestMapping("/auth")
public class AuthenticateController {
	@Autowired
	UserService userService;
	
	@PostMapping(value="/register_process", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView registerProcess(@Valid @ModelAttribute("user_register") UserRegisterDTO userRegisterDTO,BindingResult theBindingResult,HttpSession session)
	{
		System.out.println("create a new user");
		System.out.println(userRegisterDTO.toString());
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		if(userRegisterDTO.getPassword().equalsIgnoreCase(
				userRegisterDTO.getPasswordRe()
				)==false) {
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "Có lỗi khi tạo mới tài khoản: mật khẩu lặp lại không khớp");
			
		}
		if(theBindingResult.hasErrors()) {
			flashMemory.setVar("flash_code", "0");
			String sErrors = genErrorString(theBindingResult);
			System.out.println("CCC-Có lỗi");
			System.out.println(sErrors);
			flashMemory.setVar("flash_mess", "CCCCó lỗi khi tạo mới tài khoản: "+sErrors);
			
		}
		else {
			try {
				int createRet = userService.create(userRegisterDTO);
				if(createRet>0) {
					flashMemory.setVar("flash_code", "1");
					flashMemory.setVar("flash_mess", "Đã tạo tài khoản mới thành công");
				}
				else {
					flashMemory.setVar("flash_code", "0");
					flashMemory.setVar("flash_mess", "AAACó lỗi khi tạo mới tài khoản."+String.valueOf(createRet));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flashMemory.setVar("flash_code", "0");
				flashMemory.setVar("flash_mess", "BBBCó lỗi khi tạo mới tài khoản. "+e.getMessage());
			}
		}
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

		    if(object instanceof ObjectError) {
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
