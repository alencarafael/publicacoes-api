package com.lexon.Dto.CNJ;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ResultadoCnjDTO {

    private UUID id;
    private UUID pesqUsuarioPublicacaoId;

    private Integer idPublicacao;
    private LocalDate dataDisponibilizacao;
    private String tipoPublicacao;
    private String nomeOrgao;
    private String textoPublicacao;
    private String numeroProcesso;
    private String numeroprocessoMascarado;
    private String meio;
    private String meioCompleto;
    private String linkPublicacao;
    private String tipoDocumento;
    private String nomeClasse;
    private String hashPublicacao;
    private String statusPublicacao;
    private String motivoCancelamento;
    private LocalDate dataCancelamento;

    private List<ResultadoCnjEnvolvidoDTO> envolvidos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPesqUsuarioPublicacaoId() {
        return pesqUsuarioPublicacaoId;
    }

    public void setPesqUsuarioPublicacaoId(UUID pesqUsuarioPublicacaoId) {
        this.pesqUsuarioPublicacaoId = pesqUsuarioPublicacaoId;
    }

    public Integer getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Integer idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public LocalDate getDataDisponibilizacao() {
        return dataDisponibilizacao;
    }

    public void setDataDisponibilizacao(LocalDate dataDisponibilizacao) {
        this.dataDisponibilizacao = dataDisponibilizacao;
    }

    public String getTipoPublicacao() {
        return tipoPublicacao;
    }

    public void setTipoPublicacao(String tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }

    public String getNomeOrgao() {
        return nomeOrgao;
    }

    public void setNomeOrgao(String nomeOrgao) {
        this.nomeOrgao = nomeOrgao;
    }

    public String getTextoPublicacao() {
        return textoPublicacao;
    }

    public void setTextoPublicacao(String textoPublicacao) {
        this.textoPublicacao = textoPublicacao;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getNumeroprocessoMascarado() {
        return numeroprocessoMascarado;
    }

    public void setNumeroprocessoMascarado(String numeroprocessoMascarado) {
        this.numeroprocessoMascarado = numeroprocessoMascarado;
    }

    public String getMeio() {
        return meio;
    }

    public void setMeio(String meio) {
        this.meio = meio;
    }

    public String getMeioCompleto() {
        return meioCompleto;
    }

    public void setMeioCompleto(String meioCompleto) {
        this.meioCompleto = meioCompleto;
    }

    public String getLinkPublicacao() {
        return linkPublicacao;
    }

    public void setLinkPublicacao(String linkPublicacao) {
        this.linkPublicacao = linkPublicacao;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public String getHashPublicacao() {
        return hashPublicacao;
    }

    public void setHashPublicacao(String hashPublicacao) {
        this.hashPublicacao = hashPublicacao;
    }

    public String getStatusPublicacao() {
        return statusPublicacao;
    }

    public void setStatusPublicacao(String statusPublicacao) {
        this.statusPublicacao = statusPublicacao;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public List<ResultadoCnjEnvolvidoDTO> getEnvolvidos() {
        return envolvidos;
    }

    public void setEnvolvidos(List<ResultadoCnjEnvolvidoDTO> envolvidos) {
        this.envolvidos = envolvidos;
    }
}
