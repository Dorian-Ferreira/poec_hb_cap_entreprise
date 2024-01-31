package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.service.GameService;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import fr.dorian_ferreira.cap_entreprise.service.UserService;
import fr.dorian_ferreira.cap_entreprise.utils.FlashMessage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(path = "/", name = "AppHome")
@AllArgsConstructor
public class HomeController {

    private UserService userService;
    private ReviewService reviewService;
    private GameService gameService;

    @GetMapping(name = "index")
    public ModelAndView index(
            @ModelAttribute("flashMessage") FlashMessage flashMessage,
            ModelAndView mav,
            Principal principal
    ) {
        if(principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("index");
        mav.addObject("flashMessage", flashMessage);
        mav.addObject("reviews", reviewService.find6HighRateReview());
        mav.addObject("games", gameService.find(5));
        return mav;
    }

}
