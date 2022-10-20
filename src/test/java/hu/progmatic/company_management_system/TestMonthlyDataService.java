package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.hr_accounting.Month;
import hu.progmatic.company_management_system.models.hr_accounting.MonthlyData;
import hu.progmatic.company_management_system.models.hr_accounting.Year;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.MonthlyDataRepo;
import hu.progmatic.company_management_system.searchform.NetSalarySearchForm;
import hu.progmatic.company_management_system.services.MonthlyDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    public void testGetByForm() {
        Employee goodEmployee = new Employee();
        goodEmployee.setTaxNumber("A");
        goodEmployee.setName("B");

        Employee badEmployee = new Employee();
        badEmployee.setTaxNumber("C");
        badEmployee.setName("D");

        MonthlyData goodData = new MonthlyData();
        goodData.setId(1L);
        goodData.setEmployee(goodEmployee);
        goodData.setMonth(Month.JANUARY);
        goodData.setYear(Year.YEAR_2023);

        MonthlyData badData = new MonthlyData();
        badData.setId(1L);
        badData.setEmployee(badEmployee);
        badData.setMonth(Month.JANUARY);
        badData.setYear(Year.YEAR_2023);

        MonthlyData badData2 = new MonthlyData();
        badData2.setId(1L);
        badData2.setEmployee(goodEmployee);
        badData2.setMonth(Month.FEBRUARY);
        badData2.setYear(Year.YEAR_2023);

        MonthlyData badData3 = new MonthlyData();
        badData3.setId(1L);
        badData3.setEmployee(goodEmployee);
        badData3.setMonth(Month.JANUARY);
        badData3.setYear(Year.YEAR_2022);

        NetSalarySearchForm searchForm = new NetSalarySearchForm();
        searchForm.setName("B");
        searchForm.setMonth("JANUARY");
        searchForm.setYear("YEAR_2023");

        when(monthlyDataRepo.findAll()).thenReturn(List.of(goodData, badData2, badData3, badData));

        List<MonthlyData> results = monthlyDataService.getByForm(searchForm);

        assertEquals(1, results.size());
        assertEquals("B", results.get(0).getEmployee().getName());
        assertEquals(Year.YEAR_2023, results.get(0).getYear());
        assertEquals(Month.JANUARY, results.get(0).getMonth());
    }
}
