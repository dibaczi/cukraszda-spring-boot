package hu.nje.beadando.cukraszda.controller;
import hu.nje.beadando.cukraszda.repository.UzenetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UzenetekController {
    private final UzenetRepository uzenetek;
    public UzenetekController(UzenetRepository uzenetek) { this.uzenetek = uzenetek; }
    @GetMapping("/uzenetek")
    public String lista(Model model) {
        model.addAttribute("uzenetek", uzenetek.findAllByOrderByKuldesIdejeDescIdDesc());
        return "uzenetek";
    }
}
