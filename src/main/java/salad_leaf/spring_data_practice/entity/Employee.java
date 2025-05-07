package salad_leaf.spring_data_practice.entity;

import jakarta.persistence.*;
import lombok.*;
import salad_leaf.spring_data_practice.projection.EmployeeProjection;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String position;
    private Long salary;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String firstName, String lastName, String position, Long salary, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }
}
