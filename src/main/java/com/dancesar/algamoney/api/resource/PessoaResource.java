package com.dancesar.algamoney.api.resource;

import com.dancesar.algamoney.api.model.Pessoas;
import com.dancesar.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoas> listar(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoas> criarPessoa(@Valid @RequestBody Pessoas pessoas, HttpServletResponse response){
        Pessoas pessoasSalva = pessoaRepository.save(pessoas);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoasSalva.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return  ResponseEntity.created(uri).body(pessoasSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoas> buscarPeloCodigo(@PathVariable Long id){
        Optional<Pessoas> pessoas = this.pessoaRepository.findById(id);
        return pessoas.isPresent() ? ResponseEntity.ok(pessoas.get()) : ResponseEntity.notFound().build();
    }
}