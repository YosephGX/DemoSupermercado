package com.theyoseph.DemoSupermercado.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailDTO {
    private Long id;
    private String nameProd;
    private Integer cantProd;
    private Double price;
    private Double subTotal;
}
