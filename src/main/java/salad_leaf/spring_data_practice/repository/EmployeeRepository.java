package salad_leaf.spring_data_practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salad_leaf.spring_data_practice.entity.Employee;
import salad_leaf.spring_data_practice.projection.EmployeeProjection;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByDepartmentId(Long departmentID);
    @Query("SELECT "
            + "CONCAT(e.firstName, ' ', e.lastName) AS fullName, "
            + "e.position AS position, "
            + "e.department.name AS departmentName "
            + "FROM Employee e")
    List<EmployeeProjection> findAllProjectedBy();
}
