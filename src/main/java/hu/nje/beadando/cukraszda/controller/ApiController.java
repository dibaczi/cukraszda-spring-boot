package hu.nje.beadando.cukraszda.controller;
import hu.nje.beadando.cukraszda.entity.Suti;
import hu.nje.beadando.cukraszda.repository.SutiRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/sutik")
public class ApiController {
    private final SutiRepository sutik;
    public ApiController(SutiRepository sutik) { this.sutik = sutik; }
    @GetMapping
    public List<Suti> all() { return sutik.findAll(); }
    @GetMapping("/{id}")
    public ResponseEntity<Suti> one(@PathVariable Integer id) {
        return sutik.findById(id).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Suti> create(@Valid @RequestBody Suti suti) {
        suti.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(sutik.save(suti));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Suti> update(@PathVariable Integer id, @Valid @RequestBody Suti bemenet) {
        return sutik.findById(id).map(regi -> {
            regi.setNev(bemenet.getNev());
            regi.setTipus(bemenet.getTipus());
            regi.setDijazott(bemenet.isDijazott());
            return ResponseEntity.ok(sutik.save(regi));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!sutik.existsById(id)) return ResponseEntity.notFound().build();
        sutik.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
