package br.com.clinica.api.controller;

import br.com.clinica.api.dto.request.AtualizarConvenioRequest;
import br.com.clinica.api.dto.request.CadastrarConvenioRequest;
import br.com.clinica.api.dto.response.ConvenioResponse;
import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.infrastructure.config.TenantContext;
import br.com.clinica.usecase.convenio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/convenios")
@RequiredArgsConstructor
public class ConvenioController {

    private final CadastrarConvenioUseCase cadastrarConvenioUseCase;
    private final BuscarConvenioPorIdUseCase buscarConvenioPorIdUseCase;
    private final ListarConveniosUseCase listarConveniosUseCase;
    private final AtualizarConvenioUseCase atualizarConvenioUseCase;
    private final InativarConvenioUseCase inativarConvenioUseCase;

    @PostMapping
    public ResponseEntity<ConvenioResponse> cadastrar(@RequestBody CadastrarConvenioRequest request) {
        String tenantId = TenantContext.getCurrentTenant();
        
        CadastrarConvenioUseCase.Input input = new CadastrarConvenioUseCase.Input(
            tenantId,
            request.getNomeComercial(),
            request.getCnpj(),
            request.getRegistroAns(),
            request.getEmail()
        );
        
        Convenio convenio = this.cadastrarConvenioUseCase.executar(input);
        ConvenioResponse response = ConvenioResponse.fromDomain(convenio);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvenioResponse> buscarPorId(@PathVariable UUID id) {
        String tenantId = TenantContext.getCurrentTenant();
        
        Convenio convenio = this.buscarConvenioPorIdUseCase.executar(id, tenantId);
        ConvenioResponse response = ConvenioResponse.fromDomain(convenio);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ConvenioResponse>> listar(@PageableDefault(size = 20) Pageable pageable) {
        String tenantId = TenantContext.getCurrentTenant();
        
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize());
        
        PageInfo<Convenio> pageInfo = this.listarConveniosUseCase.executar(tenantId, pageRequest);
        
        Page<ConvenioResponse> response = new PageImpl<>(
            pageInfo.getContent().stream()
                .map(ConvenioResponse::fromDomain)
                .collect(Collectors.toList()),
            pageable,
            pageInfo.getTotalElements()
        );
        
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvenioResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizarConvenioRequest request) {
        String tenantId = TenantContext.getCurrentTenant();
        
        AtualizarConvenioUseCase.Input input = new AtualizarConvenioUseCase.Input(
            id,
            tenantId,
            request.getNomeComercial(),
            request.getRegistroAns(),
            request.getEmail()
        );
        
        Convenio convenio = this.atualizarConvenioUseCase.executar(input);
        ConvenioResponse response = ConvenioResponse.fromDomain(convenio);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable UUID id) {
        String tenantId = TenantContext.getCurrentTenant();
        
        this.inativarConvenioUseCase.executar(id, tenantId);
        
        return ResponseEntity.noContent().build();
    }
}
