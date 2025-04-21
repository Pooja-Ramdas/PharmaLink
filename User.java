package com.pharmalink.users;

import com.pharmalink.exceptions.DatabaseConnectionException;
import com.pharmalink.models.Medicine;

import java.util.List;

public abstract class User {
    protected String id;
    protected String name;
    public String email;
    public String password;
    protected String role;
    protected String pharmacyId;

    public User(String id, String name, String email, String password, String role, String pharmacyId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public abstract List<Medicine> viewAllMedicines();

    public abstract void login() throws DatabaseConnectionException;

    public abstract void signup(String id, String name, String email, String password, String role, String pharmacyId) throws DatabaseConnectionException;
}
