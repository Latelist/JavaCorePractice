package salad_leaf.spring_data_practice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.spring_data_practice.entity.Department;
import salad_leaf.spring_data_practice.entity.Employee;
import salad_leaf.spring_data_practice.projection.DepartmentProjection;
import salad_leaf.spring_data_practice.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    public List<DepartmentProjection> findAll() {
        return departmentRepository.findAllProjectedBy();
    }
    public Department findById(Long departmentId){
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Нет отдела с таким ID"));
    }
    public Department updateDepartment(Long departmentId, Department department) {
        Department existingDepartment = findById(departmentId);
        existingDepartment.setName(department.getName());
        existingDepartment.setEmployeeList(department.getEmployeeList());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long departmentId) {
        Department department = findById(departmentId);
        departmentRepository.delete(department);
    }
}
