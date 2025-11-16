package com.theyoseph.DemoSupermercado.controller;

import com.theyoseph.DemoSupermercado.dto.BranchDTO;
import com.theyoseph.DemoSupermercado.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private IBranchService service;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO dto){
        BranchDTO suc = service.create(dto);
        return ResponseEntity.created(URI.create("/api/sucursales/" + suc.getId())).body(suc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(
            @PathVariable Long id,
            @RequestBody BranchDTO dto
    ){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
