package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.SaleDetailDTO;
import com.theyoseph.DemoSupermercado.dto.SaleDTO;
import com.theyoseph.DemoSupermercado.exception.NoDataException;
import com.theyoseph.DemoSupermercado.exception.NotFoundException;
import com.theyoseph.DemoSupermercado.exception.ParamsException;
import com.theyoseph.DemoSupermercado.mapper.Mapper;
import com.theyoseph.DemoSupermercado.model.SaleDetail;
import com.theyoseph.DemoSupermercado.model.Product;
import com.theyoseph.DemoSupermercado.model.Branch;
import com.theyoseph.DemoSupermercado.model.Sale;
import com.theyoseph.DemoSupermercado.repository.ProductRepository;
import com.theyoseph.DemoSupermercado.repository.BranchRepository;
import com.theyoseph.DemoSupermercado.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private SaleRepository ventaRepo;
    @Autowired
    private ProductRepository prodRepo;
    @Autowired
    private BranchRepository sucRepo;

    @Override
    public List<SaleDTO> getAll() {
        List<Sale> sales = ventaRepo.findAll();
        List<SaleDTO> ventasDTO = new ArrayList<>();
        SaleDTO dto;
        for (Sale v : sales){
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }
        if (ventasDTO.isEmpty()) throw new NoDataException(3);
        return ventasDTO;
    }

    @Override
    public SaleDTO create(SaleDTO saleDTO) {
        // Validaciones
        if (saleDTO == null) throw new ParamsException();
        if (saleDTO.getIdBranch() == null) throw new ParamsException();
        if (saleDTO.getDate() == null) throw new ParamsException();
        if (saleDTO.getDetail() == null || saleDTO.getDetail().isEmpty()){
            throw new ParamsException();
        }
        // Buscar sucursal
        Branch suc = sucRepo.findById(saleDTO.getIdBranch()).orElse(null);
        if (suc == null){
            throw new NotFoundException(2);
        }
        // Crear la venta
        Sale vent = new Sale();
        vent.setDate(saleDTO.getDate());
        vent.setStatus(saleDTO.getStatus());
        vent.setBranch(suc);
        // Crear la lista de detalles
        List<SaleDetail> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;
        for (SaleDetailDTO detDTO : saleDTO.getDetail()){
            // Buscar producto por id o nombre
            Product p = prodRepo.findByName(detDTO.getNameProd()).orElseThrow(
                    () -> new NotFoundException(1, detDTO.getNameProd())
            );
            // Crear detalle
            SaleDetail detalleVent = new SaleDetail();
            detalleVent.setProd(p);
            detalleVent.setPrice(detDTO.getPrice());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setSale(vent);
            detalles.add(detalleVent);
            totalCalculado += (detDTO.getPrice() * detDTO.getCantProd());
        }
        vent.setTotal(totalCalculado);
        vent.setDetail(detalles);
        // Guardar
        return Mapper.toDTO(ventaRepo.save(vent));
    }

    @Override
    public SaleDTO update(Long id, SaleDTO saleDTO) {
        // Buscar si existe la venta
        Sale v = ventaRepo.findById(id).orElseThrow(() -> new NotFoundException(3));
        // Validaciones y actualizaciones
        if (saleDTO.getDate() != null) v.setDate(saleDTO.getDate());

        if (saleDTO.getStatus() != null) v.setStatus(saleDTO.getStatus());
        if (saleDTO.getTotal() != null) v.setTotal(saleDTO.getTotal());
        if (saleDTO.getIdBranch() != null){
            Branch suc = sucRepo.findById(saleDTO.getIdBranch()).orElseThrow(
                    () -> new NotFoundException(2)
            );
            v.setBranch(suc);
        }
        // Guardar
        return Mapper.toDTO(ventaRepo.save(v));
    }

    @Override
    public void delete(Long id) {
        // Buscar si existe la venta
        if (!ventaRepo.existsById(id)) throw new NotFoundException(3);
        // Eliminar
        ventaRepo.deleteById(id);

    }
}
