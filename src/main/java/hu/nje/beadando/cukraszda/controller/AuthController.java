package hu.nje.beadando.cukraszda.controller;
import hu.nje.beadando.cukraszda.entity.AppUser;
import hu.nje.beadando.cukraszda.form.RegisterForm;
import hu.nje.beadando.cukraszda.repository.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AppUserRepository users;
    private final PasswordEncoder passwordEncoder;
    public AuthController(AppUserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/login")
    public String login() { return "login"; }
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm form,
                           BindingResult bindingResult, Model model) {
        String username = form.getUsername() == null ? "" : form.getUsername().strip();
        if (users.findByUsername(username).isPresent()) {
            bindingResult.rejectValue("username", "exists", "A felhasználónév már foglalt.");
        }
        if (bindingResult.hasErrors()) return "register";
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole("USER"); // Regisztrációval soha nem lehet ADMIN szerepet szerezni.
        users.save(user);
        return "redirect:/login?registered";
    }
}
