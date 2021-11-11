package ir.maktab.controller;

import ir.maktab.command.ContactCommand;
import ir.maktab.model.entity.Contact;
import ir.maktab.model.entity.User;
import ir.maktab.service.ContactService;
import ir.maktab.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {
    private final ContactService contactService;
    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @RequestMapping("/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";
    }

    @RequestMapping("/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c,
                                      Model m, HttpSession session) {
        Integer contactId = (Integer) session.getAttribute("contactId");
        if (contactId == null) {
            try {
                User user = (User) session.getAttribute("user");
                c.setUser(user);
                contactService.addContact(c);
                return "redirect:contactList?act=sv";
            } catch (Exception e) {
                m.addAttribute("err", "Failed to save contact");
                return "contact_form";
            }
        } else {
            try {
                contactService.updateContact(c, contactId);
                return "redirect:contactList?act=ed";
            } catch (Exception e) {
                m.addAttribute("err", "Failed to update contact");
                return "contact_form";
            }
        }
    }

    @RequestMapping("/user/contactList")
    public String contactList(Model m, HttpSession session) {
        User user = (User) session.getAttribute("user");
        m.addAttribute("contacts", contactService.findAllByUserId(user));
        return "clist";
    }

    @RequestMapping("/user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer cid, Model model) {
        contactService.deleteById(cid);
        return "redirect:contactList?act=del";
    }

    @RequestMapping("/user/edit_contact")
    public String preparedEditForm(@RequestParam("cid") Integer cid, Model model,
                                   HttpSession session) {
        session.setAttribute("contactId", cid);
        Contact found = contactService.findById(cid);
        model.addAttribute("command", found);
        return "contact_form";
    }

    @RequestMapping(value = "/user/contact_search", method = RequestMethod.POST)
    public ModelAndView searchContact(@ModelAttribute("command") ContactCommand contact, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Page<Contact> contacts = contactService.findMaxMatch(contact.getName(), contact.getPhone(),
                contact.getEmail(), user.getId(), 0, 100);
        return new ModelAndView("clist", "contacts", contacts.getContent());
    }

}
