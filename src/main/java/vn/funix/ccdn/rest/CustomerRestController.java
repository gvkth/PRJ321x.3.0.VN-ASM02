package vn.funix.ccdn.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.funix.ccdn.entity.Customer;
import vn.funix.ccdn.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	//autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	//add mapping for GET/ customers
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer theCustomer = customerService.getCustomer(customerId);
		if (theCustomer==null)
			throw new CustomerNotFoundException("Customer id not found - "+customerId);
		return theCustomer;
				
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		//if id=0 => create instead of update
		
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer tempCustomer = customerService.getCustomer(customerId);
		if(tempCustomer==null) {
			throw new CustomerNotFoundException("Customer is not found - "+customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Deleted customer id - "+customerId;
	}

}
