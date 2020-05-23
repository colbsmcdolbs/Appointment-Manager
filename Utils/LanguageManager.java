package Utils;

import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author colby
 */
public class LanguageManager {

    private static HashMap<String, String> languageStringsMap = null;
    private static String userLanguage = null;
    
    
    private static void initializeLanguageStrings() {
        
        languageStringsMap = new HashMap<>();
        //English App Phrases
        addString("English", "LoginWelcome", "Welcome to Appointment Manager");
        addString("English", "Login", "Login");
        addString("English", "Cancel", "Cancel");
        addString("English", "Username", "Username");
        addString("English", "Password", "Password");
        addString("English", "AllFieldsError", "Error: All fields are required");
        addString("English", "WrongUsernamePassError", "Error: Username or Password Incorrect");

        //Spanish App Phrases
        addString("Spanish", "LoginWelcome", "Inicie sesión en el Administrador de Citas");
        addString("Spanish", "Login", "Conexion");
        addString("Spanish", "Cancel", "Cancelar");
        addString("Spanish", "Username", "Nombre de Usuario");
        addString("Spanish", "Password", "Clave");
        addString("Spanish", "AllFieldsError", "Error: Todos los campos son obligatorios");
        addString("Spanish", "WrongUsernamePassError", "Error: Nombre de Usuario o Clave Incorrecta");
    }
    /**
     * To avoid any muckups, this will automatically set the system language to English.
     */
    private static void initializeUserLanguage() {
        switch(Locale.getDefault().getLanguage()) {
            case "fr":
                userLanguage = "French";
                break;
            case "sp":
                userLanguage = "Spanish";
                break;
            default:
                userLanguage = "English";
                break;
        }
    }

    // So we don't have to worry about initializing, it will automatically
    // do it for us.
    public static String getString(String appString) {
        if (languageStringsMap == null) initializeLanguageStrings();
        if (userLanguage == null) initializeUserLanguage();

        return languageStringsMap.get(userLanguage + "_" + appString);
    }

    private static void addString(String language, String appString, String phrase) {
        languageStringsMap.put(language + "_" + appString, phrase);
    }
}
