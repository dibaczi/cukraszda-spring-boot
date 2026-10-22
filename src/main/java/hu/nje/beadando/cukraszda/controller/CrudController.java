package hu.nje.beadando.cukraszda.controller;
import hu.nje.beadando.cukraszda.entity.Suti;
import hu.nje.beadando.cukraszda.repository.SutiRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/crud")
public class CrudController {
    private final SutiRepository sutik;
    public CrudController(SutiRepository sutik) { this.sutik = sutik; }
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("sutik", sutik.findAll(Sort.by("id")));
        return "crud";
    }
    @GetMapping("/uj")
    public String uj(Model model) {
        model.addAttribute("suti", new Suti());
        model.addAttribute("urlapCim", "Új sütemény felvétele");
        return "crud-form";
    }
    @GetMapping("/szerkesztes/{id}")
    public String szerkesztes(@PathVariable Integer id, Model model) {
        Suti suti = sutik.findById(id).orElseThrow(() ->
                new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Nem található sütemény"));
        model.addAttribute("suti", suti);
        model.addAttribute("urlapCim", "Sütemény szerkesztése");
        return "crud-form";
    }
    @PostMapping("/mentes")
    public String mentes(@Valid @ModelAttribute("suti") Suti suti,
                         BindingResult hibak, Model model, RedirectAttributes flash) {
        if (hibak.hasErrors()) {
            model.addAttribute("urlapCim", "Sütemény adatai");
            return "crud-form";
        }
        // Csak ismert azonosítót frissítünk, így véletlenül sem keletkezik új rekord elgépelésből.
        if (suti.getId() != null && !sutik.existsById(suti.getId())) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Nem található sütemény");
        }
        sutik.save(suti);
        flash.addFlashAttribute("siker", "A sütemény mentése sikeres.");
        return "redirect:/crud";
    }
    @PostMapping("/torles/{id}")
    public String torles(@PathVariable Integer id, RedirectAttributes flash) {
        if (!sutik.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Nem található sütemény");
        }
        sutik.deleteById(id);
        flash.addFlashAttribute("siker", "A sütemény törölve.");
        return "redirect:/crud";
    }
}
