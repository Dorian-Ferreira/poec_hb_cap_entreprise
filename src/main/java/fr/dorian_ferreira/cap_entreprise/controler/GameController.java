package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.GameService;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import fr.dorian_ferreira.cap_entreprise.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppGame")
public class GameController {

    private GameService gameService;
    private UserService userService;

    @GetMapping(value = UrlRoute.URL_GAME, name = "list")
    public ModelAndView index(
            ModelAndView mav,
            Principal principal
    ) {
        mav.setViewName("game/list");
        mav.addObject("games", gameService.findAllAvailable(userService.findByName(principal.getName())));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_GAME + "/{id}", name = "show")
    public ModelAndView show(
            @PathVariable Long id,
            ModelAndView mav
    ) {
        mav.setViewName("game/show");
        mav.addObject("game", gameService.getObjectById(id));
        return mav;
    }


}
