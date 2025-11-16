package com.theyoseph.DemoSupermercado.mapper;

import com.theyoseph.DemoSupermercado.dto.*;
import com.theyoseph.DemoSupermercado.model.Product;
import com.theyoseph.DemoSupermercado.model.Branch;
import com.theyoseph.DemoSupermercado.model.Sale;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de Producto a ProductoDTO
    public static ProductDTO toDTO(Product p) {
        if (p == null) return null;
        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .price(p.getPrice())
                .quantity(p.getQuantity())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static SaleDTO toDTO(Sale v) {
        if (v == null) return null;
        var detail = v.getDetail().stream().map(det ->
                SaleDetailDTO.builder()
                        .id(det.getProd().getId())
                        .nameProd(det.getProd().getName())
                        .cantProd(det.getCantProd())
                        .price(det.getPrice())
                        .subTotal(det.getPrice() * det.getCantProd())
                        .build()
        ).collect(Collectors.toList());
        var total = detail.stream()
                .map(SaleDetailDTO::getSubTotal)
                .reduce(0.0, Double::sum);
        return SaleDTO.builder()
                .id(v.getId())
                .date(v.getDate())
                .idBranch(v.getBranch().getId())
                .status(v.getStatus())
                .detail(detail)
                .total(total)
                .build();
    }


    //Mapeo de Sucursal a SucursalDTO
    public static BranchDTO toDTO(Branch s){
        if (s == null) return null;
        return BranchDTO.builder()
                .id(s.getId())
                .name(s.getName())
                .address(s.getAddress())
                .build();
    }

    //Mapeo ProductStatsDTO
    public static ProductStatsDTO toDTO(Product p, Long units){
        if (p == null) return null;
        return ProductStatsDTO.builder()
                .idProduct(p.getId())
                .nameProduct(p.getName())
                .totalSales(units)
                .build();
    }

    //Mapeo BranchStatsDTO
    public static BranchStatsDTO toDTO(Branch b, Double total){
        if (b == null) return null;
        return BranchStatsDTO.builder()
                .idBranch(b.getId())
                .nameBranch(b.getName())
                .totalSales(total)
                .build();
    }
}
