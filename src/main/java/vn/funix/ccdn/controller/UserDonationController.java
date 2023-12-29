package vn.funix.ccdn.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.funix.ccdn.dto.UserDonationDTO;
import vn.funix.ccdn.entity.Donation;
import vn.funix.ccdn.entity.User;
import vn.funix.ccdn.entity.UserDonation;
import vn.funix.ccdn.entity.UserDonationStatus;
import vn.funix.ccdn.service.DonationService;
import vn.funix.ccdn.service.UserDonationService;
import vn.funix.ccdn.service.UserService;
import vn.funix.ccdn.utilities.FlashMemory;

@Controller
@RequestMapping("/user_donation")
public class UserDonationController {
	@Autowired
	private UserDonationService userDonationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private Environment env;
	
	@PostMapping(value="/change_status/{status_id}", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView userDonationChangeStatus(
			@PathVariable("status_id") Integer theNewStatusID,
			@RequestParam("id") Integer idUserDonation
		){
		ModelAndView modelAndView = new ModelAndView();
		Integer idDonation=null;
		if(idUserDonation!=null) {
			UserDonation userDonation1 = userDonationService.getUserDonation(idUserDonation);
			int currentStatusID = userDonation1.getStatusID();
			if(currentStatusID==UserDonationStatus.STATUS_CHOXACNHAN 
					&& theNewStatusID==UserDonationStatus.STATUS_DAQUYENGOP) {
				userDonation1.setStatus(new UserDonationStatus(UserDonationStatus.STATUS_DAQUYENGOP));
			}
			else if(currentStatusID==UserDonationStatus.STATUS_DAQUYENGOP 
					&& theNewStatusID==UserDonationStatus.STATUS_CHOXACNHAN) {
				userDonation1.setStatus(new UserDonationStatus(UserDonationStatus.STATUS_CHOXACNHAN));
			}
			try {
				userDonationService.saveUserDonation(userDonation1);
				idDonation = userDonation1.getDonation().getId();
				Donation relatedDonation = donationService.getDonation(idDonation);
				if(relatedDonation!=null) {
					relatedDonation.reCalculateMoney();
					donationService.save(relatedDonation);
				}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println("the UserDonation: "+userDonation.getId());
//		System.out.println(theNewStatusID);
		if(idDonation!=null)
			modelAndView.setViewName("redirect:/admin/donation/detail/"+idDonation);
		else
			modelAndView.setViewName("redirect:/admin/donation/");
		return  modelAndView;
	}
	
	
	
	@PostMapping(value="/make", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView  makeADonation(
			@RequestParam(value ="currentUrl", required = false) String currentUrl,
			@ModelAttribute("user_donation") UserDonationDTO userDonationDTO,
			HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
		//System.out.println(userDonationDTO.toString());
		//System.out.println(currentUrl);
		
		User userMakeDonation = userService.get(userDonationDTO.getIdUser());
		Donation donationBeDonated = donationService.getDonation(userDonationDTO.getIdDonation());
		
		UserDonation userDonation = new UserDonation(userDonationDTO,userMakeDonation,donationBeDonated);
		try {
			int new_id = userDonationService.createUserDonation(userDonation);
			if(new_id!=0) {
				
				flashMemory.setVar("flash_mess", "Đã thực hiện quyên góp thành công. Cám ơn bạn rất nhiều");
				flashMemory.setVar("flash_code","1");
			} else {
				flashMemory.setVar("flash_mess", "Không thành công");
				flashMemory.setVar("flash_code","0");
			}
		} catch (Exception e) {
			flashMemory.setVar("flash_mess", "Không thành công."+e.getLocalizedMessage());
			flashMemory.setVar("flash_code","0");
			e.printStackTrace();
		}
		
		
		
		if(currentUrl!="")
			modelAndView.setViewName("redirect:"+currentUrl);
		else
			modelAndView.setViewName("redirect:/donation/getAll/page/1");
		return  modelAndView;
	}
}
