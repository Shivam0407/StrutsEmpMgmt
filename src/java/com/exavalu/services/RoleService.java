/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exavalu.services;

import com.exavalu.models.Role;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author itssh
 */
public class RoleService {
     public static ArrayList getAllRole() {
        ArrayList roleLIst = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getConnection();

            String sql = "Select * from roles";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Role role=new Role();
                
                role.setRoleId(rs.getInt("roleId"));
                role.setRoleName(rs.getString("roleName"));
                
                roleLIst.add(role);
                
            }

        } catch (SQLException ex) {
          Logger log = Logger.getLogger(EmployeeService.class.getName());
            log.error("Error Message:" +ex.getMessage());
        }

        return roleLIst;
    }
    
}
