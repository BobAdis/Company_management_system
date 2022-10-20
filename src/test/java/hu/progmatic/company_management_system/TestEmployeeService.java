package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.MonthlyDataRepo;
import hu.progmatic.company_management_system.repositories.UserRepository;
import hu.progmatic.company_management_system.searchform.EmployeeSearchForm;
import hu.progmatic.company_management_system.services.EmployeeService;
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

public class TestEmployeeService {
    @Mock
    private UserRepository monthlyDataRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;

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

        Employee badEmployee2 = new Employee();
        badEmployee2.setTaxNumber("E");
        badEmployee2.setName("F");

        when(employeeRepo.findAll()).thenReturn(List.of(goodEmployee, badEmployee2, badEmployee));

        EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
        employeeSearchForm.setName("B");
        employeeSearchForm.setTaxnumber("A");

        List<Employee> results = employeeService.getByForm(employeeSearchForm);

        assertEquals(1, results.size());
        assertEquals("B", results.get(0).getName());
        assertEquals("A", results.get(0).getTaxNumber());


    }
}
