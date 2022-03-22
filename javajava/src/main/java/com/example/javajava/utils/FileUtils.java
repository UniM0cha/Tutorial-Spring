package com.example.javajava.utils;

import com.example.javajava.board.dto.FileDto;
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

  public List<FileDto> parseFileInfo2(List<MultipartFile> files) {
    if (files.isEmpty()) {
      return null;
    }

    List<FileDto> fileDtos = new ArrayList<>();

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
    ZonedDateTime current = ZonedDateTime.now();
    String path = "/" + current.format(format);
    String originalFileExtention = null;

    log.info("path: " + path);
    File f = new File("files" + path);
    // 경로가 없으면 디렉터리를 만든다.
    if (!f.exists()) {
      f.mkdirs();
    }

    for (MultipartFile file : files) {
      // 파일이 있을 떼
      if (!file.isEmpty()) {

        String contentType = file.getContentType();
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
            .storedFilePath(path + "/" + newFileName)
            .build();

        log.info("file: " + fildDto.toString());
        fileDtos.add(fildDto);

        File fileSave = new File(path + "/" + newFileName);
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
