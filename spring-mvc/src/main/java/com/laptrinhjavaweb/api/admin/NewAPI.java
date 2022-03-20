   package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.INewService;



//quản lý bài viết ở phần admin
// -để định nghĩa dc @RestController(API)
/*+DISPATCHER-SERVLET.XML
thêm package="com.laptrinhjavaweb.api" để khi request thì sẽ tìm 
đến package=com.laptrinhjavaweb.api  để nhảy vào tìm các requestMapping
trùng với URL cta gọi*/
@RestController(value = "newAPIOfAdmin") //biến class java bt thành 1 Api
public class NewAPI {
	
	
	@Autowired
	private INewService newService;
	
	@PostMapping("/api/new") 
	public NewDTO createNew(@RequestBody NewDTO newDTO) {
		return newService.save(newDTO);
	}
	
	@PutMapping("/api/new")
	public NewDTO updateNew(@RequestBody NewDTO updateNew) {
		return newService.save(updateNew);
	}


	@DeleteMapping("/api/new")
	public void deleteNew(@RequestBody long[] ids) {
		//@RequestBody long[] ids : là nhận về 1mảng để chứa Id bài viết cần xóa
		newService.delete(ids);
	}
}
//@PathVariable là annotation ĐỂ LẤY THAM SỐ là một thành phần của đường dẫn 
//đc gửi từ client về server để lấy data từ database theo cái THAM Số đấy.
//	 
//	        Ví dụ http://acme.com/book/5 thì 5 là thành phần dùng làm tham số
//			@GetMapping("/book/{id}")
//            public String getBookByPath(@PathVariable("id") int id) {
//                      return "Book id = " + id;
//                     }
	
//nếu dùng @RequestParam là annotation ĐỂ LẤY THAM SỐ là biến trong query string.
//thì url sẽ là http://acme.com/book/?id=5


//Test 
//@PostMapping ("/news")
//public String test() {
//
//      return "ss";
//}
/*@RestController = @Controller + @ResponseBody
* @PostMapping = @RequestMapping + RequestMethod.POST
* @ResponseBody như ModelAndView để đưa ra view sau khi xử lý xong
*/

//@Controller
//public class NewAPI {
//
//      @RequestMapping(value = "/new", method = RequestMethod.POST)
//      @ResponseBody // khi server trả data về client thì cũng phải convert qua kiểu json
//      
//      public NewDTO createNew(@RequestBody NewDTO model) {
//              return model;
//
//      }
//
//}