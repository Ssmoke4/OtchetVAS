package ru.smoke.otchet.vasotchetv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.smoke.otchet.vasotchetv2.entity.Employee;
import ru.smoke.otchet.vasotchetv2.service.EmployeeService;

import java.security.Principal;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String HomePage(Principal principal, Model model){
        model.addAttribute("Name", principal.getName());
        return "index.html";
    }


    @GetMapping("/allinfo")
    public String ViewInformation(Principal principal, Model model) {
        Iterable<Employee> Employee = employeeService.getAllEmployees();
        model.addAttribute("Employees", Employee);
        model.addAttribute("Name", principal.getName());
        return "info2";
    }
    @RequestMapping("/new")
        public String addNewEmployee(Principal principal,Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        model.addAttribute("LoginName", principal.getName());
            return "employee-info";
        }

    @PostMapping("/ByDepartment")
    public String ViewByDepartment(@RequestParam String department, Model model) {
        List<Employee> employeeList = employeeService.getAllByDepartment(department);
        model.addAttribute("Department", employeeList);
        return "/AllByDepartment";
    }



    @PostMapping("/AddNew")
    public String SaveEmployee(@ModelAttribute("employee") Employee employee, Principal principal) {
        employee.setName(principal.getName());
        employeeService.saveEmployee(employee);
        return "redirect:/allinfo";
    }


    @RequestMapping("/deleteEmployee/{id}")
    private String deleteEmployee(@PathVariable(name = "id") Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/allinfo";
    }

    @GetMapping("/allinfoDesc")
    public String SortByIdDesc(Model model) {
        Iterable<Employee> Employee = employeeService.DestinctByID();
        model.addAttribute("Employees", Employee);
        return "info2";
    }


    @RequestMapping("/Update/{id}")
    public String EditEmployee(@PathVariable("id") Integer id, Model model){
        model.addAttribute("Employee", employeeService.findById(id));
        return "employee-info2";
    }

    @PostMapping("/Update")
    public String UpdateEmployee(@ModelAttribute("Employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/allinfoDesc";
    }


}
