package com.memo.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostMapper;
import com.memo.post.domain.Post;

import lombok.ToString;

@ToString
@Service
public class PostBO {
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		//private Logger logger = LoggerFactory.getLogger(PostBO.class);

		@Autowired
		private PostMapper postMapper; // mybatis
		
		@Autowired
		private FileManagerService fileManager;
		
		public List<Post> getPostListByUserId(int userId) {
			return postMapper.selectPostListByUserId(userId);
		}
		
		// 게시물글 db insert
		public int addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
			
			String imagePath = null;
			
			// 이미지가 있으면 업로드 후 imagePath 받아옴
			if (file != null) {
				imagePath = fileManager.saveFile(userLoginId, file);
			}
			
			return postMapper.insertPost(userId, subject, content, imagePath);
		}
		
		// 상세 게시글 가져오기 
		public Post getPostByPostIdAndUserId(int postId, int userId) {
			return postMapper.selectPostByPostIdAndUserId(postId, userId);
		}
		
		
		// 글 수정
		public void updatePost(int userId, String userLoginId, int postId, String subject, String content, MultipartFile file) {
			
			// 1. 업데이트 대상인 기존 글을 가져와본다. select (validation, 이미지 교체시 기존 이미지 제거를 위해)
			// ★★★★★ 로그 찍기
			Post post = postMapper.selectPostByPostIdAndUserId(postId, userId);
			if (post == null) {
				logger.warn("[글 수정] post is null. postId:{}, userId:{}", postId, userId);
				return;
			}
			
			// 2. 파일이 비어있지 않다면 업로드 후 imagePath 얻어옴
			// 업로드가 성공하면 기존 이미지 제거
			String imagePath = null;
			if (file != null) {
				// 업로드 
				imagePath = fileManager.saveFile(userLoginId, file);
				
				// 기존 이미지 제거
				//-- 업로드가 성공 했고, 기존 이미지 존재하는 경우 
				if (imagePath != null && post.getImagePath() != null) {
					// 이미지 제거 
					fileManager.deleteFile(post.getImagePath());
				}
			}
			
			// 3.글 업데이트 
			postMapper.updatePostByPostIdAndUserId(postId, userId, subject, content, imagePath);
		}
		
}












