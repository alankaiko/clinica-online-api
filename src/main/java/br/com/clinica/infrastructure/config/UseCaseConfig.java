package br.com.clinica.infrastructure.config;

import br.com.clinica.domain.repository.ConvenioRepository;
import br.com.clinica.domain.repository.EspecialidadeRepository;
import br.com.clinica.domain.repository.MedicoRepository;
import br.com.clinica.usecase.convenio.*;
import br.com.clinica.usecase.especialidade.*;
import br.com.clinica.usecase.medico.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    // Médico Use Cases
    @Bean
    public CadastrarMedicoUseCase cadastrarMedicoUseCase(MedicoRepository medicoRepository) {
        return new CadastrarMedicoUseCase(medicoRepository);
    }

    @Bean
    public BuscarMedicoPorIdUseCase buscarMedicoPorIdUseCase(MedicoRepository medicoRepository) {
        return new BuscarMedicoPorIdUseCase(medicoRepository);
    }

    @Bean
    public ListarMedicosUseCase listarMedicosUseCase(MedicoRepository medicoRepository) {
        return new ListarMedicosUseCase(medicoRepository);
    }

    @Bean
    public AtualizarMedicoUseCase atualizarMedicoUseCase(MedicoRepository medicoRepository) {
        return new AtualizarMedicoUseCase(medicoRepository);
    }

    @Bean
    public InativarMedicoUseCase inativarMedicoUseCase(MedicoRepository medicoRepository) {
        return new InativarMedicoUseCase(medicoRepository);
    }

    // Especialidade Use Cases
    @Bean
    public CadastrarEspecialidadeUseCase cadastrarEspecialidadeUseCase(EspecialidadeRepository especialidadeRepository) {
        return new CadastrarEspecialidadeUseCase(especialidadeRepository);
    }

    @Bean
    public BuscarEspecialidadePorIdUseCase buscarEspecialidadePorIdUseCase(EspecialidadeRepository especialidadeRepository) {
        return new BuscarEspecialidadePorIdUseCase(especialidadeRepository);
    }

    @Bean
    public ListarEspecialidadesUseCase listarEspecialidadesUseCase(EspecialidadeRepository especialidadeRepository) {
        return new ListarEspecialidadesUseCase(especialidadeRepository);
    }

    @Bean
    public AtualizarEspecialidadeUseCase atualizarEspecialidadeUseCase(EspecialidadeRepository especialidadeRepository) {
        return new AtualizarEspecialidadeUseCase(especialidadeRepository);
    }

    @Bean
    public InativarEspecialidadeUseCase inativarEspecialidadeUseCase(EspecialidadeRepository especialidadeRepository) {
        return new InativarEspecialidadeUseCase(especialidadeRepository);
    }

    // Convênio Use Cases
    @Bean
    public CadastrarConvenioUseCase cadastrarConvenioUseCase(ConvenioRepository convenioRepository) {
        return new CadastrarConvenioUseCase(convenioRepository);
    }

    @Bean
    public BuscarConvenioPorIdUseCase buscarConvenioPorIdUseCase(ConvenioRepository convenioRepository) {
        return new BuscarConvenioPorIdUseCase(convenioRepository);
    }

    @Bean
    public ListarConveniosUseCase listarConveniosUseCase(ConvenioRepository convenioRepository) {
        return new ListarConveniosUseCase(convenioRepository);
    }

    @Bean
    public AtualizarConvenioUseCase atualizarConvenioUseCase(ConvenioRepository convenioRepository) {
        return new AtualizarConvenioUseCase(convenioRepository);
    }

    @Bean
    public InativarConvenioUseCase inativarConvenioUseCase(ConvenioRepository convenioRepository) {
        return new InativarConvenioUseCase(convenioRepository);
    }
}
