package salad_leaf.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import javax.swing.text.View;

@Entity
@Table(name = "orders")//Делаем класс сущностью JPA, чтобы Spring сохранял их в базу данных
public class Order {
    @Id // Первичный ключ. Уникальный идентификатор для каждой строки в базе
    @GeneratedValue(strategy = GenerationType.IDENTITY) //генерируем айдишник автоматически с помощью автоинкремента
    @JsonView({Views.OrderSummary.class, Views.UserDetails.class})
    private Long id;
    @JsonView({Views.OrderSummary.class, Views.UserDetails.class})
    private String product;
    @JsonView(Views.OrderSummary.class)
    private int price;
    @Enumerated(EnumType.STRING) // храним в базе строковое представление статуса
    @JsonView(Views.OrderDetails.class)
    private OrderStatus status;
    @ManyToOne // у заказа — один пользователь, но у пользователя — много заказов
    @JoinColumn(name = "user_id")
    @JsonView(Views.OrderSummary.class)
    private User user;

    public Order(){

    }

    public Order(String product, int price) {
        this.product = product;
        this.price = price;
        this.status = OrderStatus.RECEIVED;
    }

    public Long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
