package ru.geekbrains.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.model.Picture;

import java.util.List;

public interface FilesService {
    String uploadFromPolog(long formId, Picture file);
    List<String> getFileList(String formId);
    Resource loadAsResource(String formId, String filename);
    String deleteOneFile(String formId, String filename);
}
