/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author colby
 */
public class LanguageStrings implements ILanguageStrings {

    public static HashMap<String, String> languageStringsMap = null;
    
    
    private static void initializeLanguageStrings() {
        //English App Phrases
        addString("English", "Login", "Login to Appointment Manager");

        //Spanish App Phrases
        addString("Spanish", "Login", "Inicie sesión en el Administrador de Citas");

        //French App Phrases
        addString("French", "Login", "Connectez-vous au Gestionnaire de Rendez-Vous");

    }

    // So we don't have to worry about initializing, it will automatically
    // do it for us.
    public static String getString(String language, String appString) {
        if (languageStringsMap == null) initializeLanguageStrings();

        return languageStringsMap.get(language + "_" + appString);
    }

    private void addString(String language, String appString, String phrase) {
        languageStringsMap.put(language + "_" + appString, phrase);
    }



}
