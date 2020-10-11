package com.date.memory.web;

import com.date.memory.config.auth.LoginUser;
import com.date.memory.config.auth.dto.SessionUser;
import com.date.memory.service.MemoriesService;
import com.date.memory.web.dto.MemoriesResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
    
    private final MemoriesService memoriesService;

    // @GetMapping("/")
    // public String index(Model model) {
    //     model.addAttribute("memories", memoriesService.findAllDesc());

    //     return "index";
    // }
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", memoriesService.findAllDesc());

        System.out.println("user:"+user);
        
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
       
        return "index";
    }

    @GetMapping("/memories/save")
    public String memoriesSave() {
       return "memories-save";
   }

   @GetMapping("/memories/update/{id}")
   public String memoriesUpdate(@PathVariable Long id, Model model) {
       MemoriesResponseDto dto = memoriesService.findById(id);
       model.addAttribute("memories", dto);

       return "memories-update";
   }
}