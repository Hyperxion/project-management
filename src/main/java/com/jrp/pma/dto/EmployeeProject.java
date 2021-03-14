package com.jrp.pma.dto;

public interface EmployeeProject {

    /*
    These getters represents the fields in our custom query specified in EmployeeRepository. Dto package
    stands for data transfer object and serves as a container for custom data to be stored in.
     */
    public String getFirstName();
    public String getLastName();
    public int getProjectCount();

}