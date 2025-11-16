package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.BranchStatsDTO;
import com.theyoseph.DemoSupermercado.dto.ProductStatsDTO;
import com.theyoseph.DemoSupermercado.exception.NoDataException;
import com.theyoseph.DemoSupermercado.mapper.Mapper;
import com.theyoseph.DemoSupermercado.model.Sale;
import com.theyoseph.DemoSupermercado.model.SaleDetail;
import com.theyoseph.DemoSupermercado.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService implements IStatsService {

    @Autowired
    private SaleRepository repo;

    @Override
    public ProductStatsDTO getTopProduct() { // Producto más vendido
        List<Sale> sales = repo.findAll();
        var r = sales.stream()
                .flatMap(s -> s.getDetail().stream())
                .collect(Collectors.groupingBy(
                        SaleDetail::getProd,
                        Collectors.summingInt(SaleDetail::getCantProd)
                )).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoDataException(1));
        return Mapper.toDTO(r.getKey(), r.getValue().longValue());
    }

    @Override
    public BranchStatsDTO getTopBranch() { // Sucursal con más ventas
        List<Sale> sales = repo.findAll();
        var r = sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::getBranch,
                        Collectors.summingDouble(Sale::getTotal)
                )).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoDataException(2));
        return Mapper.toDTO(r.getKey(), r.getValue());
    }
}
