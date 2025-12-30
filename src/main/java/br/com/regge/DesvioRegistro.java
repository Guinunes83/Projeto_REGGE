package br.com.regge;

import javafx.scene.control.CheckBox;

public class DesvioRegistro {

    private CheckBox selecionar;
    private String estudo;
    private String investigador;
    private String centro;
    private String nomePaciente;
    private String descricao;
    private String numPaciente;
    private String dataOcorrencia;
    private String dataDesvio;
    private String status;
    private String dataGeracao;

    // Campos extras para o PDF (não aparecem na tabela mas guardamos aqui)
    private String patrocinador;
    private String coordenador;

    public DesvioRegistro(String estudo, String investigador, String centro, String nomePaciente,
            String numPaciente, String dataOcorrencia, String dataDesvio,
            String descricao, String patrocinador, String coordenador) {

        this.selecionar = new CheckBox();
        this.estudo = estudo;
        this.investigador = investigador;
        this.centro = centro;
        this.nomePaciente = nomePaciente;
        this.numPaciente = numPaciente;
        this.dataOcorrencia = dataOcorrencia;
        this.dataDesvio = dataDesvio;
        this.descricao = descricao;
        this.patrocinador = patrocinador;
        this.coordenador = coordenador;
        this.status = "Pendente";
        this.dataGeracao = "-";
    }

    // --- GETTERS (A TABELA USA ISSO PARA PREENCHER AS COLUNAS) ---
    public CheckBox getSelecionar() {
        return selecionar;
    }

    public String getEstudo() {
        return estudo;
    }

    public String getInvestigador() {
        return investigador;
    }

    public String getCentro() {
        return centro;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public String getNumPaciente() {
        return numPaciente;
    }

    public String getDataOcorrencia() {
        return dataOcorrencia;
    }

    public String getDataDesvio() {
        return dataDesvio;
    }

    // --- O IMPORTANTE PARA A NOVA COLUNA ---
    public String getDescricao() {
        return descricao;
    }
    // Se este método não existir ou tiver outro nome, a coluna fica vazia!
    // ---------------------------------------

    public String getStatus() {
        return status;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public String getCoordenador() {
        return coordenador;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataGeracao(String dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
