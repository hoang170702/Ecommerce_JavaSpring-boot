package h2tk.ecom.model;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(unique=true,name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    Genders genders;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Column(name = "token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Genders getGenders() {
        return genders;
    }

    public void setGenders(Genders genders) {
        this.genders = genders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Users() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Users(int id, String name, String username, String password, String phoneNumber, Genders genders, String address, String email, Date birthday, Status status, Roles role, String token) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.genders = genders;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
        this.status = status;
        this.role = role;
        this.token = token;
    }
}
