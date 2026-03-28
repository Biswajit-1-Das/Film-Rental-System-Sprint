package com.frontend.controller;

import com.frontend.dto.MemberDTO;
import com.frontend.dto.EntityDTO;
import com.frontend.dto.EndpointDTO;
import com.frontend.service.TeamDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ViewController {

    private final TeamDataService dataService;

    public ViewController(TeamDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping({"/", "/team"})
    public String team(Model model) {
        model.addAttribute("members", dataService.getMembers());
        return "team";
    }

    @GetMapping("/team/{index}")
    public String memberPage(@PathVariable int index, Model model) {
        List<MemberDTO> members = dataService.getMembers();
        if (index < 0 || index >= members.size()) {
            return "redirect:/team";
        }
        model.addAttribute("member", members.get(index));
        return "member";
    }

    @GetMapping("/team/{memberIndex}/endpoint")
    public String endpointPage(
            @PathVariable int memberIndex,
            @RequestParam String entity,
            @RequestParam int epIndex,
            Model model) {

        List<MemberDTO> members = dataService.getMembers();
        if (memberIndex < 0 || memberIndex >= members.size()) {
            return "redirect:/team";
        }

        MemberDTO member = members.get(memberIndex);
        EntityDTO entityDTO = member.getEntities().stream()
                .filter(e -> e.getName().equals(entity))
                .findFirst()
                .orElse(null);

        if (entityDTO == null || epIndex < 0 || epIndex >= entityDTO.getEndpoints().size()) {
            return "redirect:/team/" + memberIndex;
        }

        EndpointDTO endpoint = entityDTO.getEndpoints().get(epIndex);
        model.addAttribute("member", member);
        model.addAttribute("entity", entityDTO);
        model.addAttribute("endpoint", endpoint);
        model.addAttribute("memberIndex", memberIndex);
        model.addAttribute("baseUrl", "http://localhost:9081");
        return "endpoint";
    }

    @PostMapping("/execute")
    public String execute(
            @RequestParam String method,
            @RequestParam String url,
            @RequestParam(required = false) String body,
            Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String response;
        try {
            if (method.equals("GET")) {
                response = restTemplate.getForObject(url, String.class);
            } else {
                response = restTemplate.postForObject(url, body, String.class);
            }
        } catch (Exception e) {
            response = "ERROR: " + e.getMessage();
        }
        model.addAttribute("response", response);
        return "result";
    }
}