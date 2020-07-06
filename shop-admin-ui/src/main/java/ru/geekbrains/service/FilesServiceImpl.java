package ru.geekbrains.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.model.Picture;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class FilesServiceImpl implements FilesService{

    @Override
    public String  uploadFromPolog(long formId, Picture file) {
        System.out.println("RC-REST-API: FilesServiceImpl: uploadFromPolog: Start upload service file "+file.getName());
        String operationResult ="";
        String fullFolder = "D:/java/" + formId + "/";
        if (file.getPictureData() == null) {
            System.out.println("RC-REST-API: FilesServiceImpl: file.isEmpty()");
            operationResult = "File is empty. Please select a file to upload";
            return operationResult;
        }
        try {
            //Get the file and save it
            byte[] bytes = file.getPictureData().getData();
            Path folder = Paths.get(fullFolder);
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
                System.out.println("RC-REST-API: FilesServiceImpl: uploadFromPolog: Create folder: " + fullFolder);
            }
            Path path = Paths.get(fullFolder + file.getName());
            if (Files.exists(path)) {
                System.out.println("RC-REST-API: FilesServiceImpl: uploadFromPolog: File already exists, try to send another file");
                operationResult = "File already exists, try to send another file";
                return operationResult;
            }
            Files.write(path, bytes);
            System.out.println("RC-REST-API: FilesServiceImpl: uploadFromPolog: File saved! ");
            System.out.println("RC-REST-API: FilesServiceImpl: uploadFromPolog: Where can we find a file? - " + path.toAbsolutePath().toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("RC-REST-API: FilesServiceImpl: uploadFromPolog: File was not saved " + e);
            operationResult="IO Error - File was not saved";
            return operationResult;
        }
        operationResult="OK. File uploaded.";
        return operationResult;
    }

    @Override
    public List<String> getFileList(String formId) {
        return null;
    }

    @Override
    public Resource loadAsResource(String formId, String filename) {
        return null;
    }

    @Override
    public String deleteOneFile(String formId, String filename) {
        return null;
    }

 /*
    @Override
    public List<String> getFileList(String formId) {
        File folder = new File(pathRead() + formId + "/");
        if (!folder.exists())
            return null;
        String[] list = folder.list();
        return Arrays.asList(list);
    }

    @Override
    public Resource loadAsResource(String formId, String filename) {
        try {
            Path file = Paths.get(pathRead() + formId + "/" + filename);
            Resource resource = new UrlResource(file.toUri());
            admin.info("RC-REST-API: FilesServiceImpl: loadAsResource: resource.getFilename() = " + resource.getFilename());
            if (resource.exists() || resource.isReadable()) {
                admin.info("RC-REST-API: FilesServiceImpl: loadAsResource: file exists");
                return resource;
            } else {
                admin.error("RC-REST-API: FilesServiceImpl: loadAsResource: file not found");
                return null;
            }
        } catch (MalformedURLException e) {
            admin.error("RC-REST-API: FilesServiceImpl: loadAsResource: Problem: ", e);
        }
        admin.error("RC-REST-API: FilesServiceImpl: loadAsResource: Unknown problem");
        return null;
    }
    @Override
    public OperationResult deleteOneFile(String formId, String filename) {
        OperationResult operationResult = new OperationResult();
        Path file = Paths.get(pathRead() + formId + "/" + filename);
        if (!Files.exists(file)) {
            admin.warn("RC-REST-API: FilesServiceImpl: deleteOneFile: file "+formId+"/"+filename+" not found");
            operationResult.setMessage("File not found ");
            operationResult.setCode(-22);
            return operationResult;
        }
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
            admin.error("RC-REST-API: FilesServiceImpl: deleteOneFile: problem with deleting file " + e);
            operationResult.setMessage("IO Error - File was not deleted");
            operationResult.setCode(-28);
            return operationResult;
        }
        operationResult.setMessage("OK. File deleted");
        operationResult.setCode(0);
        return operationResult;
    }

    private String pathRead() {
        String file = "D:\java\" + "survey.init";
        String OS;
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(file);
        if (Files.exists(path)) {
            try {
                lines = Files.readAllLines(path);
            } catch (IOException e) {
                e.printStackTrace();
                admin.error("RC-REST-API: FilesServiceImpl: pathRead: probles with reading file .init " + e);
            }
        }
        if (INIT_FOLDER.startsWith("/")) {
            OS = "LINUX";
        } else
            OS = "WIN";
        List<String> temp = lines.stream().filter(p -> p.startsWith("FILE_FOLDER_" + OS)).map(p -> p.substring(p.indexOf(":") + 2)).collect(Collectors.toList());
        return temp.get(0);
    }
*/

}