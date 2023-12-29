/**
 * Class AdminDonationController
 * Controller cho các trang quản trị về đợt quyên góp
 */

package vn.funix.ccdn.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import vn.funix.ccdn.entity.Donation;
import vn.funix.ccdn.entity.DonationStatus;
import vn.funix.ccdn.entity.UserDonation;
import vn.funix.ccdn.service.DonationService;
import vn.funix.ccdn.service.UserDonationService;
import vn.funix.ccdn.utilities.FlashMemory;
import vn.funix.ccdn.utilities.Utility;

@Controller
@RequestMapping("/admin/donation")
public class AdminDonationController {
	@Autowired
	private DonationService donationService;
	
	
	
	@Autowired
	private Environment env;
	
	@GetMapping({"/",""})
	public String listAll(Model model,HttpSession session) {
		List<Donation> donations = donationService.getDonations();
		int pageSize_DT = getIntProperty("frontend.paging.qluser");
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
		model.addAttribute("donations",donations);
		model.addAttribute("new_donation",new Donation());
		model.addAttribute("dataTable_init_pagesize",pageSize_DT);
		String flashMess = flashMemory.getVar("flash_mess");
		if(flashMess!=null)
			model.addAttribute("flash_mess",flashMess);
		
		String flashCode = flashMemory.getVar("flash_code");
		if(flashCode!=null)
			model.addAttribute("flash_code",flashCode);
			
		return "admin/donation";
	}
	
	@GetMapping("/detail/{id}")
	public String detailDonation(Model model,@PathVariable("id") int theId,HttpSession session) {
		int pageSize_DT = getIntProperty("frontend.paging.qluser");
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
		Donation theDonation = donationService.getDonation(theId);
		model.addAttribute("donation",theDonation);
		String flashMess = flashMemory.getVar("flash_mess");
		if(flashMess!=null)
			model.addAttribute("flash_mess",flashMess);
		
		String flashCode = flashMemory.getVar("flash_code");
		if(flashCode!=null)
			model.addAttribute("flash_code",flashCode);
			
		return "/admin/detail.html";
	}
	
	@GetMapping("/detailD/{id}")
	public String detailDonationDebug(Model model,@PathVariable("id") int theId,HttpSession session) {
		int pageSize_DT = getIntProperty("frontend.paging.qluser");
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
		Donation theDonation = donationService.getDonation(theId);
		model.addAttribute("donation",theDonation);
		String flashMess = flashMemory.getVar("flash_mess");
		if(flashMess!=null)
			model.addAttribute("flash_mess",flashMess);
		
		String flashCode = flashMemory.getVar("flash_code");
		if(flashCode!=null)
			model.addAttribute("flash_code",flashCode);
			
		return "/debugger.html";
	}
	
	
	
