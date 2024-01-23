package fr.dorian_ferreira.cap_entreprise.mapping;

public class UrlRoute {

    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTER = "/register";
    public final static String URL_ADMIN = "/admin";

    public final static String URL_GAME = "/game";
    public final static String URL_REVIEW = "/review";
    public final static String URL_USER = "/user";

    public final static String URL_NEW = "/new";
    public final static String URL_EDIT = "/edit";

    public static final String URL_REVIEW_NEW = URL_REVIEW + URL_NEW;
    public static final String URL_ADMIN_GAME = URL_ADMIN + URL_GAME;

    public final static String URL_ADMIN_GAME_NEW = URL_ADMIN_GAME + URL_NEW;
    public final static String URL_ADMIN_GAME_EDIT = URL_ADMIN_GAME + URL_EDIT;

    public final static String URL_ADMIN_REVIEW = URL_ADMIN + URL_REVIEW;

}
