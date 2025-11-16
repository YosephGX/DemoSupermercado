package com.theyoseph.DemoSupermercado.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStatsDTO {
    private Long idProduct;
    private String nameProduct;
    private Long totalSales;
}
