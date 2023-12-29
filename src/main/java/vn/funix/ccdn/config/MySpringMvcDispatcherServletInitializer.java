package vn.funix.ccdn.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DemoAppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	
	/* Khong co tac dung
	@Override
	protected Filter[] getServletFilters() {
	CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
	encodingFilter.setEncoding("UTF-8");
	encodingFilter.setForceEncoding(true);
	return new Filter[]{encodingFilter};
	}*/
	
	
	/* notepoint 001 - Good! 
	 * Theo huong dan: https://kienthuclaptrinh.vn/2020/03/11/debug-va-khac-phuc-loi-hien-thi-ky-tu-unicode-cua-ung-dung-web/
	 * */
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic filterRegistration =
            servletContext.addFilter("endcoding-filter", new CharacterEncodingFilter());
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        
        //make sure encodingFilter is matched most first, by "false" arg
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        
        super.onStartup(servletContext);
    }
	
}






