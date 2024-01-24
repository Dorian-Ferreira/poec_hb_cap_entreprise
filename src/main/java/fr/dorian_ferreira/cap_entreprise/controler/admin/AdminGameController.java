package fr.dorian_ferreira.cap_entreprise.controler.admin;

import fr.dorian_ferreira.cap_entreprise.dto.GameDTO;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import fr.dorian_ferreira.cap_entreprise.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppAdminGame")
public class AdminGameController {

    private final GameService gameService;

    private final ImageUploadService imageUploadService;

    private final PlatformService platformService;
    private final PublisherService publisherService;
    private final GenreService genreService;
    private final BusinessModelService businessModelService;
    private final ClassificationService classificationService;

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new GameDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_EDIT + "/{id}", name = "edit")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                gameService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_DELETE + "/{id}")
    public ModelAndView delete(
            @PathVariable Long id,
            ModelAndView mav
    ) {
        gameService.delete(id);

        mav.setViewName("redirect:/game");
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_IMAGE + "/{id}")
    public ModelAndView imageHandler(
            ModelAndView mav,
            @PathVariable Long id
    ) {
        mav.setViewName("admin/game/image_upload");
        mav.addObject("game", gameService.findById(id));
        return mav;
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GAME_IMAGE + "/{id}")
    public ModelAndView fileUpload(
            @RequestParam("file") MultipartFile file,
            ModelAndView mav,
            @PathVariable Long id) {
        String imagePath = imageUploadService.uploadImage("jeu/", file);

        gameService.addImage(id, imagePath);

        mav.setViewName("redirect:/game");
        return mav;
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GAME_NEW, name = "newHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("game") GameDTO platformDTO,
        BindingResult result,
        ModelAndView mav,
        Principal principal
    ) {
        return formHandle(result, mav, platformDTO, null, principal);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GAME_EDIT + "/{id}", name = "editHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("game") GameDTO platformDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id,
        Principal principal
    ) {
        return formHandle(result, mav, platformDTO, id, principal);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            GameDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("admin/game/form");
        mav.addObject("game", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return setup(mav);
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            GameDTO dto,
            Long id,
            Principal principal
    ) {
        if (result.hasErrors()) {
            mav.setViewName("admin/game/form");
            return setup(mav);
        }
        gameService.persist(dto, id, principal);
        mav.setViewName("redirect:" + UrlRoute.URL_GAME);
        return mav;
    }

    private ModelAndView setup(ModelAndView mav) {
        mav.addObject("platforms", platformService.findAll());
        mav.addObject("publishers", publisherService.findAll());
        mav.addObject("genres", genreService.findAll());
        mav.addObject("businessModels", businessModelService.findAll());
        mav.addObject("classifications", classificationService.findAll());
        return mav;
    }
}
