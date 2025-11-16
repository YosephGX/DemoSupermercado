package com.theyoseph.DemoSupermercado.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchStatsDTO {
    private Long idBranch;
    private String nameBranch;
    private Double totalSales;
}
