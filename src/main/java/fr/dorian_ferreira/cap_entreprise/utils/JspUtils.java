package fr.dorian_ferreira.cap_entreprise.utils;

import fr.dorian_ferreira.cap_entreprise.entity.Platform;
import fr.dorian_ferreira.cap_entreprise.mapping.UrlRoute;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class JspUtils {

    public String excerpt(String text, int size) {
        if (text.length() <= size) return text;
        String finalText = text.substring(0, size) + "...";
        if (!finalText.contains("</strong>")) {
            finalText += "</strong>";
        }
        return finalText;
    }

    public String getCssClas(float rating) {
        if (rating <= 5) return "rating-5";
        if (rating <= 10) return "rating-10";
        if (rating <= 15) return "rating-15";
        if (rating < 20) return "rating-19";
        return "rating-20";
    }

    public String getBorderCssClass(boolean moderated) {
        return moderated ? "main-review-border-green" : "main-review-border-red";
    }

    public String getStringRating(float rating) {
        return ("" + rating).replace(".0", "");
    }

    public String getPlatformsString(List<Platform> platformList) {
        String s = "";
        for(Platform p : platformList) {
            if(!s.contains(p.getImage()))
            {
                s += "<p class=\"mx-2 pt-2\">" + p.getImage() + "</p>";
            }
        }
        return s;
    }

    /**
     * Génère une URL, à partir d'une URL contenant, possiblement, des query Param
     * et les nouveaux query params
     *
     * @param currentUrl, l'URL de la page courante (le path !)
     * @param addQueryParams, les paramètres de requêtes à ajouter
     * @return la requête formattée correctement avec les anciens et nouveaux query params
     */
    public String generateUrlFrom(
            String currentUrl,
            String... addQueryParams
    ) {
        // String... :  la méthode prend autant de paramètres de type String que voulu
        // Et les ajoute automatiquement dans une Liste
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(currentUrl);
        for (String queryParam : addQueryParams) {
            if (!queryParam.isEmpty()) {
                if (queryParam.contains("&")) { // existing old query param
                    String[] oldQueryParams = queryParam.split("&");
                    for (String oldQueryParamSplit : oldQueryParams) {
                        String[] parsed = oldQueryParamSplit.split("=");
                        url = addQueryParam(url, parsed[0], parsed[1]);
                    }
                } else {
                    String[] parsed = queryParam.split("=");
                    url = addQueryParam(url, parsed[0], parsed[1]);
                }
            }
        }
        return url.toUriString();
    }

    private UriComponentsBuilder addQueryParam(UriComponentsBuilder uri, String queryParamName, String queryParamValue) {
        if (queryParamName.equals("sort") &&
                !uri.toUriString().contains(queryParamValue.split(",")[0])
        ) {
            return uri.queryParam(queryParamName, queryParamValue);
        }
        return uri.replaceQueryParam(queryParamName, queryParamValue);
    }
}
