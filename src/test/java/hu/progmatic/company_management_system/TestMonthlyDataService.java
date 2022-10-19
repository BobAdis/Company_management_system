package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.hr_accounting.Month;
import hu.progmatic.company_management_system.models.hr_accounting.MonthlyData;
import hu.progmatic.company_management_system.models.hr_accounting.Year;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.MonthlyDataRepo;
import hu.progmatic.company_management_system.services.MonthlyDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TestMonthlyDataService {
    @Mock
    private MonthlyDataRepo monthlyDataRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private MonthlyDataService monthlyDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMonthlyData() {
        MonthlyData data = new MonthlyData();
        data.setId(1L);
        data.setEmployee(new Employee("8475000253", "Héni Péter", "Héni Péter", "093366915",
                "Székesfehérvár", LocalDate.of(1988,2,23),
                "Ilik Marianna", "2360 Gyál, Táncsics Mihály utca 163.", "Alkalmazott",
                LocalDate.of(2021,5,12), "9310", 6, 400000));
        data.setNetSalary(1, 0, 0, 0);
        data.setMonth(Month.JANUARY);
        data.setYear(Year.YEAR_2023);

    }
}
