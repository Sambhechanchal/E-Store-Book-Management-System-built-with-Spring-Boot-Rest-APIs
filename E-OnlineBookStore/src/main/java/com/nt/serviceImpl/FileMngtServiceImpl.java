package com.nt.serviceImpl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.controller.CustomerController;
import com.nt.entity.FileEntity;
import com.nt.repository.FileRepository;
import com.nt.service.IFileMngtService;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class FileMngtServiceImpl implements IFileMngtService {

	@Autowired
	private FileRepository fileRepo;
	
	//private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileMngtServiceImpl.class);
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Override
	public String storeFile(MultipartFile file) throws IOException {
		logger.info("FileMngtServiceImpl storeFile method execution started...! ");
		FileEntity entity = new FileEntity();
		
		entity.setFileName(file.getOriginalFilename());
		entity.setFileType(file.getContentType());
		entity.setData(file.getBytes());
		fileRepo.save(entity);
		logger.info("FileMngtServiceImpl storeFile method execution ended...! ");
		return "File inserted successfully.....!"+ file.getOriginalFilename();
	}

}
