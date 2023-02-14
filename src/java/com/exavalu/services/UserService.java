/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class UserService {

    public static UserService userService = null;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            return new UserService();
        } else {
            return userService;
        }
    }

    public boolean doSignUp(User emp) {
      
          boolean result = false;
        boolean success=false;

        Connection con = JDBCConnectionManager.getConnection();
        String sql = "INSERT INTO employeedb.users\n"
                + "(emailAddress,\n"
                + "password,\n"
                + "firstName,\n"
                + "lastName,\n"
                + "status,\n"
                + "phoneNumber,\n"
                + "addressLine1,\n"
                + "addressLine2,\n"
                + "countryId,\n"
                + "stateId,\n"
                + "districtId)VALUES (?, ?, ?, ?, ?,?,?, ?, ?, ?, ?);";

        try {
            //System.out.println("entering try block");
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, emp.getEmail());
            preparedStatement.setString(2, emp.getPassword());
            preparedStatement.setString(3, emp.getFirstName());
            preparedStatement.setString(4, emp.getLastName());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, emp.getPhoneNumber());
            preparedStatement.setString(7, emp.getAddressLine1());
            preparedStatement.setString(8, emp.getAddressLine2());
            preparedStatement.setInt(9, emp.getCountryId());
            preparedStatement.setInt(10, emp.getStateId());
            preparedStatement.setInt(11, emp.getDistrictId());

             int rows = preparedStatement.executeUpdate();

            if (rows==1) {
                
                result = true;
            }
             con.close();
             System.out.println("LoginService :: "+preparedStatement);

            
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next())
            {
                success = true;
            }

            //con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }
}
