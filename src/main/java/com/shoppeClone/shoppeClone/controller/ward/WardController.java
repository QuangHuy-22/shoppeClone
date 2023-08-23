package com.shoppeClone.shoppeClone.controller.ward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppeClone.shoppeClone.dto.ward.WardDTO;
import com.shoppeClone.shoppeClone.service.WardService;

@Controller
@RequestMapping("admin/wards")
public class WardController {
    
    @Autowired
    private WardService wardService;

    @GetMapping("add")
    public String showAddWardPage() {
        return "admin/ward/add-ward";
    }

    @GetMapping("search")
    public String showSearchWardPage() {
        return "admin/ward/search-ward";
    }

    @GetMapping("{wardId}")
    public String showUpdateWardPage(
            @PathVariable String wardId, Model model) {
        WardDTO wardDTO = wardService.getWardByWardId(Long.parseLong(wardId));
        model.addAttribute("ward", wardDTO);
        return "admin/ward/update-ward";
    }

 
}
