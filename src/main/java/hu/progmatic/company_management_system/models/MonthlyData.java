package hu.progmatic.company_management_system.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MonthlyData {

    @Id
    @GeneratedValue
    private Long id;

    private String month;

    private String year;

    private int workingDays;
    private int paidLeave;
    private int sickLeave;
    private int illnessBenefit;


    @ManyToOne
    private Employee employee;

    public MonthlyData() {
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

    public int setNetSalary(int workingDays, int paidLeave, int sickLeave, int illnessBenefit) {
        return ((employee.getGrossSalary() / 31 * workingDays) + (employee.getGrossSalary() / 31 * paidLeave) +
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
