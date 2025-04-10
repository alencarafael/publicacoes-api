package com.lexon.Dto;

import java.util.List;

public class ConsultaResponseDTO {
    public String status;
    public String message;
    public Integer count;
    public List<ComunicaResponseDTO.ItemDTO> items;

    public ConsultaResponseDTO(String status, String message, List<ComunicaResponseDTO.ItemDTO> items) {
        this.status = status;
        this.message = message;
        this.items = items;
        this.count = items.size();
    }
}