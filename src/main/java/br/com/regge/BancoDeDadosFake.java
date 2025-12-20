package br.com.regge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BancoDeDadosFake {

    private static ObservableList<Estudo> listaDeEstudos;

    // Esse bloco roda assim que o programa inicia
    static {
        listaDeEstudos = FXCollections.observableArrayList();

        // --- 1. ACRUE ---
        listaDeEstudos.add(new Estudo(
            "ACRUE", 
            "D8227C00002", 
            "ASTRAZENECA", 
            "Karla Richter Zanella", 
            "IQVIA", 
            "Camila Sommer", 
            "Linfoma Difuso de Grandes Células B", 
            "SIM", 
            "716", 
            "77784624.7.2024.5355", 
            "Fernanda Gonçalvez de Carvalho", 
            "Fernanda.GonzagadeCarvalho@iconplc.com", 
            "Intervenção", 
            "ORAL", 
            "Solicitar Mini-Protocolo"
        ));

        // --- 2. CAPITELLO-281 ---
        listaDeEstudos.add(new Estudo(
            "CAPITELLO-281", 
            "D361BC00001", 
            "ASTRAZENECA", 
            "Marcelo Roberto Pereira Freitas", 
            "AstraZeneca", 
            "Bruno Vivan", 
            "Câncer de Próstata", 
            "Não", 
            "0", 
            "31017320.1.2027.5355", 
            "Ellen Scotton", 
            "ellen.scotton@astrazeneca.com", 
            "Intervencional", 
            "Oral", 
            "NÃO solicitar mini-protocolo"
        ));

        // --- 3. EMERALD-2 ---
        listaDeEstudos.add(new Estudo(
            "EMERALD-2", 
            "D910DC00001", 
            "ASTRAZENECA", 
            "Rafael Balsini Barreto", 
            "ICON", 
            "Bruno Vivan", 
            "-", 
            "Não", 
            "0", 
            "14563519.1.2020.5355", 
            "Lucas Sierra", 
            "Lucas.sierra@astrazeneca.com", 
            "Intervencional", 
            "Oral", 
            "Não pedir mini-protocolo"
        ));

        // --- 4. MAHOGANY ---
        listaDeEstudos.add(new Estudo(
            "MAHOGANY", 
            "BGB-3111-308", 
            "BeOne", 
            "Jaisson André Pagnoncelli Bortolini", 
            "BeOne", 
            "Camila Sommer", 
            "Linfoma Folicular", 
            "Sim", 
            "55082", 
            "69242623.0.2014.5355", 
            "Mariana Pezzini", 
            "marina.pezzini@beigene.com", 
            "Intervencional", 
            "Oral", 
            "Pedir mini-protocolo"
        ));
    }

    public static ObservableList<Estudo> getEstudos() {
        return listaDeEstudos;
    }

    // Método para permitir que o cadastro manual continue funcionando
    public static void adicionarEstudo(Estudo estudo) {
        listaDeEstudos.add(estudo);
    }
}