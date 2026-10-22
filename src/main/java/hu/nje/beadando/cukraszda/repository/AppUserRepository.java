package hu.nje.beadando.cukraszda.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import hu.nje.beadando.cukraszda.entity.AppUser;
import java.util.Optional;
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
