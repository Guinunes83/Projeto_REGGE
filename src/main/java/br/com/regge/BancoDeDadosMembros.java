package br.com.regge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BancoDeDadosMembros {

    private static ObservableList<Membro> listaDeMembros;

    static {
        listaDeMembros = FXCollections.observableArrayList();

        // 1. Jaisson
        listaDeMembros.add(new Membro(
            "28", "Jaisson André Pagnoncelli Bortolini", "Hematologista", "005.368.559-86", 
            "12638", "7074", "15/02/1977", "M", "(48) 9919-0470", "jaisson.bortolini@grupoelora.org.br",
            "09/04/1902", "15/10/2025", "15/10/2027", "-", "-", "-", "-", "http://lattes.cnpq.br/2871832440316915"
        ));

        // 2. Karla
        listaDeMembros.add(new Membro(
            "33", "Karla Richter Zanella", "Hematologista", "771.554.400-00", 
            "8630", "5378", "16/10/1974", "F", "(48) 98414 1674", "karla.zanella@grupoelora.org.br",
            "09/04/1902", "15/10/2025", "15/10/2027", "-", "-", "-", "-", "-"
        ));

        // 3. Marcelo
        listaDeMembros.add(new Membro(
            "37", "Marcelo Roberto Pereira Freitas", "Oncologista", "027.823.049-36", 
            "11062", "9287", "08/12/1978", "M", "(48) 99946-5499", "docfreitas@gmail.com",
            "09/04/1902", "30/12/1901", "-", "-", "-", "-", "-", "-"
        ));

        // 4. Rafael
        listaDeMembros.add(new Membro(
            "45", "Rafael Balsini Barreto", "Oncologista", "064.507.359-88", 
            "17376", "14798", "04/04/1987", "M", "(48) 9959-8526", "rafael.barreto@grupoelora.org.br",
            "09/04/1902", "14/10/2025", "14/10/2027", "-", "-", "-", "-", "http://lattes.cnpq.br/6672615284118353"
        ));
        
        // 5. Bruno (Novo)
        listaDeMembros.add(new Membro(
            "XX", "Bruno Dalmagro Vivan", "Coordenador(a) de Estudos", "002.238.690-40", 
            "19974", "-", "11/08/1984", "M", "(51) 9.9167-2205", "bruno.vivan@grupoelora.org.br",
            "-", "-", "-", "-", "-", "-", "-", "-"
        ));
        
        // 6. Camila (Novo)
        listaDeMembros.add(new Membro(
            "XX", "Camila Sommer", "Coordenador(a) de Estudos", "942.814.670-68", 
            "9781", "-", "27/08/1977", "F", "(48) 9165-9521", "camila.sommer@grupoelora.org.br",
            "03/12/2024", "-", "-", "-", "-", "-", "-", "-"
        ));
    }

    public static ObservableList<Membro> getMembros() {
        return listaDeMembros;
    }
}