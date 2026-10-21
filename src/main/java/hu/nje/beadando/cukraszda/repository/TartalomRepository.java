package hu.nje.beadando.cukraszda.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import hu.nje.beadando.cukraszda.entity.Tartalom;
public interface TartalomRepository extends JpaRepository<Tartalom, Integer> {}
