package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.post.dao.PostMapper;
import com.memo.post.domain.Post;

@Service
public class PostBO {

		@Autowired
		private PostMapper postMapper; // mybatis
		
		public List<Post> getPostListByUserId(int userId) {
			return postMapper.selectPostListByUserId(userId);
		}
}
