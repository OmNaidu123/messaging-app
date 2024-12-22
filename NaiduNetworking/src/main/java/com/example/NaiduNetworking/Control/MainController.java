package com.example.NaiduNetworking.Control;

import com.example.NaiduNetworking.Models.Chat;
import com.example.NaiduNetworking.Models.ChatHistory;
import com.example.NaiduNetworking.Models.User;
import com.example.NaiduNetworking.Repo.Mainrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    private Mainrepo repo;

    public Mainrepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(Mainrepo repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String DirectLogin(){
        return "login";
    }

    @GetMapping("signup")
    public String DirectSignup(){
        return "signup";
    }

    @PostMapping("verifyLogin")
    public ModelAndView verifyUser(@ModelAttribute User user, ModelAndView mv){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            mv.setViewName("error");
            return mv;
        }
        if(repo.checkIfExists(user)){
            user.setDisplayname(repo.getDisplay(user));
            mv.addObject("user", user);
            mv.addObject("others", repo.getUserList(user));
            mv.setViewName("welcome");
        }
        else
            mv.setViewName("error");
        return mv;
    }

    @PostMapping("insertUser")
    public ModelAndView addUser(@ModelAttribute User user, ModelAndView mv){
        System.out.println(user.toString());
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getDisplayname().isEmpty()){
            System.out.println("entered");
            mv.setViewName("error");
            return mv;
        }
        if(repo.insertUser(user))
            mv.setViewName("login");
        else
            mv.setViewName("error");
        return mv;
    }

    @GetMapping("chat")
    public ModelAndView getChat(@RequestParam("Ousername") String receiver, @RequestParam("Yusername") String sender, ModelAndView mv){
        System.out.println(sender + " " + receiver);
        List<ChatHistory> his = repo.getChats(sender, receiver);
        User send = repo.getUser(sender);
        User rece = repo.getUser(receiver);
        mv.addObject("userChat", his);
        mv.addObject("sender", send);
        mv.addObject("receiver", rece);
        mv.setViewName("chatting");
        return mv;
    }

    @PostMapping("sendMsg")
    public ModelAndView uploadMsg(@ModelAttribute Chat chat, ModelAndView mv){
        repo.insertMsg(chat);
        List<ChatHistory> his = repo.getChats(chat.getSender(), chat.getReceiver());
        User send = repo.getUser(chat.getSender());
        User rece = repo.getUser(chat.getReceiver());
        mv.addObject("userChat", his);
        mv.addObject("sender", send);
        mv.addObject("receiver", rece);
        mv.setViewName("chatting");
        return mv;
    }
}
