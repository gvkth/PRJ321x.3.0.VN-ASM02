package vn.funix.ccdn.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
		/*
		 * Code demo bên dưới. Khi áp dụng vướng: trong cả 2 case 
		 *    + sai email
		 *    + đúng email sai password
		 *    đều có  exception.getMessage()= "Bad credentials" nên không phân biệt được
		 * */
		
		String username = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("Failed login with email '"+username+"', password ='"+password+"'");
        if(exception instanceof org.springframework.security.authentication.BadCredentialsException){
            @SuppressWarnings("unused")
			int i=0; //test case 1
            if(i==0) {
            	response.sendRedirect(request.getContextPath() +"/auth/login?error=wrong_email");
            	return;
            }
            //else if(i==1)
        	response.sendRedirect(request.getContextPath() +"/auth/login?error=wrong_password");
        	return;
            
        }
        
        response.sendRedirect(request.getContextPath() +"/auth/login?error=wrong_information");
	    return;
        
        
	}

}
