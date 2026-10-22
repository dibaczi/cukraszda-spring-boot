package hu.nje.beadando.cukraszda.controller;
import hu.nje.beadando.cukraszda.repository.ArRepository;
import hu.nje.beadando.cukraszda.repository.SutiRepository;
import hu.nje.beadando.cukraszda.repository.TartalomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
    private final SutiRepository sutik;
    private final ArRepository arak;
    private final TartalomRepository tartalmak;
    public PageController(SutiRepository sutik, ArRepository arak, TartalomRepository tartalmak) {
        this.sutik = sutik; this.arak = arak; this.tartalmak = tartalmak;
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("sutiDb", sutik.count());
        model.addAttribute("arDb", arak.count());
        model.addAttribute("tartalomDb", tartalmak.count());
        return "home";
    }
    @GetMapping("/adatbazis")
    public String adatbazis(Model model) {
        model.addAttribute("sutik", sutik.findAll());
        model.addAttribute("arak", arak.findAll());
        model.addAttribute("tartalmak", tartalmak.findAll());
        return "adatbazis";
    }
    @GetMapping("/rest") public String rest() { return "rest"; }
}
