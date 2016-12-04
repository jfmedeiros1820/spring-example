package org.codehouse.store.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

@Component
public class FileSaver {

  @Autowired
  private HttpServletRequest request;

  public String write(String baseFolder, MultipartFile file) {
    String realPath = request.getServletContext().getRealPath("/" + baseFolder);
    String path = realPath + "/" + file.getOriginalFilename();
    try {
      file.transferTo(new File(path));
    } catch (IllegalStateException | IOException e) {
      throw new RuntimeException(e);
    }
    return baseFolder + "/" + file.getOriginalFilename();
  }
}
