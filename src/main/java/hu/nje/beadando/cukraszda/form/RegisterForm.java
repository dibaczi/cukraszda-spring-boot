package hu.nje.beadando.cukraszda.form;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class RegisterForm {
    @NotBlank(message = "A felhasználónév kötelező.")
    @Size(min = 3, max = 80, message = "A felhasználónév 3–80 karakteres legyen.")
    private String username;
    @NotBlank(message = "A jelszó kötelező.")
    @Size(min = 8, max = 72, message = "A jelszó 8–72 karakteres legyen.")
    private String password;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
