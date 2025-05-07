package salad_leaf.spring_data_practice.controller;

import org.springframework.web.bind.annotation.*;
import salad_leaf.spring_data_practice.entity.Employee;
import salad_leaf.spring_data_practice.projection.EmployeeProjection;
import salad_leaf.spring_data_practice.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public List<EmployeeProjection> findAll(){
        return employeeService.findAll();
    }

    @PostMapping("/{departmentId}")
    public Employee createEmployee(@PathVariable Long departmentId, @RequestBody Employee employee) {
        return employeeService.createEmployee(employee, departmentId);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable UUID id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }
}
