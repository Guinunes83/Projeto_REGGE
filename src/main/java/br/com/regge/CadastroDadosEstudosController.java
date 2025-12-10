package br.com.regge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroDadosEstudosController {

    // Seus campos (IDs do FXML)
    @FXML private TextField tfProtocoloEstudo;
    @FXML private TextField tfPatrocinador;
    
    // Vou usar apenas esses dois + status fixo para o exemplo funcionar rápido.
    // Você pode adicionar os outros campos depois seguindo a lógica.

    @FXML
    void onBtnCadastrarClicked(ActionEvent event) {
        // 1. Ler dados da tela
        String nome = "Estudo Padrão"; // Pode adicionar um campo para isso depois
        String protocolo = (tfProtocoloEstudo != null) ? tfProtocoloEstudo.getText() : "Sem Protocolo";
        String patrocinador = (tfPatrocinador != null) ? tfPatrocinador.getText() : "Sem Patrocinador";
        String status = "Novo Cadastro"; // Vamos deixar fixo por enquanto

        // 2. Criar o objeto
        Estudo novo = new Estudo(nome, protocolo, patrocinador, status);

        // 3. Salvar no Banco Fake
        BancoDeDadosFake.adicionarEstudo(novo);

        System.out.println("Salvo! Total de estudos agora: " + BancoDeDadosFake.getEstudos().size());
    }
}