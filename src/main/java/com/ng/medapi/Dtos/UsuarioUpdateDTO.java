package com.ng.medapi.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO {
    private String email;
    private String telefono;
    private String documento;
    private String password;
    private String rol;
}
