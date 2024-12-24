package org.example.DTO;


import org.example.enums.OrderStatusType;

public class OrderStatusDTO {
    private Long id;
    private OrderStatusType status;

    public OrderStatusDTO() {}

    public OrderStatusDTO(Long id, OrderStatusType status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatusDTO{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
