package com.ryzen.contacts;

import java.io.Serializable;

/**
 * Created by samhacker on 3/2/2018.
 */

public class Contact implements Serializable {
    String FirstName,LastName,Email,PhNo;

    public Contact(String firstName, String lastName, String email, String phNo) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhNo = phNo;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhNo() {
        return PhNo;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhNo(String phNo) {
        PhNo = phNo;
    }
}
