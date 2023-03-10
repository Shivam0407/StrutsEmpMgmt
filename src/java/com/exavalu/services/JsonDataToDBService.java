/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exavalu.services;

/**
 *
 * @author itssh
 */
import com.exavalu.models.Transcript;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class JsonDataToDBService {

    public static boolean InsertJsonData(Transcript[] transcript) {

        boolean result = true;

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO transcript (userId,id,title,completed) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            for (Transcript data : transcript) {

                preparedStatement.setString(1, data.getUserId());
                preparedStatement.setString(2, data.getId());
                preparedStatement.setString(3, data.getTitle());
                preparedStatement.setString(4, data.getCompleted());
                //System.out.println("preparedstatement= " + preparedStatement);

                preparedStatement.executeUpdate();
            }

//            if (row == 1) {
//                result = true;
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static boolean InsertJsonData(Transcript transcript) {

        boolean result = false;

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO transcript (userId,id,title,completed) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, transcript.getUserId());
            preparedStatement.setString(2, transcript.getId());
            preparedStatement.setString(3, transcript.getTitle());
            preparedStatement.setString(4, transcript.getCompleted());
            System.out.println("preparedstatement= " + preparedStatement);

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
