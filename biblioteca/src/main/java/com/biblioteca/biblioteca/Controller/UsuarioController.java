package com.biblioteca.biblioteca.Controller;

import com.biblioteca.biblioteca.Controller.RequestDTO.UsuarioRequestDTO;
import com.biblioteca.biblioteca.Controller.ResponseDTO.UsuarioResponseDTO;
import com.biblioteca.biblioteca.Model.Usuario;
import com.biblioteca.biblioteca.Services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO usuarioDTO){
        Usuario usuario = usuarioService.cadastrar(usuarioDTO.paraUsuario());
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail(), usuario.getRoles().toString());

        return ResponseEntity.ok().body(usuarioResponseDTO);
    }
}
