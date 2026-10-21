package hu.nje.beadando.cukraszda.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "ar")
public class Ar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sutiid") private Integer sutiId;
    @Column(name = "ertek") private Integer ertek;
    @Column(name = "egyseg") private String egyseg;
    public Ar() {}
    public Integer getId() { return id; }
    public Integer getSutiId() { return sutiId; }
    public Integer getErtek() { return ertek; }
    public String getEgyseg() { return egyseg; }
}
