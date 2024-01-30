package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppReview")
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;

    private GameService gameService;

    @GetMapping(value = UrlRoute.URL_REVIEW, name = "index")
    public ModelAndView index(
            ModelAndView mav,
            Principal principal,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            @RequestParam(value="search",required = false) String search

    ) {
        if(principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("review/index");
        mav.addObject("reviews", reviewService.findAllFiltered(search, search, principal, pageable, null));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_REVIEW + "/{id}", name = "show")
    public ModelAndView show(
            @PathVariable Long id,
            ModelAndView mav
    ) {
        mav.setViewName("review/show");
        mav.addObject("review", reviewService.findById(id));
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_REVIEW_NEW + "/{slug}", name = "createForGame")
    public ModelAndView createReviewForGame(
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            @PathVariable String slug
    ) {
        mav.setViewName("review/form");

        ReviewDTO r = new ReviewDTO();
        r.setGame(gameService.findBySlug(slug));

        return setUp(mav, r, httpServletRequest.getRequestURI());
    }

    @GetMapping(value = UrlRoute.URL_REVIEW_NEW, name = "create")
    public ModelAndView createReview(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        mav.setViewName("review/form");
        return setUp(mav, new ReviewDTO(), httpServletRequest.getRequestURI());
    }

    @PostMapping(value = UrlRoute.URL_REVIEW_NEW, name = "createHandler")
    public ModelAndView createReview(
            @ModelAttribute("reviewDto") @Valid ReviewDTO reviewDto,
            BindingResult result,
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            Principal principal
    ) {
        if (result.hasErrors()) {
            mav.setViewName("review/form");
            return setUp(mav, reviewDto, httpServletRequest.getRequestURI());
        }

        reviewService.persist(reviewDto, null, principal);
        mav.setViewName("redirect:/");
        return mav;
    }
    @PostMapping(value = UrlRoute.URL_REVIEW_NEW + "/{slug}", name = "createHandler")
    public ModelAndView createReview(
            @ModelAttribute("reviewDto") @Valid ReviewDTO reviewDto,
            BindingResult result,
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            Principal principal,
            @PathVariable String slug
    ) {
        if (result.hasErrors()) {
            mav.setViewName("review/form");
            return setUp(mav, reviewDto, httpServletRequest.getRequestURI());
        }

        reviewService.persist(reviewDto, null, principal);
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW);
        return mav;
    }

    private ModelAndView setUp(ModelAndView mav, ReviewDTO reviewDTO, String uri) {
        mav.addObject("action", uri);
        mav.addObject("games", gameService.findAll());
        mav.addObject("reviewDto", reviewDTO);
        return mav;
    }

}
