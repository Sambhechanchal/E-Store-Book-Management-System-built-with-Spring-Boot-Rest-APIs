package com.nt.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileMngtService {

	public String storeFile(MultipartFile file) throws IOException;
}
