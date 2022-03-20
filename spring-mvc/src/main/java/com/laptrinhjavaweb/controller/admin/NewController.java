package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	
	@Autowired
	private INewService newService;
    
	@Autowired
	private ICategoryService categoryService;
    
	@Autowired
	private MessageUtil messageUtil;
	

	/*Phân Trang
 1. client gửi về server   : 
     +page
	 +limit (cái limit là động tức là số lượng item hiển thị trong mỗi page là tùy theo ý người dùng.)
 
 2.  server trả ra  client sau khi xử lý:
   -totalPage
   -page
   -list<data(newDTO, userDTO, commentDTO,....)>   */
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page,
			                     @RequestParam("limit") int limit,HttpServletRequest request ) {
		
		
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/new/list");
		
		//xử lý offset và limit
		Pageable pageable = new PageRequest(page-1, limit);
		model.setListResult(newService.findAll(pageable));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		//@RequestParam : sử dụng khi thêm cái param id trên URl
		//uRL:/quan-tri/bai-viet/chinh-sua?id=
//required = false : tránh trường hợp khi thêm bài viết ko cần đến id 
	//tức là trên url ko có cái @RequestParam id
		
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if (id !=null) {
			model = newService.findById(id);
		}
		//xử lý message
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories",categoryService.findAll());
		mav.addObject("model", model);
		return mav;
		
//mav.addObject("model", model);
//cái model sẽ match với cái modelAttribute = "model"trong edit.jsp
		//modelAttribute <=> formUtils		
//		Thường thì request body sẽ ở dạng JSON hoặc form-data, 
//	       khi vào controller sẽ được tự động chuyển ra thành Object 
//	           (ví dụ DTO chẳng hạn để map vs DTO).
//		 -@ModelAttribute khác gì với @RequestBody:
//		     KHI VIẾT HÀM HỨNG POST REQUEST sẽ có hai khả năng:
//
//		       +Một HTML form tạo ra post request. @ModelAttribute dùng để hứng dữ liệu dạng form.
//		       +Một ứng dụng di động, hay ứng dụng web client side render kiểu :
//			      React, Vue, Angular gửi post request dạng JSON. 
//				  @RequestBody dùng để hứng dữ liệu dạng JSON.
	}
}
