CREATE TABLE especialidade (
    id UUID PRIMARY KEY,
    tenant_id VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    descricao VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE convenio (
    id UUID PRIMARY KEY,
    tenant_id VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nome_comercial VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    registro_ans VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE medico (
    id UUID PRIMARY KEY,
    tenant_id VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nome VARCHAR(255) NOT NULL,
    crm VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE medico_especialidade (
    medico_id UUID NOT NULL,
    especialidade_id UUID NOT NULL,
    PRIMARY KEY (medico_id, especialidade_id),
    FOREIGN KEY (medico_id) REFERENCES medico(id) ON DELETE CASCADE,
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id) ON DELETE CASCADE
);

CREATE INDEX idx_medico_tenant ON medico(tenant_id);
CREATE INDEX idx_medico_crm ON medico(crm);
CREATE INDEX idx_medico_ativo ON medico(ativo);
CREATE INDEX idx_especialidade_tenant ON especialidade(tenant_id);
CREATE INDEX idx_especialidade_ativo ON especialidade(ativo);
CREATE INDEX idx_convenio_tenant ON convenio(tenant_id);
CREATE INDEX idx_convenio_cnpj ON convenio(cnpj);
CREATE INDEX idx_convenio_ativo ON convenio(ativo);
