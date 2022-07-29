package com.piserver.raspberry.fileHandler;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepo extends JpaRepository <FileModel,Long>{
}
