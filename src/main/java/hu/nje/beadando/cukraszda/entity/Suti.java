package hu.nje.beadando.cukraszda.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "suti")
public class Suti {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "A sütemény nevét meg kell adni.")
    @Size(max = 150, message = "Legfeljebb 150 karakter lehet a név.")
    @Column(nullable = false, length = 150)
    private String nev;
    @NotBlank(message = "A sütemény típusát meg kell adni.")
    @Size(max = 100, message = "Legfeljebb 100 karakter lehet a típus.")
    @Column(nullable = false, length = 100)
    private String tipus;
    @Column(nullable = false)
    private boolean dijazott;
    public Suti() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }
    public String getTipus() { return tipus; }
    public void setTipus(String tipus) { this.tipus = tipus; }
    public boolean isDijazott() { return dijazott; }
    public void setDijazott(boolean dijazott) { this.dijazott = dijazott; }
}
