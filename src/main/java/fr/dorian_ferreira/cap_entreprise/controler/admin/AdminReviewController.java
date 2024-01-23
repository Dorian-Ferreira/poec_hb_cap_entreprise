package fr.dorian_ferreira.cap_entreprise.controler.admin;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import fr.dorian_ferreira.cap_entreprise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppAdminReview")
public class AdminReviewController {

    private ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW + "/{id}" + "/accept")
    public ModelAndView accept(
            @PathVariable Long id,
            ModelAndView mav,
            Principal principal
    ) {
        reviewService.validate(id, principal);

        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW + "/{id}" + "/refuse")
    public ModelAndView refuse(
            @PathVariable Long id,
            ModelAndView mav
    ) {
        reviewService.refuse(id);

        mav.setViewName("redirect:/");
        return mav;
    }
}
