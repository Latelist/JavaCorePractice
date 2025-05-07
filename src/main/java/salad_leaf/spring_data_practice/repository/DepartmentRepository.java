package salad_leaf.spring_data_practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salad_leaf.spring_data_practice.entity.Department;
import salad_leaf.spring_data_practice.projection.DepartmentProjection;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<DepartmentProjection> findAllProjectedBy();
}
