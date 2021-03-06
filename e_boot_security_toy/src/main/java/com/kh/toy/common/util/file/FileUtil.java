package com.kh.toy.common.util.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.kh.toy.common.code.Code;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.CustomException;

public class FileUtil {

	public List<FileEntity> fileUpload(List<MultipartFile> files) {
		//파일과 관련된 정보를 가지고 반환될 list	
		List<FileEntity> fileData = new ArrayList<FileEntity>();
		
		if(files.size() >= 1 && !files.get(0).getOriginalFilename().equals("")) {
			for(MultipartFile mf : files) {
				//서버에 저장할 경로
				String savePath = getSavePath();
				//사용자가 올린 파일 이름
				String originFileName = mf.getOriginalFilename();
				//서버에 저장될 파일 이름
				String renameFileName = getRenameFileName(originFileName);
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("originFileName", originFileName);
				map.put("renameFileName", renameFileName);
				map.put("savePath",savePath);
				
				FileEntity FileEntity = new FileEntity();
				FileEntity.setOriginFileName(originFileName);
				FileEntity.setRenameFileName(renameFileName);
				FileEntity.setSavePath(savePath);
				
				//tb_file에 저장할 데이터를 list에 추가
				fileData.add(FileEntity);
				saveFile(mf,FileEntity);
			}
		}
		return fileData;	
	}
	
	public String getSavePath() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return year + "/" + mon + "/" + day + "/";
	}
	
	public String getRenameFileName(String originFileName) {
		 UUID renameFileID = UUID.randomUUID(); 
	     return renameFileID.toString() + originFileName.substring(originFileName.lastIndexOf("."));
	}

	public void saveFile(MultipartFile mf, FileEntity FileEntity)  {
		//사용자가 등록한 파일을 옮겨담을 파일 객체 생성
		//savePath : 저장할 경로 + 변경된 파일명
		File path = new File(FileEntity.getFullPath());
		if(!path.exists()) {
			path.mkdirs();
		}
		
		File tempFile 
			= new File(FileEntity.getFullPath()+FileEntity.getRenameFileName());
		try {
			mf.transferTo(tempFile);
		} catch (IllegalStateException | IOException e) {
			throw new CustomException(ErrorCode.FILE_ACCESS_ERROR);
		}
	}
	
	public void deleteFile(String fullPath, String fileName) {
		//지정된 경로의 파일 객체를 생성
		File file = new File(fullPath + fileName);
		//delete() 메서드로 파일을 삭제
		file.delete();
	}
}
