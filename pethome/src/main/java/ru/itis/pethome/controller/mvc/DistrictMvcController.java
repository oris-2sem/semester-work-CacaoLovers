package ru.itis.pethome.controller.mvc;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.service.DistrictService;

import java.util.UUID;

@Controller
@RequestMapping("/mvc/district")
@AllArgsConstructor
public class DistrictMvcController {
    private final DistrictService districtService;

    @GetMapping("/edit")
    @PostAuthorize("hasRole('ADMIN')")
    public String getEditDistrict(Model model){
        model.addAttribute("districts", districtService.getDistricts());
        return "editDistrict";
    }

    @PostMapping("/edit/{id}")
    public String deleteDistrict(@PathVariable UUID id){
        districtService.deleteDistrictById(id);
        return "redirect:/mvc/district/edit";
    }


}
