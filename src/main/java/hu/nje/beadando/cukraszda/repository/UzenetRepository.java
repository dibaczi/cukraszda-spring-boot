package hu.nje.beadando.cukraszda.repository;
import hu.nje.beadando.cukraszda.entity.Uzenet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UzenetRepository extends JpaRepository<Uzenet, Long> {
    List<Uzenet> findAllByOrderByKuldesIdejeDescIdDesc();
}
