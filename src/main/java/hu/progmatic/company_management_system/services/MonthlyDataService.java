package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.hr_accounting.MonthlyData;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.MonthlyDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyDataService {

    private MonthlyDataRepo monthlyDataRepo;
    private EmployeeRepo employeeRepo;

    @Autowired
    public MonthlyDataService(MonthlyDataRepo monthlyDataRepo, EmployeeRepo employeeRepo){
        this.monthlyDataRepo = monthlyDataRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<MonthlyData> getMonthlyData(){
      List<MonthlyData> monthlyDataList = (List<MonthlyData>) monthlyDataRepo.findAll();
      return monthlyDataList;
    }
    public void save(MonthlyData monthlyData) {
        monthlyDataRepo.save(monthlyData);
    }

    public int setNetSalary(int grossSalary, int workingDays, int paidLeave, int sickLeave, int illnessBenefit) {
        return ((grossSalary / 31 * workingDays) + (grossSalary/ 31 * paidLeave) +
                ((grossSalary / 31 * sickLeave) /10 * 7) /1000 * 665);
    }

    public MonthlyData getMonthlyDataByEmployee(Employee employee) {
        return monthlyDataRepo.findMonthlyDataByEmployee(employee);
    }
}
