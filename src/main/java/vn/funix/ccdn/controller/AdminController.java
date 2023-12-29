/**
 * Class AdminController
 * Controller cho các trang quản trị tổng hợp
 */

package vn.funix.ccdn.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
	public class AdminController {
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public void  showDefaultAdminPage(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.sendRedirect("account/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {""}, method = RequestMethod.GET)
	public void  showDefaultAdminPage_(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.sendRedirect("admin/account/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/debug")
	public String debug(Model model) {
		return "debugger";
	}
}
