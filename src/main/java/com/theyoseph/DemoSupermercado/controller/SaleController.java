package com.theyoseph.DemoSupermercado.controller;

import com.theyoseph.DemoSupermercado.dto.SaleDTO;
import com.theyoseph.DemoSupermercado.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private ISaleService service;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    /**
     * Se crea una venta usando directamente VentaDTO en la request (opcion simple, sin request separado)
     * Se espera que el DTO traiga toda la informacion necesaria
     *
     */
    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO dto){
        SaleDTO venta = service.create(dto);
        return ResponseEntity.created(URI.create("/api/ventas/" + venta.getId())).body(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(
            @PathVariable Long id,
            @RequestBody SaleDTO dto
    ){
        // Actualiza fecha, estado, idSucursal, total y reemplaza el detalle
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
