package com.theyoseph.DemoSupermercado.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
    private int status;
    private String error;
    private String message;
    private String path;
}
