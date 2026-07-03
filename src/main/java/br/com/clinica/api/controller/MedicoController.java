package br.com.clinica.api.controller;

import br.com.clinica.api.dto.request.AtualizarMedicoRequest;
import br.com.clinica.api.dto.request.CadastrarMedicoRequest;
import br.com.clinica.api.dto.response.MedicoResponse;
import br.com.clinica.domain.model.Medico;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.infrastructure.config.TenantContext;
import br.com.clinica.usecase.medico.*;
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
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final CadastrarMedicoUseCase cadastrarMedicoUseCase;
    private final BuscarMedicoPorIdUseCase buscarMedicoPorIdUseCase;
    private final ListarMedicosUseCase listarMedicosUseCase;
    private final AtualizarMedicoUseCase atualizarMedicoUseCase;
    private final InativarMedicoUseCase inativarMedicoUseCase;

    @PostMapping
    public ResponseEntity<MedicoResponse> cadastrar(@RequestBody CadastrarMedicoRequest request) {
        String tenantId = TenantContext.getCurrentTenant();
        
        CadastrarMedicoUseCase.Input input = new CadastrarMedicoUseCase.Input(
            tenantId,
            request.getNome(),
            request.getCrm(),
            request.getEmail()
        );
        
        Medico medico = this.cadastrarMedicoUseCase.executar(input);
        MedicoResponse response = MedicoResponse.fromDomain(medico);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarPorId(@PathVariable UUID id) {
        String tenantId = TenantContext.getCurrentTenant();
        
        Medico medico = this.buscarMedicoPorIdUseCase.executar(id, tenantId);
        MedicoResponse response = MedicoResponse.fromDomain(medico);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponse>> listar(@PageableDefault(size = 20) Pageable pageable) {
        String tenantId = TenantContext.getCurrentTenant();
        
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize());
        
        PageInfo<Medico> pageInfo = this.listarMedicosUseCase.executar(tenantId, pageRequest);
        
        Page<MedicoResponse> response = new PageImpl<>(
            pageInfo.getContent().stream()
                .map(MedicoResponse::fromDomain)
                .collect(Collectors.toList()),
            pageable,
            pageInfo.getTotalElements()
        );
        
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizarMedicoRequest request) {
        String tenantId = TenantContext.getCurrentTenant();
        
        AtualizarMedicoUseCase.Input input = new AtualizarMedicoUseCase.Input(
            id,
            tenantId,
            request.getNome(),
            request.getEmail()
        );
        
        Medico medico = this.atualizarMedicoUseCase.executar(input);
        MedicoResponse response = MedicoResponse.fromDomain(medico);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable UUID id) {
        String tenantId = TenantContext.getCurrentTenant();
        
        this.inativarMedicoUseCase.executar(id, tenantId);
        
        return ResponseEntity.noContent().build();
    }
}
