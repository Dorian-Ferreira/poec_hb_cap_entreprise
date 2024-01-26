package fr.dorian_ferreira.cap_entreprise.utils;

import fr.dorian_ferreira.cap_entreprise.entity.Review;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import lombok.AllArgsConstructor;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExcelReviewService {

    private ReviewService reviewService;

    private JspUtils jspUtils;

    private DateUtils dateUtils;

    public File writeExcel() {
        UUID uuid = UUID.randomUUID();
        String fileName = "export-avis-" + uuid + ".xlsx";
        File dir = new File(System.getProperty("catalina.home") + File.separator + "export");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
        try (OutputStream os = new FileOutputStream(serverFile)) {
            Workbook wb = new Workbook(os, "Export-Avis", "1.0");
            Worksheet ws = wb.newWorksheet("Avis"); // La feuille à l'intérieur de l'excel

            ws.value(0, 0, "Date d'envoi");
            ws.value(0, 1, "Jeu");
            ws.value(0, 2, "Joueur");
            ws.value(0, 3, "Note");
            ws.value(0, 4, "Statut");
            ws.value(0, 5, "Date modération");

            List<Review> reviews = reviewService.findAll();
            for (int i = 1; i <= reviews.size(); i++) {
                Review review = reviews.get(i-1);
                boolean isModerated = review.getModerator() == null;
                ws.value(i, 0, dateUtils.getDateFormat(review.getCreatedAt(), "dd/MM/YYYY"));
                ws.value(i, 1, review.getGame().getName());
                ws.value(i, 2, review.getWriter().getNickname());
                ws.value(i, 3, jspUtils.getStringRating(review.getRating()));
                ws.value(i, 4, isModerated ? "À modérer" : "Modéré");
                ws.value(i, 5, isModerated ? "" : dateUtils.getDateFormat(review.getModeratedAt(), "dd/MM/YYYY"));
            }
            wb.finish();
            return serverFile;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
