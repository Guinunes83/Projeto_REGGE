package br.com.regge;

public class Membro {
    private String id;
    private String nome;
    private String funcao;
    private String cpf;
    private String licencas; // CRM/COREN etc
    private String rqe;
    private String dataNasc;
    private String sexo;
    private String telefone;
    private String email;
    private String dataCv;
    private String valCv;
    private String dataGcp;
    private String valGcp; // Assumindo que o segundo "Val. CV" após o GCP seja a validade do GCP
    private String gcpR3;
    private String dataIata;
    private String valIata;
    private String cvLattes;

    public Membro(String id, String nome, String funcao, String cpf, String licencas, String rqe, 
                  String dataNasc, String sexo, String telefone, String email, String dataCv, 
                  String valCv, String dataGcp, String valGcp, String gcpR3, String dataIata, 
                  String valIata, String cvLattes) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
        this.cpf = cpf;
        this.licencas = licencas;
        this.rqe = rqe;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.dataCv = dataCv;
        this.valCv = valCv;
        this.dataGcp = dataGcp;
        this.valGcp = valGcp;
        this.gcpR3 = gcpR3;
        this.dataIata = dataIata;
        this.valIata = valIata;
        this.cvLattes = cvLattes;
    }

    // Getters para podermos ler os dados depois
    public String getNome() { return nome; }
    public String getFuncao() { return funcao; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    // Adicione outros Getters conforme precisar usar na tela
    
    @Override
    public String toString() {
        return nome; // Para aparecer o nome corretamente em ComboBoxes
    }
}