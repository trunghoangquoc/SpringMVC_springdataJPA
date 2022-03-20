package com.laptrinhjavaweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//
//-InternalResourceViewResolver
//+ prefix value = /WEB-INF/views/
//+ suffix value = ".jsp"

@Controller(value = "homeControllerOfWeb")
public class HomeController {
    
	//@RequestMapping nơi nhận các url vào 
//tiện ích hơn JSp-servlet vì ko cần sử dụng Type=? hay các action = login
// để phân biệt các controller nữa
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	   public ModelAndView homePage() {
	      ModelAndView mav = new ModelAndView("web/home");
	      return mav;
	   }
    
	//thêm 1 controller login 
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
//   //thêm 1 controller đăng ký (thử tự built)
	@RequestMapping(value = "/dang-ky" , method = RequestMethod.GET)
	public ModelAndView registerAccount() {
		 ModelAndView mav = new ModelAndView("dangky");
			return mav;
	}
	
	
	//viet 1 controller thoat. sẽ redireat ra /trang-chu
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	//viết 1 controller nếu ko phải admin mà muốn truy cập vào admin thì
	// sẽ redirect ra trang 'dang-nhap'
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
