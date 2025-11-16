package com.theyoseph.DemoSupermercado.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {
    private Long id;
    private LocalDate date;
    private String status;
    // Datos sucursal
    private Long idBranch;
    // Lista Detalles
    private List<SaleDetailDTO> detail;
    // Total
    private Double total;
}
