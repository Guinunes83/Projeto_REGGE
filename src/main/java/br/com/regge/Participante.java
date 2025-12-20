package br.com.regge;

public class Participante {
    private String estudo;
    private String nome;
    private String dataNasc;
    private String tratamento;
    private String numero;
    private String randomizado;
    private String status;
    private String observacao;

    public Participante(String estudo, String nome, String dataNasc, String tratamento, 
                        String numero, String randomizado, String status, String observacao) {
        this.estudo = estudo;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.tratamento = tratamento;
        this.numero = numero;
        this.randomizado = randomizado;
        this.status = status;
        this.observacao = observacao;
    }

    public String getEstudo() { return estudo; }
    public String getNome() { return nome; }
    public String getNumero() { return numero; }
    public String getStatus() { return status; }
    
    @Override
    public String toString() {
        return nome + " (" + estudo + ")";
    }
}