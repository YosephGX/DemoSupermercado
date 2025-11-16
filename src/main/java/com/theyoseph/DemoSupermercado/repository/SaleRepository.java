package com.theyoseph.DemoSupermercado.repository;

import com.theyoseph.DemoSupermercado.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    //
}
