package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Autowired
	private NewConverter newConverter;
   
	@Autowired
	private CategoryRepository categoryRepository;
   
	/*Phân Trang
 1. client gửi về server   : 
     +page
	 +limit (cái limit là động tức là số lượng item hiển thị trong mỗi page là tùy theo ý người dùng.)
 
 2.  server trả ra  client sau khi xử lý:
   -totalPage
   -page
   -list<data(newDTO, userDTO, commentDTO,....)>   */
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		// B1.tạo 1 List DTO để hứng data lấy từ database
		List<NewDTO> models = new ArrayList<>();
		
		//để truyền dc cái pageable xuống entity để mà lấy danh sách lên
		//B2.tạo list newentity lấy data từ database lên
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		//converter từ entity sang DTO rồi thêm vào 	models.add(newDTO);
		for (NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDto(item);
			models.add(newDTO);
		}
		return models;
/*
 * //newDTO hứng từng cái field mà item gọi ra trong cái Entity
 * newDTO.setTitle(item.getTitle());
 * newDTO.setShortDescription(item.getShortDescription());
 * newDTO.setContent(item.getContent());
 * (converter lam nhiệm vụ này) 
 * models.add(newDTO);//lưu vào list DTO đã tạo bên trên }
*/
	//return models; trả lại cái LIST đó để đưa ra view.
		
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}
	
	//tại sao findById ko cần tạo list như gọi hàm findAll
	//vì lấy mỗi id thì cần gì tạo list
	@Override
	public NewDTO findById(long id) {

		NewEntity entity = newRepository.findOne(id);
		return newConverter.toDto(entity);//converter từ entity->DTO
	}

	//viết hàm save = insert + update
//	update bài viết
//
//- Khi cta chỉnh sửa ở client rồi gửi lên server thì sẽ đẩy vào thằng DTO . 
//->dùng hàm converter để chuyển những dữ liệu mới update ở các field từ DTO sang thằng oldNew(entity)
	@Override
	@Transactional
	public NewDTO save(NewDTO dto) { 
		//cả update vs insert đều cần gọi category theo code 
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if (dto.getId() != null) {
			NewEntity oldNew = newRepository.findOne(dto.getId());
//			oldNew.setCategory(category);//set category cho bài viết 
			newEntity = newConverter.toEntity(oldNew, dto);
	//convert bài viết mới vào thằng oldNew
//dùng entity toEntity (vì chỉ thay đổi value trong filed thôi)
		} else {
			newEntity = newConverter.toEntity(dto);
//			newEntity.setCategory(category);
		}
		newEntity.setCategory(category);
		return newConverter.toDto(newRepository.save(newEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id: ids) {
			newRepository.delete(id);
		}
	}
	
	
	/*
	 * @Override
	 * 
	 * @Transactional
	 * NewDTO insert(NewDTO newDto) { 
	 * CategoryEntity category = categoryRepository.findOneByCode(newDto.getCategoryCode()); 
	 * NewEntity newEntity = newConverter.toEntity(newDto); 
	 * newEntity.setCategory(category);
	 * //lưu vào entity rồi đổ vào hàm save //hàm save đc thằng Repository viết sẵn
	 * rồi
	 * 
	 * return newConverter.toDto(newRepository.save(newEntity)); //lưu xuống
	 * database rồi lấy lên converter ngươc lại Dto }
	 * 
	 * @Override
	 * Cách 1 :
	 * @Transactional 
	 * public NewDTO update(NewDTO dto) { 
	 * NewEntity oldNew = newRepository.findOne(dto.getId());
	 *///khi findOne(dto.getId()); sẽ lấy lên dữ liệu của các column trong table new theo Id
	 /* CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
	 * oldNew.setCategory(category); 
	 * NewEntity updateNew = newConverter.toEntity(oldNew,dto); 
	 * return newConverter.toDto(newRepository.save(updateNew));
	 *  }
	 *  
	 *  Cách 2 :
	 *  * 
	 *  public NewDTO update(NewDTO dto) { 
	 * NewEntity oldNew = newRepository.findOne(dto.getId());
	 *///khi findOne(dto.getId()); sẽ lấy lên dữ liệu của các column trong table new theo Id
	 /*NewEntity updateNew = newConverter.toEntity(oldNew,dto);
	 *  CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
	 * updateNew.setCategory(category); 
	 * return newConverter.toDto(newRepository.save(updateNew));
	 *  }
	 */
	 
}
