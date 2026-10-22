package hu.nje.beadando.cukraszda.controller;
import hu.nje.beadando.cukraszda.entity.Uzenet;
import hu.nje.beadando.cukraszda.repository.UzenetRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class KapcsolatController {
    private final UzenetRepository uzenetek;
    public KapcsolatController(UzenetRepository uzenetek) { this.uzenetek = uzenetek; }
    @GetMapping("/kapcsolat")
    public String ujUzenet(Model model) {
        model.addAttribute("uzenet", new Uzenet());
        return "kapcsolat";
    }
    @PostMapping("/kapcsolat")
    public String uzenetMentese(@Valid @ModelAttribute("uzenet") Uzenet uzenet,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "kapcsolat";
        uzenetek.save(uzenet);
        return "redirect:/kapcsolat?sikeres";
    }
}
