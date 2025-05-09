package salad_leaf.spring_data_practice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'SUPER_ADMIN')")
    public List<EmployeeProjection> findAll() {
        return employeeService.findAll();
    }

    @PostMapping("create/{departmentId}")
    @PreAuthorize("hasAnyRole('MODERATOR', 'SUPER_ADMIN')")
    public Employee createEmployee(@PathVariable Long departmentId, @RequestBody Employee employee) {
        return employeeService.createEmployee(employee, departmentId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'SUPER_ADMIN')")
    public Employee findById(@PathVariable UUID id) {
        return employeeService.findById(id);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyRole('MODERATOR', 'SUPER_ADMIN')")
    public Employee updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('MODERATOR', 'SUPER_ADMIN')")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }
}
