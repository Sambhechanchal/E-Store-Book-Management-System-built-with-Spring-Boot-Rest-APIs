package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.BookExcelFile;

@Repository
public interface BookExcelFileRepository  extends JpaRepository<BookExcelFile, Long>{

}
