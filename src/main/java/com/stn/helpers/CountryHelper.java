package com.stn.helpers;

import com.stn.pojo.Country;
import com.stn.utils.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryHelper extends DBConnection {


    public List<Country> getCountries() throws ClassNotFoundException, SQLException {
        List<Country> countries = new ArrayList<Country>();

        query = "SELECT * FROM countries ORDER BY 2 ASC";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Country country = new Country();
                country.setCountryId(resultSet.getInt(1));
                country.setCountryName(resultSet.getString(2));
                country.setCountryImage(resultSet.getString(3));
                countries.add(country);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return countries;
    }

}
