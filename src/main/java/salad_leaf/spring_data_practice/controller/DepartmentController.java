package salad_leaf.spring_data_practice.controller;

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
    public List<DepartmentProjection> findAll(){
        return departmentService.findAll();
    }
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping("/{departmentId}")
    public Department findById(@PathVariable Long departmentId){
        return departmentService.findById(departmentId);
    }

    @PutMapping("/{departmentId}")
    public Department updateDepartment(@PathVariable Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartment(departmentId, department);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
