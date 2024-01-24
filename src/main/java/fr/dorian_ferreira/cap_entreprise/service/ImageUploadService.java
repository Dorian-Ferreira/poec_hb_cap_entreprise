package fr.dorian_ferreira.cap_entreprise.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageUploadService {

    private static final String DIR_PATH = "src/main/webapp";
    private static final String PATH = "/resources/image/";

    public String uploadImage(String path, MultipartFile file) {
        try {
            File dir = new File(DIR_PATH + PATH + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            return PATH + path + file.getOriginalFilename();
        } catch (IOException e) {
            System.out.println("Failed.");
            return "Error : Something goes wrong...";
        }
    }

}
