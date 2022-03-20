package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")//bật tính năng sử dụng Auditing
public class JpaAuditingConfig {
	
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	
	public static class AuditorAwareImpl implements AuditorAware<String> {

		//chỗ này chính là cái chỗ JpaAuditing nó get cái user name ra ở Spring_security.
		//(vì cái Spring_security nó lưu thông tin user trong principal)
		//để tìm creatddate, createby lưu xuống database
		
		@Override
		public String getCurrentAuditor() {
			//ktra tk có tồn tại hay ko ?
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			//ktra xem tài khoản ko có , hoặc chưa đăng nhập 
			if (authentication == null) {
				return null;
			}
			return authentication.getName();
		}
	}
}
