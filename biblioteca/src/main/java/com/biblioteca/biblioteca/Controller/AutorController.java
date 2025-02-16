package com.biblioteca.biblioteca.Controller;

import com.biblioteca.biblioteca.Controller.RequestDTO.AutorRequestDTO;
import com.biblioteca.biblioteca.Repository.AutorRepository;
import com.biblioteca.biblioteca.Controller.ResponseDTO.AutorResponseDTO;
import com.biblioteca.biblioteca.Services.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService, AutorRepository autorRepository) {
        this.autorService = autorService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ESCRITOR', 'GERENTE')")
    public ResponseEntity<Object> cadastrarAutor(@RequestBody @Valid AutorRequestDTO autorDTO) {
        var autor = autorService.save(autorDTO.paraAutor());

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(autor.getId())
                .toUri();

        AutorResponseDTO autorResponseDTO = new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getEmail(), autor.getTelefone());

        return ResponseEntity.created(uri).body(autorResponseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ESCRITOR', 'GERENTE')")
    public ResponseEntity<Object> infoAutor(@PathVariable Long id){
        var autor = autorService.obterAutor(id);
        if(autor == null)
            return ResponseEntity.notFound().build();

        var responseDTO = new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getEmail(), autor.getTelefone());
        return ResponseEntity.ok(responseDTO);
    }

}
