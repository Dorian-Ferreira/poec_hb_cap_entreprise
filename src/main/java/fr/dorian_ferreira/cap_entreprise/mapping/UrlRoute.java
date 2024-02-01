package fr.dorian_ferreira.cap_entreprise.mapping;

public class UrlRoute {

    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REDIRECT = "/redirect";
    public final static String URL_REGISTER = "/register";
    public final static String URL_ADMIN = "/admin";

    public final static String URL_GAME = "/jeu";
    public final static String URL_REVIEW = "/avis";
    public final static String URL_EXPORT= "/telecharger-export-excel";

    public final static String URL_NEW = "/nouveau";
    public final static String URL_EDIT = "/changer";

    public static final String URL_REVIEW_NEW = URL_REVIEW + URL_NEW;
    public static final String URL_ADMIN_GAME = URL_ADMIN + URL_GAME;

    public final static String URL_GAME_RANDOM = URL_GAME + "/random";
    public final static String URL_ADMIN_GAME_NEW = URL_ADMIN_GAME + URL_NEW;
    public final static String URL_ADMIN_GAME_EDIT = URL_ADMIN_GAME + URL_EDIT;
    public final static String URL_ADMIN_GAME_DELETE = URL_ADMIN_GAME + "/supprimer";
    public final static String URL_ADMIN_GAME_IMAGE = URL_ADMIN_GAME + "/image";

    public final static String URL_ADMIN_REVIEW = URL_ADMIN + URL_REVIEW;
    public final static String URL_ADMIN_PUBLISHER_NEW = URL_ADMIN + "/editeur" + URL_NEW;

}
