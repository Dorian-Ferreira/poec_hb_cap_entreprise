package fr.dorian_ferreira.cap_entreprise.controler.admin;

import fr.dorian_ferreira.cap_entreprise.dto.GameDTO;
import fr.dorian_ferreira.cap_entreprise.dto.PublisherDTO;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.*;
import fr.dorian_ferreira.cap_entreprise.utils.FlashMessage;
import fr.dorian_ferreira.cap_entreprise.utils.ImageUploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppAdminPublisher")
public class AdminPublisherController {

    private final PublisherService publisherService;

    @GetMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new PublisherDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_NEW, name = "newHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("publisher") PublisherDTO dto,
        BindingResult result,
        ModelAndView mav,
        RedirectAttributes redirectAttributes
    ) {
        return formHandle(result, mav, dto, redirectAttributes);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            PublisherDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("admin/publisher/form");
        mav.addObject("publisher", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            PublisherDTO dto,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("admin/publisher/form");
            return mav;
        }

        publisherService.persist(dto);

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "L'éditeur a bien été enregistré!")
        );
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_GAME_NEW);
        return mav;
    }
}
