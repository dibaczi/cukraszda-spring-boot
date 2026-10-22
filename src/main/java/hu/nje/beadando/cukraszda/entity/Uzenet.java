package hu.nje.beadando.cukraszda.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "uzenet")
public class Uzenet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "A név megadása kötelező.")
    @Size(min = 2, max = 100, message = "A név 2–100 karakteres legyen.")
    @Column(nullable = false, length = 100)
    private String nev;
    @NotBlank(message = "Az e-mail-cím megadása kötelező.")
    @Email(message = "Adj meg érvényes e-mail-címet.")
    @Size(max = 180, message = "Az e-mail-cím legfeljebb 180 karakter lehet.")
    @Column(nullable = false, length = 180)
    private String email;
    @NotBlank(message = "Az üzenet szövege kötelező.")
    @Size(min = 10, max = 2000, message = "Az üzenet 10–2000 karakteres legyen.")
    @Column(nullable = false, length = 2000)
    private String szoveg;
    @Column(name = "kuldes_ideje", nullable = false, updatable = false)
    private LocalDateTime kuldesIdeje;
    @PrePersist
    public void beallitKuldesIdeje() { if (kuldesIdeje == null) kuldesIdeje = LocalDateTime.now(); }
    public Long getId() { return id; }
    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSzoveg() { return szoveg; }
    public void setSzoveg(String szoveg) { this.szoveg = szoveg; }
    public LocalDateTime getKuldesIdeje() { return kuldesIdeje; }
}
