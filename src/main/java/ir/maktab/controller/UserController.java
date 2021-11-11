package ir.maktab.controller;

import ir.maktab.command.LoginCommand;
import ir.maktab.command.UserCommand;
import ir.maktab.exception.UserBlockedException;
import ir.maktab.model.entity.User;
import ir.maktab.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/index"})
    public String home(Model model) {
        model.addAttribute("command", new LoginCommand());
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command") LoginCommand c,
                              Model model, HttpSession session) {
        try {
            User loggedInUser = userService.login(c.getLoginName(), c.getPassword());
            if (loggedInUser == null) {
                model.addAttribute("err", "login Failed! Enter valid information");
                return "home";
            } else {
                if (loggedInUser.getRole() == 2) {
                    addUserInSession(loggedInUser, session);
                    return "redirect:user/dashboard";

                } else if (loggedInUser.getRole() == 1) {
                    addUserInSession(loggedInUser, session);
                    return "redirect:admin/dashboard";
                } else {
                    model.addAttribute("err", "Invalid User role");
                    return "home";
                }
            }
        } catch (UserBlockedException userBlockedException) {
            model.addAttribute("err", userBlockedException.getMessage());
            return "home";
        }

    }

    @RequestMapping("/user/dashboard")
    public String showUserDashboard() {
        return "dashboard_user";
    }

    @RequestMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "dashboard_admin";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index?act=lo";
    }

    @RequestMapping("/reg_form")
    public String registrationForm(Model m) {
        UserCommand cmd = new UserCommand();
        m.addAttribute("command", cmd);
        return "reg_form";
    }

    @RequestMapping("/register")
    public String registerUser(@ModelAttribute("command") UserCommand cmd, Model m) {
        try {
            User user = cmd.getUser();
            user.setLoginStatus(1);
            user.setRole(2);
            userService.register(user);
            return "redirect:index?act=reg";
        } catch (DuplicateKeyException e) {
            m.addAttribute("err", e.getMessage());
            return "reg_form";
        }

    }

    private void addUserInSession(User u, HttpSession session) {
        session.setAttribute("user", u);
        session.setAttribute("userId", u.getId());
        session.setAttribute("role", u.getRole());
    }
}
