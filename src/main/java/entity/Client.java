package entity;

import java.util.Objects;

public class Client {
    private Long id;
    private String name;
    private String phone;
    private String additionalInfo;

    public Client(Long id, String name, String phone, String additionalInfo) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.additionalInfo = additionalInfo;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(phone, client.phone) && Objects.equals(additionalInfo, client.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, additionalInfo);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
