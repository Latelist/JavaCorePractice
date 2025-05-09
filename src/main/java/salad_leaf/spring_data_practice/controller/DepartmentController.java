package salad_leaf.spring_data_practice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import salad_leaf.spring_data_practice.entity.Department;
import salad_leaf.spring_data_practice.projection.DepartmentProjection;
import salad_leaf.spring_data_practice.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'SUPER_ADMIN')")
    public List<DepartmentProjection> findAll() {
        return departmentService.findAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping("/{departmentId}")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'SUPER_ADMIN')")
    public Department findById(@PathVariable Long departmentId) {
        return departmentService.findById(departmentId);
    }

    @PutMapping("update/{departmentId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Department updateDepartment(@PathVariable Long departmentId, @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);
    }

    @DeleteMapping("delete/{departmentId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public void deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
