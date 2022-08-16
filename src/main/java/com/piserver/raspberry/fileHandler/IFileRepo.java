package com.piserver.raspberry.fileHandler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileRepo extends JpaRepository <FileModel,Long>{
}
