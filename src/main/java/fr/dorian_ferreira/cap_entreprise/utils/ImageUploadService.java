package fr.dorian_ferreira.cap_entreprise.utils;

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

    public String uploadImage(String path, MultipartFile file, String slug, String format) {
        try {
            File dir = new File(DIR_PATH + PATH + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + slug + format);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            return PATH + path + File.separator + slug + format;
        } catch (IOException e) {
            System.out.println("Failed.");
            return "Error : Something goes wrong...";
        }
    }

}
