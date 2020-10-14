package com.thrivent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"FullName_Input",
        "AddressLine1_Input", "City_Input", "StateProvince_Input",
        "Email2_Input", "Email4_Input", "user_fields", "PersonIds"})
public class Output {
    @JsonProperty("FullName_Input")
    private String fullName_Input;
    @JsonProperty("AddressLine1_Input")
    private String addressLine1_Input;
    @JsonProperty("City_Input")
    private String city_Input;
    @JsonProperty("StateProvince_Input")
    private String stateProvince_Input;
    @JsonProperty("Email2_Input")
    private String email2_Input;
    @JsonProperty("Email4_Input")
    private String email4_Input;
    @JsonProperty("user_fields")
    private List<UserFields> user_fields;
    @JsonProperty("PersonIds")
    private String personIds;

    public String getFullName_Input() {
        return fullName_Input;
    }

    public void setFullName_Input(String fullName_Input) {
        this.fullName_Input = fullName_Input;
    }

    public String getAddressLine1_Input() {
        return addressLine1_Input;
    }

    public void setAddressLine1_Input(String addressLine1_Input) {
        this.addressLine1_Input = addressLine1_Input;
    }

    public String getCity_Input() {
        return city_Input;
    }

    public void setCity_Input(String city_Input) {
        this.city_Input = city_Input;
    }

    public String getStateProvince_Input() {
        return stateProvince_Input;
    }

    public void setStateProvince_Input(String stateProvince_Input) {
        this.stateProvince_Input = stateProvince_Input;
    }

    public String getEmail2_Input() {
        return email2_Input;
    }

    public void setEmail2_Input(String email2_Input) {
        this.email2_Input = email2_Input;
    }

    public String getEmail4_Input() {
        return email4_Input;
    }

    public void setEmail4_Input(String email4_Input) {
        this.email4_Input = email4_Input;
    }

    public List<UserFields> getUser_fields() {
        return user_fields;
    }

    public void setUser_fields(List<UserFields> user_fields) {
        this.user_fields = user_fields;
    }

    public String getPersonIds() {
        return personIds;
    }

    public void setPersonIds(String personIds) {
        this.personIds = personIds;
    }
}
