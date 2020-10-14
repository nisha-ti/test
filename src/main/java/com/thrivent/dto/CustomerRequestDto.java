package com.thrivent.customerdata.dto;

public class CustomerRequestDto {

    /*private String RecordKey_Input;
    private String Email1_Input;
    private String Phone1_Input;
    private String FirstName_Input;
    private String LastName_Input;
    private String AddressLine1_Input;
    private String City_Input;
    private String StateProvince_Input;
    private String PostalCode_Input;
    private String Email2_Input;*/
    private String fullName;
    private String address;
    private String phoneNo;
    private String email;
    private String city;
    private String stateProvince;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
