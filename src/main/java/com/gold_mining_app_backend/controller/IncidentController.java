package com.gold_mining_app_backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gold_mining_app_backend.dto.IncidentDTO;
import com.gold_mining_app_backend.dto.PageDTO;
import com.gold_mining_app_backend.input.IncidentInput;
import com.gold_mining_app_backend.input.PageInput;
import com.gold_mining_app_backend.services.IncidentServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/incident")
public class IncidentController {
    @Autowired private IncidentServices incidentServices;
@PostMapping("add-incident")
public ResponseEntity<String> createIncident(@RequestBody IncidentInput incidentInput,Principal principal) {
return incidentServices.createIncident(incidentInput,principal);
}
@PostMapping("find-all")
public PageDTO<IncidentDTO> incitentItemList(@RequestBody PageInput page) {
return incidentServices.getIncidentItemsList(page);
}
}
