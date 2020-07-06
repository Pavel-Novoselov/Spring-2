package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.model.Picture;
import ru.geekbrains.model.Product;
import ru.geekbrains.repo.PictureRepository;
import ru.geekbrains.service.FilesService;
import ru.geekbrains.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private final PictureRepository pictureRepository;

    private final FilesService filesService;

    private final ProductService productService;

    @Autowired
    public PictureController(PictureRepository pictureRepository, FilesService filesService, ProductService productService) {
        this.pictureRepository = pictureRepository;
        this.filesService = filesService;
        this.productService = productService;
    }

    @GetMapping("/picture/{pictureId}")
    public void adminDownloadProductPicture(@PathVariable("pictureId") Long pictureId,
                                            HttpServletResponse response) throws IOException {
        logger.info("Picture {}", pictureId);
        Optional<Picture> picture = pictureRepository.findById(pictureId);
        if (picture.isPresent()) {
            response.setContentType(picture.get().getContentType());
            response.getOutputStream().write(picture.get().getPictureData().getData());
        }
    }

    @GetMapping("{id}/new")
    public String createCategory(Model model, @PathVariable("id") Long id) {

        System.out.println("1 step save picture");
        model.addAttribute("product", productService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("picture", new Picture());
        return "product";
    }

    @PostMapping
    public String savePicture(Picture picture, Product product) {
        logger.info("Save category method");

        singleFileUpload(product.getId(), picture);
        return "redirect:/category";
    }

//    @PostMapping("/{form_id}")
//    public ResponseEntity<String> singleFileUpload(@PathVariable(name = "form_id") String formId,
//                                                            @RequestParam("file") MultipartFile file) {
        public ResponseEntity<String> singleFileUpload(long formId,
                Picture file) {
        if (formId == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        System.out.println("starts files upload controller, saving file - " + file.getName() + " for Form ID " + formId);
        String operationResult = filesService.uploadFromPolog(formId, file);
        switch (operationResult.substring(0,15)) {
            case "OK. File upload":
                return new ResponseEntity<>(operationResult, HttpStatus.OK);//0
            case "File is empty. ":
                return new ResponseEntity<>(operationResult, HttpStatus.BAD_REQUEST);//-20
            case "File already ex":
                return new ResponseEntity<>(operationResult, HttpStatus.NOT_ACCEPTABLE);//-21
            case "IO Error - File":
                return new ResponseEntity<>(operationResult, HttpStatus.INTERNAL_SERVER_ERROR);//-29
            default:
                operationResult="Unknown error";
                return new ResponseEntity<>(operationResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
