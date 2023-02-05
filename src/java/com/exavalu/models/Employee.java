/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.exavalu.services.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String gender;
    private String age;
    private String departmentName;
    private String roleName;
    private String basicSalary;
    private String carAllaowance;
    private String departmentId;
    private String roleId;
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        setMap((ApplicationMap) application);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        setSessionMap((SessionMap<String, Object>) (SessionMap) session);
    }

    public String doLogOut() throws Exception {
        String result = "SUCCESS";
        sessionMap.clear();
        return result;
    }

    public String showEmployee() throws Exception {
        String result = "FAILURE";
        ArrayList empList = EmployeeService.getAllEmployees();
        if (empList != null) {
            result = "SUCCESS";
        }

        return result;
    }

    public String search() throws Exception {
        String result = "FAILURE";
        ArrayList empList = EmployeeService.getInstance().searchEmployee(this);
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();
        System.out.println(empList.size());
        if (empList != null) {
            sessionMap.put("EmpList", empList);
            sessionMap.put("DeptList", deptList);
            sessionMap.put("RoleList", roleList);
            result = "SUCCESS";
        }

        return result;
    }

    public String InsertEmployee() throws Exception {
        String result = "FAILURE";
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();
        sessionMap.put("DeptList", deptList);
        sessionMap.put("RoleList", roleList);
        boolean success = EmployeeService.getInstance().InsertEmployee(this);

        if (success) {
            System.out.println("returning Success from InsertEmployee method");
            result = "SUCCESS";
            ArrayList emplist = EmployeeService.getInstance().getAllEmployees();

            sessionMap.put("EmpList", emplist);

        } else {
            System.out.println("returning Failure from InsertEmployee method");
        }

        return result;
    }

    public String editEmployee() throws Exception {

        String result = "SUCCESS";
        Employee emp = EmployeeService.getInstance().getEmployee(this.employeeId);
        System.out.println("Employee first name:" + emp.getFirstName());
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();
        sessionMap.put("DeptList", deptList);
        sessionMap.put("RoleList", roleList);
        sessionMap.put("Emp", emp);

        return result;

    }
     public String UpdateService() throws Exception {
        String result = "FAILURE";

        boolean success = EmployeeService.getInstance().updateEmployee(this);

        if (success) {
            System.out.println("returning Success from updateemployee method");
             ArrayList emplist=EmployeeService.getInstance().getAllEmployees();
        
            sessionMap.put("EmpList", emplist);
            result = "SUCCESS";
        } else {
            System.out.println("returning Failure from updateemployee method");
        }

        return result;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the basicSalary
     */
    public String getBasicSalary() {
        return basicSalary;
    }

    /**
     * @param basicSalary the basicSalary to set
     */
    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    /**
     * @return the carAllaowance
     */
    public String getCarAllaowance() {
        return carAllaowance;
    }

    /**
     * @param carAllaowance the carAllaowance to set
     */
    public void setCarAllaowance(String carAllaowance) {
        this.carAllaowance = carAllaowance;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the sessionMap
     */
    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    /**
     * @param sessionMap the sessionMap to set
     */
    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    /**
     * @return the map
     */
    public ApplicationMap getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(ApplicationMap map) {
        this.map = map;
    }

    /**
     * @return the employeeId
     */
}
