package com.theyoseph.DemoSupermercado.repository;

import com.theyoseph.DemoSupermercado.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    //
}
