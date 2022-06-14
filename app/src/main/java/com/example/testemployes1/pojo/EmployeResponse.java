package com.example.testemployes1.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class EmployeResponse {
    @SerializedName("response")
    @Expose
    private List<Employee> employe = null;

    public List<Employee> getEmploye() {
        return employe;
    }

    public void setResponse(List<Employee> employe) {
        this.employe = employe;
    }

}