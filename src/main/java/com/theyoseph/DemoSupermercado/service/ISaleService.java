package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.SaleDTO;

import java.util.List;

public interface ISaleService {
    List<SaleDTO> getAll();
    SaleDTO create(SaleDTO saleDTO);
    SaleDTO update(Long id, SaleDTO saleDTO);
    void delete(Long id);
}
