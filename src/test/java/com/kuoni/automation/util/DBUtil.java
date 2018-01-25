package com.kuoni.automation.util;

import com.kuoni.automation.teststeps.BaseSteps;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    BaseSteps baseSteps = new BaseSteps();

    public String getPasswordToken(String emailId){
        String token = null;
        String query = "SELECT RESET_PWD_TOKEN FROM dps_user a, fit_traveller b WHERE a.id = b.id and a.email='"+emailId+"'";
        System.out.println("Sql query is "+query);
        ResultSet resultSet = baseSteps.executeDBQuery(query);

        try {
            while(resultSet.next()){
                token = resultSet.getString("RESET_PWD_TOKEN");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Password token for traveler is "+token);
        return token;
    }

}
