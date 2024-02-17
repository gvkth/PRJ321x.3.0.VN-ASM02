package vn.funix.ccdn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.funix.ccdn.entity.Company;
//import vn.funix.ccdn.service.CompanyService;
import vn.funix.ccdn.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	
	
	  @GetMapping("/get_/{companyId}") public String getByCompanyID_(@PathVariable
	  int companyId,Model model) { Company company = companyService.get(companyId);
	  
	  
	  List<Company> featuredCompanies = companyService.getFeatured();
	  
	  
	  model.addAttribute("company",company);
	  model.addAttribute("featured",featuredCompanies);
	  
	  return "debugger.html"; }
	 
}
