package br.com.regge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BancoDeDadosParticipantes {

    private static ObservableList<Participante> listaDeParticipantes;

    static {
        listaDeParticipantes = FXCollections.observableArrayList();

        // 1. Mario Pedro Gomes (CAPITELLO-281)
        listaDeParticipantes.add(new Participante(
            "CAPITELLO-281", "Mario Pedro Gomes", "-", "-", 
            "0716 001", "-", "Falha Screening", "Data da falha 20/04/2021"
        ));

        // 2. Edvaldo Gomes (CAPITELLO-281)
        listaDeParticipantes.add(new Participante(
            "CAPITELLO-281", "Edvaldo Gomes", "-", "-", 
            "-", "0716 009", "Ativo", "-"
        ));

        // 3. Osvaldir Gabriel Henrique (EMERALD-1)
        listaDeParticipantes.add(new Participante(
            "EMERALD-1", "Osvaldir Gabriel Henrique", "E0702001", "-", 
            "-", "-", "Óbito", "Data do óbito 05/11/20 - choque séptico"
        ));

        // 4. Antonio Arraujo Lazzaris (MAHOGANY)
        listaDeParticipantes.add(new Participante(
            "MAHOGANY", "Antonio Arraujo Lazzaris", "-", "-", 
            "055082-003", "-", "Ativo", "-"
        ));

        // 5. Maristela Lehnkuhl de Souza (MAHOGANY)
        listaDeParticipantes.add(new Participante(
            "MAHOGANY", "Maristela Lehnkuhl de Souza", "-", "-", 
            "055082-014", "-", "Falha Screening", "-"
        ));

        // 6. Dorival Martins Souza (MAHOGANY)
        listaDeParticipantes.add(new Participante(
            "MAHOGANY", "Dorival Martins Souza", "055082-007", "-", // Mantive conforme solicitado, mesmo parecendo Nº
            "-", "-", "Retirou Consent", "-"
        ));
    }

    public static ObservableList<Participante> getParticipantes() {
        return listaDeParticipantes;
    }
}