package fr.dorian_ferreira.cap_entreprise.controler.admin;

import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppAdminReview")
public class AdminReviewController {

    private ReviewService reviewService;

    @GetMapping(value = UrlRoute.URL_ADMIN_REVIEW, name = "index")
    public ModelAndView index(
            ModelAndView mav,
            Principal principal,
            @PageableDefault(
                    size = 5, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        mav.setViewName("admin/review/list");
        mav.addObject("reviews", reviewService.findAll(pageable));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW + "/{id}" + "/accept")
    public ModelAndView accept(
            @PathVariable Long id,
            ModelAndView mav,
            Principal principal
    ) {
        reviewService.validate(id, principal);

        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_REVIEW);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW + "/{id}" + "/refuse")
    public ModelAndView refuse(
            @PathVariable Long id,
            ModelAndView mav
    ) {
        reviewService.refuse(id);

        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_REVIEW);
        return mav;
    }
}
