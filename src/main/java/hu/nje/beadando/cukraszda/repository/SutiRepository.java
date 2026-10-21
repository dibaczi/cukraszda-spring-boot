package hu.nje.beadando.cukraszda.repository;
import hu.nje.beadando.cukraszda.entity.Suti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface SutiRepository extends JpaRepository<Suti, Integer> {
    @Query("select s.tipus, count(s) from Suti s group by s.tipus order by count(s) desc")
    List<Object[]> countByTipus();
}
