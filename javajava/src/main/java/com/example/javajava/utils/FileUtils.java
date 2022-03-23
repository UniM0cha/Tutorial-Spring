package com.example.javajava.utils;

import com.example.javajava.board.dto.FileDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FileUtils {

  // 실제 저장 경로 : filePath + todayPath + newFilename

  @Value("${custom.path.upload-file}")
  private String filePath;

  public List<FileDto> parseFileInfoAndSave(List<MultipartFile> files) {
    if (files.isEmpty()) {
      return null;
    }

    List<FileDto> fileDtos = new ArrayList<>();

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
    ZonedDateTime current = ZonedDateTime.now();
    String todayPath = current.format(format) + "/";

    File dir = new File(filePath + todayPath);
    // 경로가 없으면 디렉터리를 만든다.
    if (!dir.exists()) {
      dir.mkdirs();
    }

    for (MultipartFile file : files) {
      // 파일이 있을 떼
      if (!file.isEmpty()) {

        String contentType = file.getContentType();
        String originalFileExtention = null;
        if (contentType.contains("image/jpeg")) {
          originalFileExtention = ".jpg";
        } else if (contentType.contains("image/png")) {
          originalFileExtention = ".png";
        } else if (contentType.contains("image/gif")) {
          originalFileExtention = ".gif";
        } else {
          break;
        }

        // 파일 이름이 중복되면 안되니까 나노타임을 파일명으로 쓴다.
        String newFileName = Long.toString(System.nanoTime()) + originalFileExtention;

        FileDto fildDto = FileDto.builder()
            .fileSize(file.getSize())
            .originalFileName(file.getOriginalFilename())
            .storedFilePath(todayPath + newFileName)
            .build();

        fileDtos.add(fildDto);

        File fileSave = new File(filePath + todayPath + newFileName);
        try {
          file.transferTo(fileSave);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return fileDtos;
  }
}
