/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;


import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {
    
    public static EmployeeService employeeService = null;
    
    public static EmployeeService getInstance()
    {
        if(employeeService==null)
        {
            return new EmployeeService();
        }
        else
        {
            return employeeService;
        }
    }
    
     public static ArrayList getAllEmployees() {

        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select employeesId,firstName,lastName,age,basicSalary,carAllowance,address,roleName,departmentName,gender,phone from employeedb.employees emp inner join employeedb.roles role on emp.roleId=role.roleId inner join employeedb.department dpt on emp.departmentId=dpt.departmentId ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employeesId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllaowance(rs.getString("carAllowance"));

                empList.add(emp);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Number of employees = " + empList.size());
        return empList;
    }
      public static ArrayList searchEmployee(Employee emp) {
        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getConnection();
             String sql = "select * from employees e, department d, roles r where e.departmentId=d.departmentId and e.roleId=r.roleId "
                    + "having firstName like ?"
                    + "and lastName like ?"
                    + "and gender like ?"
                    + "and phone like ?"
                    + "and departmentName like ?"
                    + "and roleName like ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,emp.getFirstName()+"%");
            preparedStatement.setString(2,emp.getLastName()+"%");
            preparedStatement.setString(3,emp.getGender()+"%");
            preparedStatement.setString(4,emp.getPhone()+"%");
            preparedStatement.setString(5,emp.getDepartmentName()+"%");
            preparedStatement.setString(6,emp.getRoleName()+"%");
            System.out.println("Prepared statement = "+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee emp1 = new Employee();
                emp1.setAddress(rs.getString("address"));
                emp1.setEmployeeId(rs.getString("employeesId"));
                emp1.setFirstName(rs.getString("firstName"));
                emp1.setLastName(rs.getString("lastName"));
                emp1.setPhone(rs.getString("phone"));
                emp1.setGender(rs.getString("gender"));
                emp1.setAge(rs.getString("age"));
                emp1.setDepartmentName(rs.getString("departmentName"));
                emp1.setRoleName(rs.getString("roleName"));
                emp1.setBasicSalary(rs.getString("basicSalary"));
                emp1.setCarAllaowance(rs.getString("carAllowance"));

                empList.add(emp1);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        System.out.println("Number of employees = " + empList.size());
        return empList;
//To change body of generated methods, choose Tools | Templates.

    }

     public  boolean InsertEmployee(Employee emp) {
      boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO employeedb.employees (firstName, lastName,phone,address,gender,age,basicSalary,carAllowance,departmentId,roleId)"
                    + " VALUES (?, ?, ?,?,?,?,?,?,?,?)";

            
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setDouble(7, Double.parseDouble(emp.getBasicSalary()));
            preparedStatement.setDouble(8, Double.parseDouble(emp.getCarAllaowance()));
           //reparedStatement.setString(9, emp.getDepartmentId());
           //reparedStatement.setString(10, emp.getRoleId());
             preparedStatement.setString(9, emp.getDepartmentId());
              preparedStatement.setString(10, emp.getRoleId());
           System.out.println("preparedstatement :" +preparedStatement);
              int row = preparedStatement.executeUpdate();

            if(row==1)
            {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
     public  Employee getEmployee(String employeeId) {

        Employee emp = new Employee();

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, department d, roles r "
                    + "where e.departmentId=d.departmentId and e.roleId=r.roleId and  e.employeesId =?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);

            ResultSet rs = preparedStatement.executeQuery();
             System.out.println("preparedstatement :" +preparedStatement);
            

            while (rs.next()) {

                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employeesId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllaowance(rs.getString("carAllowance"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return emp;

    }
     public  boolean updateEmployee(Employee emp) {

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employeedb.employees\n"
                    + "SET firstName = ? , lastName = ? , phone = ? , address = ? ,\n"
                    + "gender = ? , age = ? , basicSalary = ?,carAllowance = ?, departmentId=?,roleId=?\n"
                    + "WHERE employeesId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setDouble(7, Double.parseDouble(emp.getBasicSalary()));
            preparedStatement.setDouble(8, Double.parseDouble(emp.getCarAllaowance()));
            preparedStatement.setString(9, emp.getDepartmentId());
            preparedStatement.setString(10, emp.getRoleId());
            
            
                       
            preparedStatement.setString(11,emp.getEmployeeId());
            
            int row = preparedStatement.executeUpdate();

            if(row==1)
            {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
}
