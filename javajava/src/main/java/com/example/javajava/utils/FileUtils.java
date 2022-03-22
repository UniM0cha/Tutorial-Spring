package com.example.javajava.utils;

import com.example.javajava.board.dto.FileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {

  public List<FileDto> parseFileInfo(Long boardIdx, MultipartFile[] files) {

    if (ObjectUtils.isEmpty(files)) {
      return null;
    }

    List<FileDto> fileList = new ArrayList<FileDto>();

    // 파일이 업로드 될 폴더 생성
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
    ZonedDateTime current = ZonedDateTime.now();
    String path = "images/" + current.format(format);

    File file = new File(path);
    // 경로가 없으면 디렉터리를 만든다.
    if (!file.exists()) {
      file.mkdirs();
    }

    Iterator<String> iter = files.getFileNames();

    String originalFileExtention = null;
    while (iter.hasNext()) {
      List<MultipartFile> list = files.getFiles(iter.next());

      for (MultipartFile multipartFile : list) {

        // 파일이 있음
        if (!multipartFile.isEmpty()) {

          // contentType을 불러와서 타입별로 확장자를 붙혀주기 위함
          String contentType = multipartFile.getContentType();
          if (ObjectUtils.isEmpty(contentType)) {

            break;
          } else {

            if (contentType.contains("image/jpeg")) {
              originalFileExtention = ".jpg";
            } else if (contentType.contains("image/png")) {
              originalFileExtention = ".png";
            } else if (contentType.contains("image/gif")) {
              originalFileExtention = ".gif";
            } else {
              break;
            }
          }

          // 파일 이름이 중복되면 안되니까 나노타임을 파일명으로 쓴다.
          String newFileName = Long.toString(System.nanoTime()) + originalFileExtention;

          FileDto boardFile = new FileDto();
          boardFile.setIdx(boardIdx);
          boardFile.setFileSize(multipartFile.getSize());
          boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
          boardFile.setStoredFilePath(path + "/" + newFileName);
          fileList.add(boardFile);

          file = new File(path + "/" + newFileName);
          try {
            multipartFile.transferTo(file);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return fileList;
  }

}
