package entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.ResultSet;

@Entity
@Table(name = "USER")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LOGIN")
    @Size(min = 5, message = "Не менее 5 знаков")
    private String login;

    @Column(name = "NAME")
    @Size(min = 5, message = "Не менее 5 знаков")
    private String name;

    @Column(name = "PASSWORD")
    @Size(min = 5, message = "Не менее 5 знаков")
    private String password;

    @Column(name = "ADMIN_STATUS")
    private boolean adminStatus;

    public User() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLogin() {
        return login;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public boolean getAdmin_status() {
        return adminStatus;
    }
    public void setLogin(String login) { this.login = login; }
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAdmin_status(boolean admin_status) {
        this.adminStatus = admin_status;
    }
}
