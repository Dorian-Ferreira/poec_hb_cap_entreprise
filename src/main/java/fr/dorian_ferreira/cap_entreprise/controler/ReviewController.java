package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.*;
import fr.dorian_ferreira.cap_entreprise.utils.FlashMessage;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @ModelAttribute("flashMessage") FlashMessage flashMessage,
            ModelAndView mav,
            Principal principal,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            @RequestParam(value = "moderation",required = false) String moderation,
            @RequestParam(value="search",required = false) String search

    ) {
        if(principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }

        if(!userService.isAdmin(principal)) {
            moderation = "";
        }

        mav.setViewName("review/index");
        mav.addObject("flashMessage", flashMessage);
        mav.addObject("reviews", reviewService.findAllFiltered(search, search, principal, pageable, moderation));
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
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("review/form");
            return setUp(mav, reviewDto, httpServletRequest.getRequestURI());
        }

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "Votre commentaire a bien été enregistré, il est actuellement en attente de modération !")
        );

        reviewService.persist(reviewDto, null, principal);
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW);
        return mav;
    }

    @PostMapping(value = UrlRoute.URL_REVIEW_NEW + "/{slug}", name = "createHandler")
    public ModelAndView createReview(
            @ModelAttribute("reviewDto") @Valid ReviewDTO reviewDto,
            BindingResult result,
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            Principal principal,
            @PathVariable String slug,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("review/form");
            return setUp(mav, reviewDto, httpServletRequest.getRequestURI());
        }

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "Votre commentaire a bien été enregistré, il est actuellement en attente de modération !")
        );

        reviewService.persist(reviewDto, null, principal);
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + slug);
        return mav;
    }

    private ModelAndView setUp(ModelAndView mav, ReviewDTO reviewDTO, String uri) {
        mav.addObject("action", uri);
        mav.addObject("games", gameService.findAll());
        mav.addObject("reviewDto", reviewDTO);
        return mav;
    }

}
