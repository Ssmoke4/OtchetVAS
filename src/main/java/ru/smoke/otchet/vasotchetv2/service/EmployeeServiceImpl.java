package ru.smoke.otchet.vasotchetv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.smoke.otchet.vasotchetv2.entity.Employee;
import ru.smoke.otchet.vasotchetv2.repo.EmployeeRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public List<Employee> getAllByDepartment(String department) {
        List<Employee> getAllByDepartment = employeeRepository.findEmployeeByDepartment(department);
        return getAllByDepartment;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public List<Employee> DestinctByID() {

        return employeeRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Employee findById(int id) {

        return employeeRepository.findEmployeeById(id);
    }


}
