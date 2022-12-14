package hu.progmatic.company_management_system.models.hr_accounting;

import javax.persistence.*;

@Entity
public class MonthlyData {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    private Month month;

    @Enumerated
    private Year year;

    private int workingDays;
    private int paidLeave;
    private int sickLeave;
    private int illnessBenefit;

    private int netSalary;


    @ManyToOne
    private Employee employee;

    public MonthlyData() {
    }

    public MonthlyData(Month month, Year year, int workingDays, int paidLeave,
                       int sickLeave, int illnessBenefit, int netSalary, Employee employee) {
        this.month = month;
        this.year = year;
        this.workingDays = workingDays;
        this.paidLeave = paidLeave;
        this.sickLeave = sickLeave;
        this.illnessBenefit = illnessBenefit;
        this.netSalary = netSalary;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getPaidLeave() {
        return paidLeave;
    }

    public void setPaidLeave(int paidLeave) {
        this.paidLeave = paidLeave;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    public int getIllnessBenefit() {
        return illnessBenefit;
    }

    public void setIllnessBenefit(int illnessBenefit) {
        this.illnessBenefit = illnessBenefit;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(int workingDays, int paidLeave, int sickLeave, int illnessBenefit) {
        this.netSalary = ((employee.getGrossSalary() / 31 * workingDays) + (employee.getGrossSalary() / 31 * paidLeave) +
                ((employee.getGrossSalary() / 31 * sickLeave) /10 * 7) /1000 * 665);
    }

    @Override
    public String toString() {
        return "MonthlyData{" +
                "workingDays=" + workingDays +
                ", paidLeave=" + paidLeave +
                ", sickLeave=" + sickLeave +
                ", illnessBenefit=" + illnessBenefit +
                '}';
    }
}
