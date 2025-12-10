package br.com.regge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BancoDeDadosFake {

    private static final ObservableList<Estudo> listaEstudos = FXCollections.observableArrayList();

    public static ObservableList<Estudo> getEstudos() {
        return listaEstudos;
    }

    public static void adicionarEstudo(Estudo novoEstudo) {
        listaEstudos.add(novoEstudo);
    }

    public static void inicializarDados() {
        if (listaEstudos.isEmpty()) {
            listaEstudos.add(new Estudo("ACRUE", "D8227C00002", "AstraZeneca", "Recrutando"));
            listaEstudos.add(new Estudo("AMPLITUDE", "67652000PCR3002", "AstraZeneca", "Em Análise"));
            listaEstudos.add(new Estudo("ATLAS", "56021927PCR3003", "Janssen", "Fechado"));
            listaEstudos.add(new Estudo("BENITO", "MB12-C-02-24", "mAbxience", "Recrutando"));
        }
    }
}