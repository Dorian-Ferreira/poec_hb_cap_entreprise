package fr.dorian_ferreira.cap_entreprise.controler.admin;

import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping
public class AdminController {

    @GetMapping(path = UrlRoute.URL_ADMIN)
    public ModelAndView admin(ModelAndView mav) {
        mav.setViewName("admin/index");
        return mav;
    }
}
