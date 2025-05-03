package salad_leaf.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @JsonView(Views.UserDetails.class)
    private UUID id;

    @NotBlank
    @JsonView({Views.UserSummary.class, Views.OrderSummary.class})
    private String name;

    @Email
    @Column(unique = true)
    @JsonView(Views.UserSummary.class)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.UserDetails.class)
    private List<Order> orderList = new ArrayList<>();

    public User() {

    }

    public User(String name, String email, List<Order> orderList) {
        this.name = name;
        this.email = email;
        this.orderList = orderList;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
        order.setUser(this);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
