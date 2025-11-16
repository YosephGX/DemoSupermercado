package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.BranchDTO;
import com.theyoseph.DemoSupermercado.exception.NoDataException;
import com.theyoseph.DemoSupermercado.exception.NotFoundException;
import com.theyoseph.DemoSupermercado.exception.ParamsException;
import com.theyoseph.DemoSupermercado.mapper.Mapper;
import com.theyoseph.DemoSupermercado.model.Branch;
import com.theyoseph.DemoSupermercado.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {

    @Autowired
    private BranchRepository repo;

    @Override
    public List<BranchDTO> getAll() {
        List<BranchDTO> r = repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
        if (r.isEmpty()) throw new NoDataException(2);
        return r;
    }

    @Override
    public BranchDTO create(BranchDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() ||
                dto.getAddress() == null || dto.getAddress().isBlank()
        ){ throw new ParamsException(); }
        Branch suc = Branch.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public BranchDTO update(Long id, BranchDTO branchDTO) {
        // Buscar si existe la sucursal
        Branch suc = repo.findById(id).orElseThrow(() -> new NotFoundException(2));
        // Modificar
        if (branchDTO.getName() != null) suc.setName(branchDTO.getName());
        if (branchDTO.getAddress() != null) suc.setAddress(branchDTO.getAddress());
        // Guardar
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void delete(Long id) {
        // Buscar si existe la sucursal
        if (!repo.existsById(id)) throw new NotFoundException(2);
        // Eliminar
        repo.deleteById(id);
    }
}
