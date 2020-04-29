package com.dancesar.algamoney.api.resource;

import com.dancesar.algamoney.api.event.RecursoCriadoEvent;
import com.dancesar.algamoney.api.model.Pessoas;
import com.dancesar.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoas> listar(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoas> criarPessoa(@Valid @RequestBody Pessoas pessoas, HttpServletResponse response){
        Pessoas pessoasSalva = pessoaRepository.save(pessoas);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoasSalva.getId()));
        return  ResponseEntity.status(HttpStatus.CREATED).body(pessoasSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoas> buscarPeloCodigo(@PathVariable Long id){
        Optional<Pessoas> pessoas = this.pessoaRepository.findById(id);
        return pessoas.isPresent() ? ResponseEntity.ok(pessoas.get()) : ResponseEntity.notFound().build();
    }
}