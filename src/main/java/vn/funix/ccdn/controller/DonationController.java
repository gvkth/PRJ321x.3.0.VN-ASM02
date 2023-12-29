package vn.funix.ccdn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import vn.funix.ccdn.dto.DonationDTO;
import vn.funix.ccdn.dto.UserDonationDTO;
import vn.funix.ccdn.entity.CustomUserDetails;
import vn.funix.ccdn.entity.Donation;
import vn.funix.ccdn.entity.User;
import vn.funix.ccdn.entity.UserDonation;
import vn.funix.ccdn.service.DonationService;
import vn.funix.ccdn.service.UserService;
import vn.funix.ccdn.utilities.FlashMemory;

@Controller
@RequestMapping("/donation")
public class DonationController {
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment env;
	
	
	@GetMapping("/getAll_/}")
	public String listAll(@RequestParam(name="page", defaultValue="1") int pageNumber,Model model) {
		long countAllDonations = donationService.countAll();
		int iPageSize = getIntProperty("spring.data.web.pageable.default-page-size");
		int pageCount = (int)(countAllDonations/iPageSize)+1;
		
		int iCur = (pageNumber-1)*iPageSize,
			iPageNext = pageNumber+1,
			iPagePrev = pageNumber-1;
		if(iPagePrev<1)
			iPagePrev=0;
		if((long)iPageNext>pageCount)
			iPageNext=-1;
		
		
		List<Donation> donations = donationService.getDonations(iCur,iPageSize);
		model.addAttribute("donations",donations);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("iPagePrev",iPagePrev);
		model.addAttribute("iPageNext",iPageNext);
		model.addAttribute("count",countAllDonations);
		model.addAttribute("pageCount",pageCount);
		
		return "public/home";
	}
	
	@GetMapping("/getAll/page/{pageNumber}")
	public String listAllByPageNumber(@PathVariable int pageNumber,Model model,Authentication authentication,HttpSession session) {
		long countAllDonations = donationService.countAll();
		int iPageSize = getIntProperty("spring.data.web.pageable.default-page-size");
		UserDonationDTO userDonationDTO = new UserDonationDTO();
		String currentPath = "/donation/getAll/page/"+new Integer(pageNumber);
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		int pageCount = (int)(countAllDonations/iPageSize)+1;
		//System.out.println(authentication.getPrincipal());
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
		
		
		
		int iCur = (pageNumber-1)*iPageSize,
			iPageNext = pageNumber+1,
			iPagePrev = pageNumber-1;
		if(iPagePrev<1)
			iPagePrev=0;
		if((long)iPageNext>pageCount)
			iPageNext=-1;
		
		
		List<Donation> donations = donationService.getDonations(iCur,iPageSize);
		model.addAttribute("donations",donations);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("iPagePrev",iPagePrev);
		model.addAttribute("iPageNext",iPageNext);
		model.addAttribute("count",countAllDonations);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("userDonationDTO",userDonationDTO);
		model.addAttribute("currentPath",currentPath);
		
		String flashMess = flashMemory.getVar("flash_mess");
		if(flashMess!=null)
			model.addAttribute("flash_mess",flashMess);
		
		String flashCode = flashMemory.getVar("flash_code");
		if(flashCode!=null)
			model.addAttribute("flash_code",flashCode);
		
		return "public/home";
	}
	
	@PostMapping("/add/{donationId}")
	public RedirectView  addDonation(@PathVariable int donationId,RedirectAttributes attributes) {
		attributes.addAttribute("msg", "Success");
		return new RedirectView("/donation/get/{donationId}");
	}
	
	@GetMapping("/get/{donationId}")
	public String getByDonationID(@PathVariable int donationId,Model model,Authentication authentication,HttpSession session) {
		Donation donation = donationService.getDonation(donationId);
		DonationDTO donationDTO = donation.getDTO();
		UserDonationDTO userDonationDTO = new UserDonationDTO();
		String currentPath = "/donation/get/"+new Integer(donationId);
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
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
		
		
		model.addAttribute("donation",donation);
		model.addAttribute("donationDTO",donationDTO);
		model.addAttribute("userDonationDTO",userDonationDTO);
		model.addAttribute("currentPath",currentPath);
		
		String flashMess = flashMemory.getVar("flash_mess");
		if(flashMess!=null)
			model.addAttribute("flash_mess",flashMess);
		
		String flashCode = flashMemory.getVar("flash_code");
		if(flashCode!=null)
			model.addAttribute("flash_code",flashCode);
		
		
		return "public/detail";
	}
	
	@GetMapping("/get_/{donationId}")
	public String getByDonationID_(@PathVariable int donationId,Model model) {
		Donation donation = donationService.getDonation(donationId);
		DonationDTO donationDTO = donation.getDTO();
		List<UserDonation> userDonations = donation.getUsers();
		model.addAttribute("donation",donation);
		model.addAttribute("donationDTO",donationDTO);
		model.addAttribute("userDonations",userDonations);
		model.addAttribute("msg","A message");
		
		return "debugger.html";
	}
	
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
}
