package fr.dorian_ferreira.cap_entreprise.controler;

import fr.dorian_ferreira.cap_entreprise.dto.UserDTO;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class SecurityController {

    private UserService userService;

    @GetMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(ModelAndView mav) {
        mav.setViewName("security/register");
        mav.addObject("userForm", new UserDTO());
        return mav;
    }

    @GetMapping(UrlRoute.URL_REDIRECT)
    public ModelAndView redirect(ModelAndView mav,
                                 Principal principal) {
        if(userService.isAdmin(principal)) {
            mav.setViewName("redirect:"+UrlRoute.URL_REVIEW + "?moderation=2");
        } else {
            mav.setViewName("redirect:/");
        }

        return mav;
    }

    @PostMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(
            @ModelAttribute("userForm") @Valid UserDTO userForm,
            BindingResult bindingResult,
            ModelAndView mav
    ) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("security/register");
            return mav;
        }
        userService.persist(userForm);
        mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_LOGIN)
    public ModelAndView login(ModelAndView mav, String error, Principal principal) {
        if(principal != null) {
            mav.setViewName("redirect:/");
            return mav;
        }
        if (error != null) {
            mav.addObject("error", "Votre nom d'utilisateur ou mot de passe est invalide.");
        }
        mav.setViewName("security/login");
        return mav;
    }

}
