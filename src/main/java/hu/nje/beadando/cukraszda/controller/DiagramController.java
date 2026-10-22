package hu.nje.beadando.cukraszda.controller;
import hu.nje.beadando.cukraszda.repository.SutiRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@Controller
public class DiagramController {
    private final SutiRepository sutik;
    public DiagramController(SutiRepository sutik) { this.sutik = sutik; }
    @GetMapping("/diagram")
    public String diagram(Model model) {
        List<Object[]> adatok = sutik.countByTipus();
        model.addAttribute("cimkek", adatok.stream().map(sor -> (String) sor[0]).toList());
        model.addAttribute("ertekek", adatok.stream().map(sor -> ((Number) sor[1]).longValue()).toList());
        return "diagram";
    }
}
