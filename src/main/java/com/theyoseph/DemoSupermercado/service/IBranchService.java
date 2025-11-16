package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.BranchDTO;

import java.util.List;

public interface IBranchService {

    List<BranchDTO> getAll();
    BranchDTO create(BranchDTO branchDTO);
    BranchDTO update(Long id, BranchDTO branchDTO);
    void delete(Long id);

}
