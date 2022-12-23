package com.idev4.gateway.service.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class JWTToken {

    public String id_token;

    public String role;

    public Map<String, String> manage;

    public List<Screen> screens;

    public String error;

    public long emp_portfolio;

    public long emp_branch;

    public String emp_name;

    public List<Long> ports;

    public LocalDateTime brnchOpnDt;

    public String loginId;

    public String version;

    public List<Module> modules;

    public boolean brnchUpdtd;

    public long emp_area;

    // Added By Naveed - 23-02-2022
    // For RM User Login
    public long emp_reg;

    // End By Naveed
    public JWTToken() {
        super();
    }

    public JWTToken(String error) {
        this.error = error;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long port, String empName, String user) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_portfolio = port;
        this.emp_name = empName;
        this.loginId = user;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long port, String empName, String user, String version) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_portfolio = port;
        this.emp_name = empName;
        this.loginId = user;
        this.version = version;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long port, long branch, String empName, String user,
                    List<Module> mod) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_portfolio = port;
        this.emp_branch = branch;
        this.emp_name = empName;
        this.loginId = user;
        this.modules = mod;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long port, long branch, String empName, String user) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_portfolio = port;
        this.emp_branch = branch;
        this.emp_name = empName;
        this.loginId = user;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long port, long branch, String empName, String user,
                    String version) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_portfolio = port;
        this.emp_branch = branch;
        this.emp_name = empName;
        this.loginId = user;
        this.version = version;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long branch, List<Long> ports, String empName, String user) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_branch = branch;
        this.emp_name = empName;
        this.ports = ports;
        this.loginId = user;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long branch, List<Long> ports, String empName) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_branch = branch;
        this.emp_name = empName;
        this.ports = ports;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long port, long branch, String empName) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_portfolio = port;
        this.emp_branch = branch;
        this.emp_name = empName;
    }

    public JWTToken(String idToken, String role, List<Screen> screens, long branch, String empName) {
        this.id_token = idToken;
        this.role = role;
        this.screens = screens;
        this.emp_branch = branch;
        this.emp_name = empName;
    }

    public JWTToken(Map<String, String> manage) {
        this.manage = manage;
    }
}