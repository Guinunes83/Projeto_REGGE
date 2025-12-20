package br.com.regge;

import javafx.scene.control.CheckBox;

public class DesvioRegistro {

    // Checkbox para selecionar a linha
    private CheckBox selecionar;

    // Dados
    private String estudo;
    private String investigador;
    private String centro;
    private String nomePaciente;
    private String numPaciente;
    private String dataOcorrencia;
    private String dataDesvio;
    private String status;
    private String dataGeracao;

    // Dados extras (invisíveis na tabela)
    private String descricao;
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
        this.status = "Pendente";
        this.dataGeracao = "-";

        this.descricao = descricao;
        this.patrocinador = patrocinador;
        this.coordenador = coordenador;
    }

    // --- GETTERS (SEM ISSO A TABELA NÃO FUNCIONA) ---
    // Verifique se você tem todos estes métodos abaixo:
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(String dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    // Extras para o PDF
    public String getDescricao() {
        return descricao;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public String getCoordenador() {
        return coordenador;
    }
}
