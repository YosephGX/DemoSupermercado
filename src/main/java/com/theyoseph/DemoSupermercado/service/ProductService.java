package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.ProductDTO;
import com.theyoseph.DemoSupermercado.exception.NoDataException;
import com.theyoseph.DemoSupermercado.exception.NotFoundException;
import com.theyoseph.DemoSupermercado.exception.ParamsException;
import com.theyoseph.DemoSupermercado.mapper.Mapper;
import com.theyoseph.DemoSupermercado.model.Product;
import com.theyoseph.DemoSupermercado.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> getAll() {
        List<ProductDTO> r = repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
        if (r.isEmpty()) throw new NoDataException(1);
        return r;
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() ||
                dto.getCategory() == null || dto.getCategory().isBlank() ||
                dto.getPrice() == null ||
                dto.getQuantity() == null
        ){ throw new ParamsException(); }
        Product prod = Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        // Buscar si existe el producto
        Product prod = repo.findById(id).orElseThrow(() -> new NotFoundException(1));
        // Modificar
        if (productDTO.getName() != null) prod.setName(productDTO.getName());
        if (productDTO.getCategory() != null) prod.setCategory(productDTO.getCategory());
        if (productDTO.getPrice() != null) prod.setPrice(productDTO.getPrice());
        if (productDTO.getQuantity() != null) prod.setQuantity(productDTO.getQuantity());
        // Guardar
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void delete(Long id) {
        // Buscar si existe el producto
        if (!repo.existsById(id)) throw new NotFoundException(1);
        // Eliminar
        repo.deleteById(id);
    }
}
