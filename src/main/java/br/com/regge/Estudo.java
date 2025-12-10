package br.com.regge;

public class Estudo {
    private String nome;        // Ex: ACRUE
    private String protocolo;   // Ex: D8227C00002
    private String membro;      // Ex: AstraZeneca
    private String status;      // Ex: Recrutando

    public Estudo(String nome, String protocolo, String membro, String status) {
        this.nome = nome;
        this.protocolo = protocolo;
        this.membro = membro;
        this.status = status;
    }

    // Getters
    public String getNome() { return nome; }
    public String getProtocolo() { return protocolo; }
    public String getMembro() { return membro; }
    public String getStatus() { return status; }
}