	@PostMapping(value="/new", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView  createDonation(@ModelAttribute("new_donation") Donation theRequestDonation,HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		//System.out.println("the new Donation: "+theDonation.getCode());
		Donation theDonation = new Donation();
		theDonation.setCreated(Utility.getCurrentTimeString());
		theDonation.setCode(ensureUTF8(theRequestDonation.getCode()));
		theDonation.setName(ensureUTF8(theRequestDonation.getName()));
		theDonation.setStartDate(theRequestDonation.getStartDate());
		theDonation.setEndDate(theRequestDonation.getEndDate());
		theDonation.setOrganizationName(ensureUTF8(theRequestDonation.getOrganizationName()));
		theDonation.setPhoneNumber(theRequestDonation.getPhoneNumber());
		theDonation.setMoney(theRequestDonation.getMoney());
		theDonation.setDescription(ensureUTF8(theRequestDonation.getDescription()));
		
		
		
		try {
			int id = donationService.create(theDonation);
			flashMemory.setVar("flash_code", "1");
			flashMemory.setVar("flash_mess", "Đã tạo mới Donation "+theDonation.getCode()+" thành công");
			
		}
		catch(Exception e) {
			Throwable causeOne = e.getCause();
			String message = "Có lỗi khi thực hiện ("+e.getLocalizedMessage()+")";
			System.out.println(message);
			if(causeOne instanceof MySQLIntegrityConstraintViolationException) {
				MySQLIntegrityConstraintViolationException constraintViolationException = (MySQLIntegrityConstraintViolationException) causeOne;
				//System.out.println("MySQLIntegrityConstraintViolationException");
		        String constraintMessage = constraintViolationException.getMessage();
		        
		        //System.out.println(constraintMessage);
		        if(constraintMessage.contains("donation_code_unique"))
		        	message = "Mã dự án (code) đã tồn tại";
		        		
			}
			
			flashMemory.setVar("flash_code", "0");
			flashMemory.setVar("flash_mess", "Có lỗi khi tạo mới Donation: "+ message);
		}
		
		modelAndView.setViewName("redirect:/admin/donation/");
		//modelAndView.setViewName("redirect:/admin/debug");
		
		return  modelAndView;
	}
	
	@PostMapping(value="/changestatus/{new_state}", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView  changeDonationStatus(@ModelAttribute("old_donation") Donation theDonation,@PathVariable("new_state") String  newState,HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		try {
			int id = theDonation.getId(); 
			Donation oldDonation = donationService.getDonation(id);
			if(oldDonation!=null) {
				DonationStatus currentStatus= oldDonation.getStatusDonation();
				int iCurrentStatus = currentStatus.getId();
				if(newState.equalsIgnoreCase("1") && iCurrentStatus==0) {//từ Mở->Đang quyên góp
					oldDonation.setStatusDonation(new DonationStatus(1));
					flashMemory.setVar("flash_mess", "Đã chuyển trạng thái Donation "+oldDonation.getCode()+" thành Đang quyên góp");
				}
				else if (newState.equalsIgnoreCase("2") && iCurrentStatus== 1){ //từ Đang quyên góp->Kết thúc
					oldDonation.setStatusDonation(new DonationStatus(2));
					flashMemory.setVar("flash_mess", "Đã chuyển trạng thái Donation "+oldDonation.getCode()+" thành Kết thúc");
				}
				else if (newState.equalsIgnoreCase("3") && iCurrentStatus== 2){ //từ Kết thúc -> Đóng
					oldDonation.setStatusDonation(new DonationStatus(3));
					flashMemory.setVar("flash_mess", "Đã chuyển trạng thái Donation "+oldDonation.getCode()+" thành Đóng");
				}
				
				donationService.save(oldDonation);
				
				flashMemory.setVar("flash_code", "1");
			}
			else{
				flashMemory.setVar("flash_mess", "Có lỗi khi chuyển trạng thái");
				flashMemory.setVar("flash_code", "0");
			}
		}
		catch(Exception e) {
			flashMemory.setVar("flash_mess", "Có lỗi khi chuyển trạng thái: "+ e.getMessage());
			flashMemory.setVar("flash_code", "0");
		}
		
		modelAndView.setViewName("redirect:/admin/donation/");
		//modelAndView.setViewName("redirect:/admin/debug");
		
		return  modelAndView;
	}
	
	@PostMapping(value="/delete", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView  deleteDonation(@ModelAttribute("old_donation") Donation theDonation,HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		try {
			int id = theDonation.getId(); 
			Donation oldDonation = donationService.getDonation(id);
			String nameDonation = oldDonation.getName();
			donationService.delete(id);
			flashMemory.setVar("flash_mess", "Đã xóa Donation '"+nameDonation+"' (ID: "+String.valueOf(id)+")");
			flashMemory.setVar("flash_code", "1");
		}
		catch(Exception e) {
			flashMemory.setVar("flash_mess", "Có lỗi khi xóa: "+ e.getMessage());
			flashMemory.setVar("flash_code", "0");
		}
		
		modelAndView.setViewName("redirect:/admin/donation/");
		//modelAndView.setViewName("redirect:/admin/debug");
		
		return  modelAndView;
	}
	
	
	private static String ensureUTF8(String data) {
		return Utility.ensureUTF8(data);
	}
	
	@PostMapping(value="/save", produces="application/x-www-form-urlencoded;charset=utf-8")
	public ModelAndView  saveDonation(@ModelAttribute("new_donation") Donation theDonation,HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		FlashMemory flashMemory = FlashMemory.getInstance(session);
		
		Donation oldDonation = donationService.getDonation(theDonation.getId());
		if(oldDonation!=null) {
			oldDonation.setCode(ensureUTF8(theDonation.getCode()));
			oldDonation.setName(ensureUTF8(theDonation.getName()));
			oldDonation.setStartDate(theDonation.getStartDate());
			oldDonation.setEndDate(theDonation.getEndDate());
			oldDonation.setOrganizationName(ensureUTF8(theDonation.getOrganizationName()));
			oldDonation.setPhoneNumber(theDonation.getPhoneNumber());
			oldDonation.setMoney(theDonation.getMoney());
			oldDonation.setDescription(ensureUTF8(theDonation.getDescription()));
			
			try {
				donationService.save(oldDonation);
				flashMemory.setVar("flash_mess", "Đã lưu Donation '"+theDonation.getName()+"' (Mã: "+theDonation.getCode()+")");
				flashMemory.setVar("flash_code", "1");
			}
			catch(Exception e) {
				flashMemory.setVar("flash_mess", "Có lỗi trong khi lưu: "+e.getMessage());
				flashMemory.setVar("flash_code", "0");
			}
		}
		
		modelAndView.setViewName("redirect:/admin/donation/");
		//modelAndView.setViewName("redirect:/admin/debug");
		
		return  modelAndView;
	}
	
	
	
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
}
