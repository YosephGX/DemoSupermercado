package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAll();
    ProductDTO create(ProductDTO productDTO);
    ProductDTO update(Long id, ProductDTO productDTO);
    void delete(Long id);
}