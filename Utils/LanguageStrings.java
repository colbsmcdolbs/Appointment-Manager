/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.HashMap;
import Interfaces.ILanguageStrings;
import java.util.Locale;

/**
 *
 * @author colby
 */
public class LanguageStrings implements ILanguageStrings {

    public static HashMap<String, String> languageStringsMap = null;
    public static String userLanguage = null;
    
    
    private static void initializeLanguageStrings() {
        //English App Phrases
        addString("English", "Login", "Login to Appointment Manager");

        //Spanish App Phrases
        addString("Spanish", "Login", "Inicie sesión en el Administrador de Citas");

        //French App Phrases
        addString("French", "Login", "Connectez-vous au Gestionnaire de Rendez-Vous");

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
