package vn.funix.ccdn.utilities;

import javax.servlet.http.HttpSession;

public class FlashMemory {
	private static HttpSession _httpSession;
	private static final FlashMemory _theSingletonInstance ;
	
	static {
		_httpSession = null;
		try {
			_theSingletonInstance = new FlashMemory();
		}
		catch(Exception e) {
			throw new RuntimeException("Exception occurred in creating singleton instance");
		}
	}

	public static FlashMemory getInstance(HttpSession httpSession) {
		
		if(_httpSession==null || _httpSession!=httpSession) {
			_httpSession = httpSession;
		}
		return _theSingletonInstance;
	}

	private FlashMemory() {
	}
	
	public String setVar(String varName, String varValue) {
		if(_httpSession!=null) {
			_httpSession.setAttribute(varName, varValue);
			return varValue;
		}
		return null;
	}
	
	public String getVar(String varName) {
		String varValue = null;
		if(_httpSession!=null) {
			try {
				varValue = (String)_httpSession.getAttribute(varName);
				_httpSession.removeAttribute(varName);
			}
			catch (Exception e) {

			}
		}
		return varValue;
	}
}
