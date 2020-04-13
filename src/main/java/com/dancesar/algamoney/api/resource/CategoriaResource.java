package com.dancesar.algamoney.api.resource;

import com.dancesar.algamoney.api.model.Categoria;
import com.dancesar.algamoney.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoriaSalva.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return  ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping("/{id}")
    public Categoria buscarPeloCodigo(@PathVariable Long id){
        return categoriaRepository.findById(id).get();
    }
}