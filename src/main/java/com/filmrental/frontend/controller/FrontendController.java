package com.filmrental.frontend.controller;

import com.filmrental.frontend.model.EndpointDefinition;
import com.filmrental.frontend.model.EndpointExecutionForm;
import com.filmrental.frontend.model.EndpointResult;
import com.filmrental.frontend.model.TeamMember;
import com.filmrental.frontend.service.BackendApiService;
import com.filmrental.frontend.service.EndpointCatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class FrontendController {

    private final EndpointCatalogService endpointCatalogService;
    private final BackendApiService backendApiService;

    public FrontendController(EndpointCatalogService endpointCatalogService, BackendApiService backendApiService) {
        this.endpointCatalogService = endpointCatalogService;
        this.backendApiService = backendApiService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("members", endpointCatalogService.findAllMembers());
                model.addAttribute("title", "Meet The Team");
        return "home";
    }

    @GetMapping("/members/{memberId}")
    public String memberEndpoints(@PathVariable String memberId, Model model) {
        TeamMember member = endpointCatalogService.findMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown member: " + memberId));

        List<EndpointDefinition> endpoints = endpointCatalogService.findEndpointsByMember(memberId);

        model.addAttribute("member", member);
        model.addAttribute("endpoints", endpoints);
        model.addAttribute("ownershipNotes", endpointCatalogService.getOwnershipNotes(memberId));
                model.addAttribute("executionForm", new EndpointExecutionForm());

        return "member-endpoints";
    }

    @GetMapping("/members/{memberId}/endpoints/{endpointId}")
    public String endpointExecutor(
            @PathVariable String memberId,
            @PathVariable String endpointId,
            Model model
    ) {
        TeamMember member = endpointCatalogService.findMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown member: " + memberId));

        EndpointDefinition endpoint = endpointCatalogService.findEndpointById(memberId, endpointId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown endpoint: " + endpointId));

        EndpointExecutionForm executionForm = new EndpointExecutionForm();
        executionForm.setPathInput(endpoint.getPath());
        executionForm.setRequestBody("");

        model.addAttribute("member", member);
        model.addAttribute("endpoint", endpoint);
        model.addAttribute("executionForm", executionForm);
        model.addAttribute("hasResult", false);

        return "endpoint-result";
    }

    @PostMapping("/members/{memberId}/endpoints/{endpointId}")
    public String executeEndpoint(
            @PathVariable String memberId,
            @PathVariable String endpointId,
            @ModelAttribute("executionForm") EndpointExecutionForm executionForm,
            Model model
    ) {
        TeamMember member = endpointCatalogService.findMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown member: " + memberId));

        EndpointDefinition endpoint = endpointCatalogService.findEndpointById(memberId, endpointId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown endpoint: " + endpointId));

        String pathInput = executionForm.getPathInput();
        if (pathInput == null || pathInput.isBlank()) {
            pathInput = endpoint.getPath();
        }

        String requestBody = executionForm.getRequestBody();
        EndpointResult result = backendApiService.execute(endpoint.getMethod(), pathInput, requestBody);

        model.addAttribute("member", member);
        model.addAttribute("endpoint", endpoint);
        executionForm.setPathInput(pathInput);
        executionForm.setRequestBody(requestBody == null ? "" : requestBody);
        model.addAttribute("executionForm", executionForm);
        model.addAttribute("result", result);
        model.addAttribute("hasResult", true);

        return "endpoint-result";
    }
}
