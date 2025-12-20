package br.com.regge;

public class Estudo {
    // Todos os campos que você me passou
    private String nome;
    private String protocolo;
    private String patrocinador;
    private String pi; // Principal Investigator
    private String cro;
    private String coordenador;
    private String patologia;
    private String recrutamento; // Sim ou Não
    private String numeroCentro;
    private String caae;
    private String monitor;
    private String emailMonitor;
    private String tipoEstudo;
    private String administracao;
    private String observacao;

    // Construtor Gigante (Para preencher tudo de uma vez)
    public Estudo(String nome, String protocolo, String patrocinador, String pi, String cro, 
                  String coordenador, String patologia, String recrutamento, String numeroCentro, 
                  String caae, String monitor, String emailMonitor, String tipoEstudo, 
                  String administracao, String observacao) {
        this.nome = nome;
        this.protocolo = protocolo;
        this.patrocinador = patrocinador;
        this.pi = pi;
        this.cro = cro;
        this.coordenador = coordenador;
        this.patologia = patologia;
        this.recrutamento = recrutamento;
        this.numeroCentro = numeroCentro;
        this.caae = caae;
        this.monitor = monitor;
        this.emailMonitor = emailMonitor;
        this.tipoEstudo = tipoEstudo;
        this.administracao = administracao;
        this.observacao = observacao;
    }

    // --- GETTERS (Para o sistema conseguir ler os dados) ---
    public String getNome() { return nome; }
    public String getProtocolo() { return protocolo; }
    public String getPatrocinador() { return patrocinador; }
    public String getPi() { return pi; }
    public String getCro() { return cro; }
    public String getCoordenador() { return coordenador; }
    public String getPatologia() { return patologia; }
    public String getRecrutamento() { return recrutamento; }
    public String getNumeroCentro() { return numeroCentro; }
    public String getCaae() { return caae; }
    public String getMonitor() { return monitor; }
    public String getEmailMonitor() { return emailMonitor; }
    public String getTipoEstudo() { return tipoEstudo; }
    public String getAdministracao() { return administracao; }
    public String getObservacao() { return observacao; }
    
    // Método toString para facilitar a exibição em listas simples se precisar
    @Override
    public String toString() {
        return nome;
    }
    // --- CONSTRUTOR DE COMPATIBILIDADE (Para o código antigo funcionar) ---
    // Ele recebe os 4 dados antigos e preenche o resto com vazio ""
    public Estudo(String nome, String protocolo, String patrocinador, String recrutamento) {
        this(nome, protocolo, patrocinador, "", "", "", "", recrutamento, "", "", "", "", "", "", "");
    }
}