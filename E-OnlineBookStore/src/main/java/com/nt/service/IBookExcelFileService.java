package com.nt.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.BookExcelFile;

public interface IBookExcelFileService {

	public void SaveExcelFile(MultipartFile file) throws IOException;

	public List<BookExcelFile> findExcelFileData();

}
