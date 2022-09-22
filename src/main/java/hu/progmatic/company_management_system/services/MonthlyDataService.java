package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.MonthlyData;
import hu.progmatic.company_management_system.repositories.MonthlyDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyDataService {

    private MonthlyDataRepo monthlyDataRepo;

    @Autowired
    public MonthlyDataService(MonthlyDataRepo monthlyDataRepo){
        this.monthlyDataRepo = monthlyDataRepo;
    }

    public List<MonthlyData> getMonthlyData(){
      List<MonthlyData> monthlyDataList = (List<MonthlyData>) monthlyDataRepo.findAll();
      return monthlyDataList;
    }
    public void save(MonthlyData monthlyData) {
        monthlyDataRepo.save(monthlyData);
    }

    public void calculate(MonthlyData monthlyData) {

    }
}
