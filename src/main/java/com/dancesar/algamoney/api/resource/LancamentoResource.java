package com.dancesar.algamoney.api.resource;

import com.dancesar.algamoney.api.event.RecursoCriadoEvent;
import com.dancesar.algamoney.api.model.Lancamento;
import com.dancesar.algamoney.api.repository.LancamentoRepository;
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
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Lancamento> listar(){
        return lancamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long id){
        Optional<Lancamento> lancamento = this.lancamentoRepository.findById(id);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> criarLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento lancamnentoSalvo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamnentoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamnentoSalvo);
    }
}