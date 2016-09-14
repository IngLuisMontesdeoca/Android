package io.github.ingluismontesdeoca.fragment.io;

/**
 * Created by Aministrador on 18/08/2016.
 */
public class APIConstants {
    /*URL*/
    public static final String URL_BASE = "http://ws.audioscrobbler.com";
    public static final String PATH_VERSION = "/2.0";
    /*PARAMETERS*/
    public static final String PARAM_METHOD = "method";
    public static final String PARAM_FORMAT = "format";
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_USER = "user";
    public static final String PARAM_ARTIST = "artist";

    /*VALUES*/
    public static final String VALUE_METHOD = "chart.gettopartists";
    public static final String VALUE_TOPARTIST_METHOD = "chart.gettopartists";
    public static final String VALUE_GETARTIST_METHOD = "artist.getinfo";

    public static final String VALUE_FORMAT = "json";
    public static final String VALUE_API_KEY = "e644f25d04519caab4e069761ce0116d";
    public static final String VALUE_USER = "rj";

    //http://ws.audioscrobbler.com/2.0/?method=user.getartisttracks&user=rj&artist=metallica&api_key=e644f25d04519caab4e069761ce0116d&format=json
    /*FORMED URL*/
    public static final String URL_HYPED_ARTISTS =
            PATH_VERSION + "/?"    + PARAM_METHOD  + "=" +  VALUE_METHOD +
            "&"     + PARAM_USER    + "=" + VALUE_USER +
            "&"     + PARAM_API_KEY + "=" + VALUE_API_KEY +
            "&"     + PARAM_FORMAT  + "=" + VALUE_FORMAT;


    public static final String URL_TOP_ARTISTS =
            PATH_VERSION + "/?"    + PARAM_METHOD  + "=" +  VALUE_TOPARTIST_METHOD +
                    "&"     + PARAM_USER    + "=" + VALUE_USER +
                    "&"     + PARAM_API_KEY + "=" + VALUE_API_KEY +
                    "&"     + PARAM_FORMAT  + "=" + VALUE_FORMAT;

    public static final String URL_GET_ARTIST =
            PATH_VERSION + "/?"    + PARAM_METHOD  + "=" +  VALUE_GETARTIST_METHOD +
                    "&"     + PARAM_USER    + "=" + VALUE_USER +
                    "&"     + PARAM_API_KEY + "=" + VALUE_API_KEY +
                    "&"     + PARAM_FORMAT  + "=" + VALUE_FORMAT;

    public static String getURLBASE(String method , String apiKey){
        return PATH_VERSION + "/?"    + PARAM_METHOD  + "=" +  method +
                        "&"     + PARAM_USER    + "=" + VALUE_USER +
                        "&"     + PARAM_API_KEY + "=" + apiKey +
                        "&"     + PARAM_FORMAT  + "=" + VALUE_FORMAT;
    }
}
