package DAO;

import Models.City;
import Models.Country;
import Utils.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author colby
 */
public class CountryCityDao {
    
    public static ObservableList<City> getAllCities() {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String cityQuery = "SELECT * FROM city;";
            ResultSet cityResult = connection.executeQuery(cityQuery);
            
            ObservableList<City> cityList = FXCollections.observableArrayList();
            
            while(cityResult.next()) {
                int cityId = cityResult.getInt("cityId");
                String cityName = cityResult.getString("city");
                int countryId = cityResult.getInt("countryId");
                City city = new City(cityId, cityName, countryId);
                cityList.add(city);
            }
            
            return cityList;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static ObservableList<Country> getAllCountries() {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String countryQuery = "SELECT * FROM country;";
            ResultSet countryResult = connection.executeQuery(countryQuery);
            
            ObservableList<Country> countryList = FXCollections.observableArrayList();
            
            while(countryResult.next()) {
                int countryId = countryResult.getInt("countryId");
                String countryName = countryResult.getString("country");
                Country country = new Country(countryId, countryName);
                countryList.add(country);
            }
            
            return countryList;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
}
