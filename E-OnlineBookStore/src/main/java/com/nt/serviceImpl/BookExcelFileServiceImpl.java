package com.nt.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.BookExcelFile;
import com.nt.repository.BookExcelFileRepository;
import com.nt.service.IBookExcelFileService;
import com.nt.utility.Helper;

@Service
public class BookExcelFileServiceImpl implements IBookExcelFileService {
	
	@Autowired
	private BookExcelFileRepository bookExcelFileRepo;
	
	@Override
	public void SaveExcelFile(MultipartFile file) throws IOException {
		
		List<BookExcelFile> saveAll = bookExcelFileRepo.saveAll(Helper.excelFileInsertDatabase(file.getInputStream()));
		System.out.println("stored successfully");
		
	}

	@Override
	public List<BookExcelFile> findExcelFileData() {
		return bookExcelFileRepo.findAll();
		
	}

}
