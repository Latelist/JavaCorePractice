package salad_leaf.spring_data_practice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.spring_data_practice.entity.Department;
import salad_leaf.spring_data_practice.entity.Employee;
import salad_leaf.spring_data_practice.projection.EmployeeProjection;
import salad_leaf.spring_data_practice.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public List<EmployeeProjection> findAll() {
        return employeeRepository.findAllProjectedBy();
    }

    public Employee createEmployee(Employee employee, Long departmentId) {
        addEmployeeToDepartment(departmentId, employee);
        return employeeRepository.save(employee);
    }

    public Employee findById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Не нашёл сотрудника с таким ID"));
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public Employee updateEmployee(UUID id, Employee employee) {
        Employee existingEmployee = findById(id);
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setSalary(employee.getSalary());
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(UUID id){
        Employee employee = findById(id);
        employeeRepository.delete(employee);
    }

    public void addEmployeeToDepartment(Long departmentId, Employee employee) {
        Department department = departmentService.findById(departmentId);
        employee.setDepartment(department);
        department.getEmployeeList().add(employee);
    }

}
