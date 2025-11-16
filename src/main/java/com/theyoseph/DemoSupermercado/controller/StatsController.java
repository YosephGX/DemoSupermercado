package com.theyoseph.DemoSupermercado.controller;

import com.theyoseph.DemoSupermercado.dto.BranchStatsDTO;
import com.theyoseph.DemoSupermercado.dto.ProductStatsDTO;
import com.theyoseph.DemoSupermercado.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private StatsService service;

    @GetMapping("/product/top")
    public ResponseEntity<ProductStatsDTO> getTopProduct(){
        return ResponseEntity.ok(service.getTopProduct());
    }

    @GetMapping("/branch/top")
    public ResponseEntity<BranchStatsDTO> getTopBranch(){
        return ResponseEntity.ok(service.getTopBranch());
    }
}
