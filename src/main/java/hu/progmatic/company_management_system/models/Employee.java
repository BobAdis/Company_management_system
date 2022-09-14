package hu.progmatic.company_management_system.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Employee {

    @Id
    private String taxNumber;
    private String name;
    private String maidenName;
    private String socialSecurityNumber;
    private String placeOfBirth;
    private LocalDate dateOfBirth;
    private String motherName;
    private String address;
    private String personnelGroup;
    @Enumerated
    private Task personnelTask;
    private LocalDate startOfEmployment;
    private String HSCO;
    private int workingHours;
    private int grossSalary;

    public Employee() {
    }



    public Employee(String taxNumber, String name, String maidenName, String socialSecurityNumber,
                    String placeOfBirth, LocalDate dateOfBirth, String motherName, String address,
                    String personnelGroup, Task personnelTask, LocalDate startOfEmployment,
                    String HSCO, int workingHours, int grossSalary) {
        this.taxNumber = taxNumber;
        this.name = name;
        this.maidenName = maidenName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.address = address;
        this.personnelGroup = personnelGroup;
        this.personnelTask = personnelTask;
        this.startOfEmployment = startOfEmployment;
        this.HSCO = HSCO;
        this.workingHours = workingHours;
        this.grossSalary = grossSalary;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonnelGroup() {
        return personnelGroup;
    }

    public void setPersonnelGroup(String personnelGroup) {
        this.personnelGroup = personnelGroup;
    }

    public Task getPersonnelTask() {
        return personnelTask;
    }

    public void setPersonnelTask(Task personnelTask) {
        this.personnelTask = personnelTask;
    }

    public LocalDate getStartOfEmployment() {
        return startOfEmployment;
    }

    public void setStartOfEmployment(LocalDate startOfEmployment) {
        this.startOfEmployment = startOfEmployment;
    }

    public String getHSCO() {
        return HSCO;
    }

    public void setHSCO(String HSCO) {
        this.HSCO = HSCO;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(int grossSalary) {
        this.grossSalary = grossSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "taxNumber=" + taxNumber +
                ", name='" + name + '\'' +
                ", maidenName='" + maidenName + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", motherName='" + motherName + '\'' +
                ", address='" + address + '\'' +
                ", personnelGroup='" + personnelGroup + '\'' +
                ", personnelTask=" + personnelTask +
                ", startOfEmployment=" + startOfEmployment +
                ", HSCO='" + HSCO + '\'' +
                ", workingHours=" + workingHours +
                ", grossSalary=" + grossSalary +
                '}';
    }
}
