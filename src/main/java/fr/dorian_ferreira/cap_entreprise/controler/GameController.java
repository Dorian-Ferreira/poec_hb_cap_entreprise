package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.dto.ReviewGameDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Game;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.GameService;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import fr.dorian_ferreira.cap_entreprise.service.UserService;
import fr.dorian_ferreira.cap_entreprise.utils.FlashMessage;
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
@RequestMapping(name = "AppGame")
public class GameController {

    private GameService gameService;
    private ReviewService reviewService;

    @GetMapping(value = UrlRoute.URL_GAME, name = "list")
    public ModelAndView index(
            ModelAndView mav,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "publishedAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            @RequestParam(value="search",required = false) String search
    ) {
        mav.setViewName("game/list");
        mav.addObject("games", gameService.findAll(pageable, search));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_GAME_RANDOM, name = "show")
    public ModelAndView show(
            ModelAndView mav
    ) {
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + gameService.getRandom().getSlug());
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_GAME + "/{slug}", name = "show")
    public ModelAndView show(
            @ModelAttribute("flashMessage") FlashMessage flashMessage,
            @PathVariable String slug,
            ModelAndView mav,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {

        mav.setViewName("game/show");
        Game game = gameService.findBySlug(slug);
        mav.addObject("flashMessage", flashMessage);
        mav.addObject("game", game);
        mav.addObject("averageRating", gameService.getAverageRating(game));
        mav.addObject("reviews", reviewService.findByGame(game, pageable));
        mav.addObject("reviewDTO", new ReviewGameDTO());
        return mav;
    }

    @PostMapping(path = UrlRoute.URL_GAME + "/{slug}", name = "showPost")
    public ModelAndView showPost(
            @PathVariable String slug,
            ModelAndView mav,
            @ModelAttribute("reviewDto") @Valid ReviewGameDTO reviewDto,
            BindingResult result,
            Principal principal,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            RedirectAttributes redirectAttributes
    ) {
        Game game = gameService.findBySlug(slug);
        if (result.hasErrors()) {
            mav.setViewName("game/show");
            return setUp(mav, game, pageable);
        }

        reviewService.persist(reviewDto, game, principal);

        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + slug);

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "Votre commentaire a bien été enregistré, il est actuellement en attente de modération !")
        );
        return mav;
    }

    private ModelAndView setUp(ModelAndView mav, Game game, Pageable pageable) {
        mav.addObject("game", game);
        mav.addObject("reviews", reviewService.findByGame(game, pageable));
        mav.addObject("reviewDTO", new ReviewGameDTO());
        return mav;
    }
}
