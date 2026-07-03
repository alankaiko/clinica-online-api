package br.com.clinica.api.controller;

import br.com.clinica.api.dto.request.AtualizarEspecialidadeRequest;
import br.com.clinica.api.dto.request.CadastrarEspecialidadeRequest;
import br.com.clinica.api.dto.response.EspecialidadeResponse;
import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.infrastructure.config.TenantContext;
import br.com.clinica.usecase.especialidade.*;
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
@RequestMapping("/especialidades")
@RequiredArgsConstructor
public class EspecialidadeController {

    private final CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase;
    private final BuscarEspecialidadePorIdUseCase buscarEspecialidadePorIdUseCase;
    private final ListarEspecialidadesUseCase listarEspecialidadesUseCase;
    private final AtualizarEspecialidadeUseCase atualizarEspecialidadeUseCase;
    private final InativarEspecialidadeUseCase inativarEspecialidadeUseCase;

    @PostMapping
    public ResponseEntity<EspecialidadeResponse> cadastrar(@RequestBody CadastrarEspecialidadeRequest request) {
        String tenantId = TenantContext.getCurrentTenant();
        
        CadastrarEspecialidadeUseCase.Input input = new CadastrarEspecialidadeUseCase.Input(
            tenantId,
            request.getDescricao()
        );
        
        Especialidade especialidade = this.cadastrarEspecialidadeUseCase.executar(input);
        EspecialidadeResponse response = EspecialidadeResponse.fromDomain(especialidade);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> buscarPorId(@PathVariable UUID id) {
        String tenantId = TenantContext.getCurrentTenant();
        
        Especialidade especialidade = this.buscarEspecialidadePorIdUseCase.executar(id, tenantId);
        EspecialidadeResponse response = EspecialidadeResponse.fromDomain(especialidade);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<EspecialidadeResponse>> listar(@PageableDefault(size = 20) Pageable pageable) {
        String tenantId = TenantContext.getCurrentTenant();
        
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize());
        
        PageInfo<Especialidade> pageInfo = this.listarEspecialidadesUseCase.executar(tenantId, pageRequest);
        
        Page<EspecialidadeResponse> response = new PageImpl<>(
            pageInfo.getContent().stream()
                .map(EspecialidadeResponse::fromDomain)
                .collect(Collectors.toList()),
            pageable,
            pageInfo.getTotalElements()
        );
        
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizarEspecialidadeRequest request) {
        String tenantId = TenantContext.getCurrentTenant();
        
        AtualizarEspecialidadeUseCase.Input input = new AtualizarEspecialidadeUseCase.Input(
            id,
            tenantId,
            request.getDescricao()
        );
        
        Especialidade especialidade = this.atualizarEspecialidadeUseCase.executar(input);
        EspecialidadeResponse response = EspecialidadeResponse.fromDomain(especialidade);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable UUID id) {
        String tenantId = TenantContext.getCurrentTenant();
        
        this.inativarEspecialidadeUseCase.executar(id, tenantId);
        
        return ResponseEntity.noContent().build();
    }
}
