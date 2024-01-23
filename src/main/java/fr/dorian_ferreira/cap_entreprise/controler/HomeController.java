package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.ReviewService;
import fr.dorian_ferreira.cap_entreprise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(path = "/", name = "AppHome")
@AllArgsConstructor
public class HomeController {

    private UserService userService;
    private ReviewService reviewService;

    @GetMapping(name = "index")
    public ModelAndView index(ModelAndView mav, Principal principal) {
        if(principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("index");
        mav.addObject("reviews", reviewService.findAllAvailable(userService.findByName(principal.getName())));
        return mav;
    }

}
