package fr.dorian_ferreira.cap_entreprise.mapping;

public class UrlRoute {

    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTER = "/register";
    public final static String URL_ADMIN = "/admin";

    public final static String URL_GAME = "/game";
    public final static String URL_CLASSIFICATION = "/classification";
    public final static String URL_GENRE = "/genre";
    public final static String URL_PLATFORM = "/platform";
    public final static String URL_REVIEW = "/review";
    public final static String URL_PUBLISHER = "/publisher";
    public final static String URL_BUSINESSMODEL = "/businessModel";
    public final static String URL_USER = "/user";

    public final static String URL_NEW = "/new";
    public final static String URL_EDIT = "/edit";

    public static final String URL_REVIEW_FORM = URL_REVIEW + URL_NEW;

    public final static String URL_ADMIN_GAME_NEW = URL_ADMIN + URL_GAME + URL_NEW;
    public final static String URL_ADMIN_GAME_EDIT = URL_ADMIN + URL_GAME + URL_EDIT;

    public final static String URL_ADMIN_REVIEW = URL_ADMIN + URL_REVIEW;

    public final static String URL_ADMIN_CLASSIFICATION_NEW = URL_ADMIN + URL_CLASSIFICATION + URL_NEW;
    public final static String URL_ADMIN_GENRE_NEW = URL_ADMIN + URL_GENRE + URL_NEW;
    public final static String URL_ADMIN_PLATFORM_NEW = URL_ADMIN + URL_PLATFORM + URL_NEW;
    public final static String URL_ADMIN_PUBLISHER_NEW = URL_ADMIN + URL_PUBLISHER + URL_NEW;
    public final static String URL_ADMIN_BUSINESSMODEL_NEW = URL_ADMIN + URL_BUSINESSMODEL + URL_NEW;
}
