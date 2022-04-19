package ru.smoke.otchet.vasotchetv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.smoke.otchet.vasotchetv2.entity.Employee;
import ru.smoke.otchet.vasotchetv2.service.EmployeeService;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/allinfo")
    public String ViewInformation(Model model) {
        Iterable<Employee> Employee = employeeService.getAllEmployees();
        model.addAttribute("Employees", Employee);
        return "info2";
    }
    @RequestMapping("/new")
        public String addNewEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
            return "employee-info";
        }

    @PostMapping("/ByDepartment")
    public String ViewByDepartment(@RequestParam String department, Model model) {
        List<Employee> employeeList = employeeService.getAllByDepartment(department);
        model.addAttribute("Department", employeeList);
        return "AllByDepartment";
    }



    @PostMapping("/AddNew")
    public String SaveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/allinfo";
    }

    //    @RequestMapping("/deleteEmployee")
//    public String deleteEmployee(@RequestParam("empId") Integer id){
//        employeeService.deleteEmployee(id);
//        System.out.println(id);
//        return "redirect:/allinfo";
//    }
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

//    @RequestMapping("/Update/{id}")
//    public String UpdateEmployee(@PathVariable(name = "id") Integer id, Model model){
//        System.out.println(id);
//        Employee employee = employeeService.findById(id);
//        model.addAttribute("Employee", employee);
//        return "employee-info2";
//    }
    @RequestMapping("/Update/{id}")
    public String EditEmployee(@PathVariable("id") Integer id, Model model){
//        Employee employee = employeeService.findById(id);
        model.addAttribute("Employee", employeeService.findById(id));
        return "employee-info2";
    }

    @PostMapping("/Update")
    public String UpdateEmployee(@ModelAttribute("Employee") Employee employee){
        System.out.println(employee);
        employeeService.saveEmployee(employee);


        return "redirect:/allinfoDesc";
    }
}
