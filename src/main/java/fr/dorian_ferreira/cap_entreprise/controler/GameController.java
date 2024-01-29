package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.GameService;
import fr.dorian_ferreira.cap_entreprise.service.UserService;
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
@RequestMapping(name = "AppGame")
public class GameController {

    private GameService gameService;

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

    @GetMapping(path = UrlRoute.URL_GAME + "/{id}", name = "show")
    public ModelAndView show(
            @PathVariable Long id,
            ModelAndView mav
    ) {
        mav.setViewName("game/show");
        mav.addObject("game", gameService.findById(id));
        return mav;
    }
}
