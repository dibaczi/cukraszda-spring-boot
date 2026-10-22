package hu.nje.beadando.cukraszda.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 80)
    private String username;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "role", nullable = false, length = 20)
    private String role = "USER";
    public AppUser() {}
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
