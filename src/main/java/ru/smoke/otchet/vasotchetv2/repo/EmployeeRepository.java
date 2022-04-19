package ru.smoke.otchet.vasotchetv2.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smoke.otchet.vasotchetv2.entity.*;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public List<Employee> findAll();

    public Employee findEmployeeById(int id);

    public List<Employee> findEmployeeByDepartment(String department);


}
