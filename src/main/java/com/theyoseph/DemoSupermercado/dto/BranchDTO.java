package com.theyoseph.DemoSupermercado.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchDTO {
    private Long id;
    private String name;
    private String address;
}
