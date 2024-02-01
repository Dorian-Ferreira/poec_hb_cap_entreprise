package fr.dorian_ferreira.cap_entreprise.controler.admin;

import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import fr.dorian_ferreira.cap_entreprise.utils.ExcelReviewService;
import fr.dorian_ferreira.cap_entreprise.utils.FlashMessage;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppAdminReview")
public class AdminReviewController {

    private ReviewService reviewService;
    private ExcelReviewService excelService;

    @GetMapping(value = UrlRoute.URL_ADMIN_REVIEW, name = "index")
    public ModelAndView index(
            ModelAndView mav,
            @PageableDefault(
                    size = 5, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC

            ) Pageable pageable,
            @RequestParam(value = "moderation",required = false, defaultValue = "2") String moderation,
            @RequestParam(value="search",required = false) String search,
            Principal principal
    ) {
        mav.setViewName("admin/review/list");
        mav.addObject("reviews", reviewService.findAllFiltered(search, search, principal, pageable, moderation));
        return mav;
    }

    @GetMapping(UrlRoute.URL_EXPORT)
    public void downloadExcel(HttpServletResponse response) {
        try {
            File file = excelService.writeExcel();
            ByteArrayInputStream excelToByte = new ByteArrayInputStream(
                    Files.readAllBytes(Paths.get(file.getAbsolutePath()))
            );
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(excelToByte, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW + "/{id}" + "/{outcome}")
    public ModelAndView confirmReview(
            @PathVariable Long id,
            @PathVariable String outcome,
            ModelAndView mav,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {

        FlashMessage flashMessage = new FlashMessage();

        if(outcome.equals("accept")) {
            reviewService.validate(id, principal);
            flashMessage = new FlashMessage("success", "Le commentaire a bien été modéré !");
        } else if(outcome.equals(("refuse"))) {
            reviewService.refuse(id);
            flashMessage = new FlashMessage("warning", "Le commentaire a bien été supprimé !");
        }
        redirectAttributes.addFlashAttribute("flashMessage", flashMessage);

        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW + "?moderation=2");

        return mav;
    }

}
