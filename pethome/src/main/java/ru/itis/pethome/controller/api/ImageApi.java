package ru.itis.pethome.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.dto.response.ImageResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:3000")
public interface ImageApi {

    @GetMapping("/{imageName}")
    ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException;

    @PostMapping
    ImageResponse addImage(HttpServletRequest request) throws IOException, ServletException;
}