package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

		@RequestMapping("/is_duplicated_id")
		public Map<String, Object> isDuplicatedId(
				@RequestParam("loginId") String loginId){
			
			Map<String, Object> result = new HashMap<>();
			
			// select
			result.put("code", 1);
			result.put("usDuplicatedId", true);
			
			return result;
		}
}
