package ru.itis.pethome.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.controller.api.ImageApi;
import ru.itis.pethome.dto.response.ImageResponse;
import ru.itis.pethome.model.Image;
import ru.itis.pethome.service.ImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImageController implements ImageApi {

    private final ImageService imageService;
    @Override
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {

        String path = new File(".").getCanonicalFile() + File.separator + "images" + File.separator;

        File file = new File(path + imageName);
        byte[] content = Files.readAllBytes(file.toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(content);
    }

    @Override
    public ImageResponse addImage(HttpServletRequest request) throws IOException, ServletException {

        String path = new File(".").getCanonicalFile() + File.separator + "images" + File.separator;

        Part file = request.getParts().stream().findFirst().orElseThrow();
        String fileName = extractFileName(file);
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        ImageResponse imageResponse = imageService.addImage(Image.builder()
                .oldName(fileName)
                .type(extension)
                .weight(file.getSize())
                .build());

        file.write(path + File.separator + imageResponse.getPath());

        return imageResponse;
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";

    }
}
