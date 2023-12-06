package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Long id;
    private LocalDateTime created;
    private String type;
    private String model;
    private String serial;
    private String problem;
    private String set;
    private String additional_info;
    private Status status;
    private Long clientId;

    public Order(Long id, LocalDateTime created, String type, String model, String serial, String problem, String set, String additional_info, Status status, Long clientId) {
        this.id = id;
        this.created = created;
        this.type = type;
        this.model = model;
        this.serial = serial;
        this.problem = problem;
        this.set = set;
        this.additional_info = additional_info;
        this.status = status;
        this.clientId = clientId;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(created, order.created) && Objects.equals(type, order.type) && Objects.equals(model, order.model) && Objects.equals(serial, order.serial) && Objects.equals(problem, order.problem) && Objects.equals(set, order.set) && Objects.equals(additional_info, order.additional_info) && status == order.status && Objects.equals(clientId, order.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, type, model, serial, problem, set, additional_info, status, clientId);
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", created=" + created +
               ", type='" + type + '\'' +
               ", model='" + model + '\'' +
               ", serial='" + serial + '\'' +
               ", problem='" + problem + '\'' +
               ", set='" + set + '\'' +
               ", additional_info='" + additional_info + '\'' +
               ", status=" + status +
               ", clientId=" + clientId +
               '}';
    }
}
