package ru.smoke.otchet.vasotchetv2.service;

import ru.smoke.otchet.vasotchetv2.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public List<Employee> getAllByDepartment(String department);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(Integer id);

    public List<Employee> DestinctByID();
    public Employee findById(int id);
}
