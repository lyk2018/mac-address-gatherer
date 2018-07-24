package tr.org.linux.kamp.macaddressgatherer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@AllArgsConstructor
class HomeController {

    private final UserService userService;

    @GetMapping
    String index() {
        return "index";
    }

    @PostMapping("/users")
    String create(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        user.setIpAddress(request.getRemoteAddr());
        userService.save(user);

        redirectAttributes.addAttribute("success", "true");
        return "redirect:/";
    }

    @GetMapping("/users")
    String list(Model model) {
        model.addAttribute("users", userService.getAll());
        return "list";
    }

}
