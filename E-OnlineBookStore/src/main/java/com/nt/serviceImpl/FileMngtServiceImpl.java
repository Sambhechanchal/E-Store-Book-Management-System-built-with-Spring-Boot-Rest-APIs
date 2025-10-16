package com.nt.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.FileEntity;
import com.nt.repository.FileRepository;
import com.nt.service.IFileMngtService;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class FileMngtServiceImpl implements IFileMngtService {

	@Autowired
	private FileRepository fileRepo;
	
	@Override
	public String storeFile(MultipartFile file) throws IOException {
		FileEntity entity = new FileEntity();
		
		entity.setFileName(file.getOriginalFilename());
		entity.setFileType(file.getContentType());
		entity.setData(file.getBytes());
		fileRepo.save(entity);
		return "File inserted successfully.....!"+ file.getOriginalFilename();
	}

}
