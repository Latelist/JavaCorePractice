//package salad_leaf.spring_data_practice;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//
//import salad_leaf.spring_data_practice.entity.Department;
//import salad_leaf.spring_data_practice.entity.Employee;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class EmployeeDepartmentIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private String getUrl(String path) {
//        return "http://localhost:" + port + path;
//    }
//
//    @Test
//    public void testCreateDepartmentAndEmployee() {
//        Department department = new Department();
//        department.setName("Test Department");
//
//        ResponseEntity<Department> depResponse = restTemplate.postForEntity(
//                getUrl("/departments"),
//                department,
//                Department.class
//        );
//
//        assertThat(depResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Department savedDepartment = depResponse.getBody();
//        assertThat(savedDepartment).isNotNull();
//        assertThat(savedDepartment.getId()).isNotNull();
//
//        // 2. Создание сотрудника, привязанного к департаменту
//        Employee employee = new Employee();
//        employee.setFirstName("John");
//        employee.setLastName("Doe");
//        employee.setPosition("Engineer");
//        employee.setSalary(100000L);
//        employee.setDepartment(savedDepartment); // ссылка на департамент
//
//        ResponseEntity<Employee> empResponse = restTemplate.postForEntity(
//                getUrl("/employees/" + savedDepartment.getId()),
//                employee,
//                Employee.class
//        );
//
//        assertThat(empResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Employee savedEmployee = empResponse.getBody();
//        assertThat(savedEmployee).isNotNull();
//        assertThat(savedEmployee.getId()).isNotNull();
//        assertThat(savedEmployee.getDepartment().getId()).isEqualTo(savedDepartment.getId());
//    }
//}
