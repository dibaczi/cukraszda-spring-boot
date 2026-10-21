package hu.nje.beadando.cukraszda.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "tartalom")
public class Tartalom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sutiid") private Integer sutiId;
    @Column(name = "mentes") private String mentes;
    public Tartalom() {}
    public Integer getId() { return id; }
    public Integer getSutiId() { return sutiId; }
    public String getMentes() { return mentes; }
}
