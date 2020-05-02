package com.dancesar.algamoney.api.resource;

import com.dancesar.algamoney.api.event.RecursoCriadoEvent;
import com.dancesar.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler;
import com.dancesar.algamoney.api.model.Lancamento;
import com.dancesar.algamoney.api.repository.LancamentoRepository;
import com.dancesar.algamoney.api.repository.filter.LancamentoFilter;
import com.dancesar.algamoney.api.service.LancamentoService;
import com.dancesar.algamoney.api.service.exception.PessoaInexistenteOuInativoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter){

        return lancamentoRepository.filtrar(lancamentoFilter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long id){
        Optional<Lancamento> lancamento = this.lancamentoRepository.findById(id);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> criarLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento lancamnentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamnentoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamnentoSalvo);
    }

    @ExceptionHandler({PessoaInexistenteOuInativoException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativoException(PessoaInexistenteOuInativoException ex){
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}