package br.com.regge;

// --- Imports Básicos ---
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// --- Imports JavaFX ---
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

// --- Imports PDF ---
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class DesvioProtocoloController {

    // --- Campos do Formulário ---
    @FXML
    private ComboBox<String> estudosDesvioP;
    @FXML
    private TextField piDesvioP;
    @FXML
    private TextField nCentroDesvioP;
    @FXML
    private ComboBox<Participante> nomePacienteDesvioP;
    @FXML
    private TextField nPacienteDesvioP;
    @FXML
    private DatePicker dataOcorrenciaDesvioP;
    @FXML
    private DatePicker dataDesvioP;
    @FXML
    private TextArea descricaoDesvioP;

    // Assinaturas e Seleção
    @FXML
    private RadioButton rbInvestigadorPrincipal;
    @FXML
    private RadioButton rbSubInvestigador;
    @FXML
    private ComboBox<String> cmbPesquisadorResponsavel;
    @FXML
    private ComboBox<Membro> cmbCoordenador;
    @FXML
    private Label lblAvisoErro;

    // --- Tabela e Colunas ---
    @FXML
    private TableView<DesvioRegistro> tabListaDesvioP;
    @FXML
    private TableColumn<DesvioRegistro, Object> colSelecionar;
    @FXML
    private TableColumn<DesvioRegistro, String> colEstudo;
    @FXML
    private TableColumn<DesvioRegistro, String> colInvestigador;
    @FXML
    private TableColumn<DesvioRegistro, String> colCentro;
    @FXML
    private TableColumn<DesvioRegistro, String> colNomePaciente;
    @FXML
    private TableColumn<DesvioRegistro, String> colNumPaciente;
    @FXML
    private TableColumn<DesvioRegistro, String> colDataOcorrencia;
    @FXML
    private TableColumn<DesvioRegistro, String> colDataDesvio;
    @FXML
    private TableColumn<DesvioRegistro, String> colStatus;
    @FXML
    private TableColumn<DesvioRegistro, String> colDataGeracao;
    @FXML
    private TableColumn<DesvioRegistro, Void> colAcoes;

    private ToggleGroup grupoInvestigador;
    private ObservableList<DesvioRegistro> listaDesvios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (lblAvisoErro != null) {
            lblAvisoErro.setVisible(false);
            lblAvisoErro.setText("ERRO: Selecione linhas do MESMO estudo e preencha as assinaturas.");
        }

        // Configuração das Colunas Fixas
        if (colSelecionar != null) {
            colSelecionar.setPrefWidth(40);
            colSelecionar.setMinWidth(40);
            colSelecionar.setMaxWidth(40);
            colSelecionar.setResizable(false);
        }
        if (colAcoes != null) {
            colAcoes.setPrefWidth(90);
            colAcoes.setMinWidth(90);
            colAcoes.setMaxWidth(90);
            colAcoes.setResizable(false);
        }

        grupoInvestigador = new ToggleGroup();
        if (rbInvestigadorPrincipal != null && rbSubInvestigador != null) {
            rbInvestigadorPrincipal.setToggleGroup(grupoInvestigador);
            rbSubInvestigador.setToggleGroup(grupoInvestigador);

            // Agora atualiza baseado na TABELA, se houver seleção
            rbInvestigadorPrincipal.setOnAction(e -> atualizarComboPesquisador(true));
            rbSubInvestigador.setOnAction(e -> atualizarComboPesquisador(false));

            rbInvestigadorPrincipal.setSelected(true);
        }

        configurarTabela();
        configurarColunaAcoes();

        if (estudosDesvioP != null) {
            for (Estudo estudo : BancoDeDadosFake.getEstudos()) {
                estudosDesvioP.getItems().add(estudo.getNome());
            }
            estudosDesvioP.setOnAction(event -> aoSelecionarEstudo());
        }

        if (cmbCoordenador != null) {
            for (Membro m : BancoDeDadosMembros.getMembros()) {
                if (m.getFuncao().toLowerCase().contains("coordenador")) {
                    cmbCoordenador.getItems().add(m);
                }
            }
        }

        if (nomePacienteDesvioP != null) {
            nomePacienteDesvioP.setOnAction(event -> {
                Participante p = nomePacienteDesvioP.getValue();
                if (p != null && nPacienteDesvioP != null) {
                    nPacienteDesvioP.setText(p.getNumero());
                }
            });
        }
    }

    private void configurarTabela() {
        colSelecionar.setCellValueFactory(new PropertyValueFactory<>("selecionar"));
        colEstudo.setCellValueFactory(new PropertyValueFactory<>("estudo"));
        colInvestigador.setCellValueFactory(new PropertyValueFactory<>("investigador"));
        colCentro.setCellValueFactory(new PropertyValueFactory<>("centro"));
        colNomePaciente.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
        colNumPaciente.setCellValueFactory(new PropertyValueFactory<>("numPaciente"));
        colDataOcorrencia.setCellValueFactory(new PropertyValueFactory<>("dataOcorrencia"));
        colDataDesvio.setCellValueFactory(new PropertyValueFactory<>("dataDesvio"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDataGeracao.setCellValueFactory(new PropertyValueFactory<>("dataGeracao"));
        tabListaDesvioP.setItems(listaDesvios);
    }

    private void configurarColunaAcoes() {
        if (colAcoes == null) {
            return;
        }

        Callback<TableColumn<DesvioRegistro, Void>, TableCell<DesvioRegistro, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<DesvioRegistro, Void> call(final TableColumn<DesvioRegistro, Void> param) {
                return new TableCell<>() {
                    private final Button btnEditar = new Button("✎");
                    private final Button btnDeletar = new Button("✖");
                    private final HBox container = new HBox(5);

                    {
                        btnEditar.setStyle("-fx-text-fill: white; -fx-background-color: #2196F3; -fx-font-weight: bold; -fx-background-radius: 5;");
                        btnDeletar.setStyle("-fx-text-fill: white; -fx-background-color: #F44336; -fx-font-weight: bold; -fx-background-radius: 5;");
                        container.setAlignment(Pos.CENTER);
                        container.getChildren().addAll(btnEditar, btnDeletar);

                        btnEditar.setOnAction((ActionEvent event) -> {
                            DesvioRegistro registro = getTableView().getItems().get(getIndex());
                            carregarDadosParaEdicao(registro);
                        });

                        btnDeletar.setOnAction((ActionEvent event) -> {
                            DesvioRegistro registro = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmar Exclusão");
                            alert.setHeaderText(null);
                            alert.setContentText("Deseja deletar o registro de " + registro.getNomePaciente() + "?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                listaDesvios.remove(registro);
                                tabListaDesvioP.refresh();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(container);
                        }
                    }
                };
            }
        };
        colAcoes.setCellFactory(cellFactory);
    }

    // --- NOVA LÓGICA DE ASSINATURA BASEADA NA TABELA ---
    // Método auxiliar: Descobre se temos linhas selecionadas e se são todas do mesmo estudo
    private String getEstudoUnicoSelecionado() {
        String estudoEncontrado = null;
        boolean temSelecao = false;

        for (DesvioRegistro r : listaDesvios) {
            if (r.getSelecionar().isSelected()) {
                temSelecao = true;
                if (estudoEncontrado == null) {
                    estudoEncontrado = r.getEstudo(); // Primeiro encontrado
                } else if (!estudoEncontrado.equals(r.getEstudo())) {
                    return "MISTO"; // Encontrou estudos diferentes misturados
                }
            }
        }
        return temSelecao ? estudoEncontrado : null; // Retorna o nome, "MISTO" ou null (se nada selecionado)
    }

    private void atualizarComboPesquisador(boolean isPrincipal) {
        if (cmbPesquisadorResponsavel == null) {
            return;
        }
        cmbPesquisadorResponsavel.getItems().clear();

        // 1. Tenta pegar o estudo da TABELA primeiro
        String estudoAlvo = getEstudoUnicoSelecionado();

        // 2. Se a tabela não tiver seleção válida, usa o formulário lá de cima (Comportamento antigo)
        if (estudoAlvo == null || estudoAlvo.equals("MISTO")) {
            estudoAlvo = estudosDesvioP.getValue();
        }

        if (isPrincipal) {
            // Busca o PI do estudo identificado (seja da tabela ou do form)
            if (estudoAlvo != null) {
                for (Estudo e : BancoDeDadosFake.getEstudos()) {
                    if (e.getNome().equals(estudoAlvo)) {
                        cmbPesquisadorResponsavel.getItems().add(e.getPi());
                        cmbPesquisadorResponsavel.getSelectionModel().selectFirst();
                        break;
                    }
                }
            }
        } else {
            // Se for Sub-Investigador, lista todos
            for (Membro m : BancoDeDadosMembros.getMembros()) {
                cmbPesquisadorResponsavel.getItems().add(m.getNome());
            }
        }
    }

    // --- GERAÇÃO DE PDF COM RESTRIÇÃO DE ESTUDO ÚNICO ---
    @FXML
    void onBtnGerarDesvioP(ActionEvent event) {
        System.out.println(">>> INICIANDO GERAÇÃO (MODO RESTRITO) <<<");

        // 1. Validar Seleção da Tabela (Regra: Mesmos Estudos)
        String estudoUnico = getEstudoUnicoSelecionado();

        if (estudoUnico == null) {
            mostrarErro("Nenhuma linha selecionada na tabela.");
            return;
        }

        if (estudoUnico.equals("MISTO")) {
            mostrarErro("ERRO: Você selecionou linhas de estudos diferentes!\nSelecione apenas linhas do mesmo estudo para gerar o PDF.");
            return;
        }

        // 2. Validar Assinaturas (Elas devem estar preenchidas lá embaixo)
        boolean pesquisadorOk = cmbPesquisadorResponsavel.getValue() != null && !cmbPesquisadorResponsavel.getValue().isEmpty();
        boolean coordenadorOk = cmbCoordenador.getValue() != null;

        if (!pesquisadorOk || !coordenadorOk) {
            mostrarErro("Selecione o Investigador e o Coordenador responsáveis pela assinatura.");
            return;
        }

        // Se passou daqui, está tudo certo! Esconde o erro.
        if (lblAvisoErro != null) {
            lblAvisoErro.setVisible(false);
        }

        // 3. Preparar Dados
        String nomeInvestigadorAssinatura = cmbPesquisadorResponsavel.getValue();
        String cargoInvestigadorAssinatura = rbInvestigadorPrincipal.isSelected() ? "Investigador Principal" : "Sub-Investigador";
        String nomeCoordenadorAssinatura = cmbCoordenador.getValue().getNome();

        // 4. Filtrar lista para enviar apenas os selecionados
        List<DesvioRegistro> linhasParaImprimir = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraAtual = LocalDate.now().atTime(java.time.LocalTime.now()).format(fmt);

        for (DesvioRegistro r : listaDesvios) {
            if (r.getSelecionar().isSelected()) {
                linhasParaImprimir.add(r);
                // Já atualiza o status na tabela
                r.setStatus("Gerado");
                r.setDataGeracao(dataHoraAtual);
            }
        }

        // 5. Gerar PDF Único
        gerarPDFUnico(estudoUnico, linhasParaImprimir, nomeInvestigadorAssinatura, cargoInvestigadorAssinatura, nomeCoordenadorAssinatura);

        tabListaDesvioP.refresh();
        autoAjustarColunas(tabListaDesvioP);
        System.out.println("PDF gerado com sucesso.");
    }

    private void mostrarErro(String msg) {
        if (lblAvisoErro != null) {
            lblAvisoErro.setText(msg);
            lblAvisoErro.setVisible(true);
        }
        System.out.println(msg);

        // Opcional: Mostrar um Popup também
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void gerarPDFUnico(String nomeEstudo, List<DesvioRegistro> listaRegistros, String nomeInv, String cargoInv, String nomeCoord) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String nomeArquivo = "Desvio_" + nomeEstudo.replace(" ", "_") + "_" + timestamp + ".pdf";

            Document documento = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(documento, new FileOutputStream(nomeArquivo));
            documento.open();

            // CABEÇALHO
            PdfPTable tabelaCabecalho = new PdfPTable(2);
            tabelaCabecalho.setWidthPercentage(100);
            tabelaCabecalho.setWidths(new float[]{1, 4});

            PdfPCell celulaLogo = new PdfPCell();
            celulaLogo.setBorder(0);
            try {
                String caminhoLogo = getClass().getResource("elora_assinatura_simbolo_verde.png").toExternalForm();
                Image img = Image.getInstance(caminhoLogo);
                img.scaleToFit(120, 60);
                celulaLogo.addElement(img);
            } catch (Exception e) {
                celulaLogo.addElement(new Paragraph("LOGO"));
            }
            tabelaCabecalho.addCell(celulaLogo);

            Font fonteTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph pTitulo = new Paragraph("Desvio de Protocolo - " + nomeEstudo, fonteTitulo);
            pTitulo.setAlignment(Element.ALIGN_CENTER);

            PdfPCell celulaTexto = new PdfPCell(pTitulo);
            celulaTexto.setBorder(0);
            celulaTexto.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaTexto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaCabecalho.addCell(celulaTexto);
            documento.add(tabelaCabecalho);
            documento.add(new Paragraph(" "));

            // INTRO
            Font fonteTexto = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);
            Paragraph pIntro = new Paragraph("A Coordenadora do Comitê de Ética em Pesquisa com Seres Humanos: Sra Maria Luiza Vieira e Vieira.\n"
                    + "Vimos por meio desta, encaminhar ao Comitê de Ética, os desvios na condução do protocolo que estão abaixo descritos:", fonteTexto);
            pIntro.setSpacingAfter(15);
            documento.add(pIntro);

            // TABELA
            PdfPTable tabelaDados = new PdfPTable(9);
            tabelaDados.setWidthPercentage(100);
            tabelaDados.setWidths(new float[]{2, 2, 2, 1.5f, 1.5f, 4, 3, 2, 2});

            Font fonteHeader = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
            Font fonteCelula = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);

            String[] titulos = {"Estudo", "Patroc.", "Investigador", "Tipo", "Nº Pac.", "Descrição", "Ação", "Data Ocor.", "Data Desvio"};
            for (String t : titulos) {
                PdfPCell c = new PdfPCell(new Phrase(t, fonteHeader));
                c.setBackgroundColor(BaseColor.DARK_GRAY);
                c.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaDados.addCell(c);
            }
            tabelaDados.setHeaderRows(1);

            for (DesvioRegistro reg : listaRegistros) {
                adicionarCelula(tabelaDados, reg.getEstudo(), fonteCelula);
                adicionarCelula(tabelaDados, reg.getPatrocinador(), fonteCelula);
                // Agora usamos o investigador SELECIONADO para assinar, não o da linha
                // Mas na tabela, a coluna 'Investigador' geralmente mostra o PI do estudo.
                // Vamos manter o dado original da linha aqui para registro histórico:
                adicionarCelula(tabelaDados, reg.getInvestigador(), fonteCelula);
                adicionarCelula(tabelaDados, "Desvio", fonteCelula);
                adicionarCelula(tabelaDados, reg.getNomePaciente(), fonteCelula);
                adicionarCelula(tabelaDados, reg.getDescricao(), fonteCelula);
                adicionarCelula(tabelaDados, "Reorientação", fonteCelula);
                adicionarCelula(tabelaDados, reg.getDataOcorrencia(), fonteCelula);
                adicionarCelula(tabelaDados, reg.getDataDesvio(), fonteCelula);
            }

            documento.add(tabelaDados);

            // ASSINATURAS
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            PdfPTable tabelaAssinaturas = new PdfPTable(2);
            tabelaAssinaturas.setWidthPercentage(100);
            tabelaAssinaturas.setKeepTogether(true);

            Font fonteAssinatura = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

            PdfPCell ass1 = new PdfPCell();
            ass1.setBorder(0);
            ass1.setHorizontalAlignment(Element.ALIGN_CENTER);
            ass1.addElement(new Paragraph("__________________________", fonteTexto));
            ass1.addElement(new Paragraph(nomeInv, fonteTexto));
            ass1.addElement(new Paragraph(cargoInv, fonteAssinatura));

            PdfPCell ass2 = new PdfPCell();
            ass2.setBorder(0);
            ass2.setHorizontalAlignment(Element.ALIGN_CENTER);
            ass2.addElement(new Paragraph("__________________________", fonteTexto));
            ass2.addElement(new Paragraph(nomeCoord, fonteTexto));
            ass2.addElement(new Paragraph("Coordenador de Estudos", fonteAssinatura));

            tabelaAssinaturas.addCell(ass1);
            tabelaAssinaturas.addCell(ass2);
            documento.add(tabelaAssinaturas);

            documento.close();
            java.awt.Desktop.getDesktop().open(new java.io.File(nomeArquivo));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarCelula(PdfPTable tabela, String texto, Font fonte) {
        PdfPCell c = new PdfPCell(new Phrase(texto, fonte));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tabela.addCell(c);
    }

    // --- Outros Métodos Auxiliares ---
    private void carregarDadosParaEdicao(DesvioRegistro registro) {
        estudosDesvioP.setValue(registro.getEstudo());
        aoSelecionarEstudo();

        nCentroDesvioP.setText(registro.getCentro());
        nPacienteDesvioP.setText(registro.getNumPaciente());
        descricaoDesvioP.setText(registro.getDescricao());

        for (Participante p : nomePacienteDesvioP.getItems()) {
            if (p.getNome().equals(registro.getNomePaciente())) {
                nomePacienteDesvioP.setValue(p);
                break;
            }
        }

        if (registro.getCoordenador() != null) {
            for (Membro m : cmbCoordenador.getItems()) {
                if (m.getNome().equals(registro.getCoordenador())) {
                    cmbCoordenador.setValue(m);
                    break;
                }
            }
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            if (!registro.getDataOcorrencia().equals("-")) {
                dataOcorrenciaDesvioP.setValue(LocalDate.parse(registro.getDataOcorrencia(), fmt));
            }
            if (!registro.getDataDesvio().equals("-")) {
                dataDesvioP.setValue(LocalDate.parse(registro.getDataDesvio(), fmt));
            }
        } catch (Exception e) {
            System.out.println("Erro data edição");
        }

        listaDesvios.remove(registro);
        tabListaDesvioP.refresh();
    }

    private void aoSelecionarEstudo() {
        String nomeEstudo = estudosDesvioP.getValue();
        if (nomeEstudo == null) {
            return;
        }

        Estudo estudoSelecionado = null;
        for (Estudo e : BancoDeDadosFake.getEstudos()) {
            if (e.getNome().equals(nomeEstudo)) {
                estudoSelecionado = e;
                break;
            }
        }

        if (estudoSelecionado != null) {
            if (nCentroDesvioP != null) {
                nCentroDesvioP.setText(estudoSelecionado.getNumeroCentro());
            }
            if (piDesvioP != null) {
                piDesvioP.setText(estudoSelecionado.getPi());
            }
        }

        if (nomePacienteDesvioP != null) {
            nomePacienteDesvioP.getItems().clear();
            if (nPacienteDesvioP != null) {
                nPacienteDesvioP.setText("");
            }
            for (Participante p : BancoDeDadosParticipantes.getParticipantes()) {
                if (p.getEstudo().equals(nomeEstudo)) {
                    nomePacienteDesvioP.getItems().add(p);
                }
            }
        }

        if (rbInvestigadorPrincipal != null && rbInvestigadorPrincipal.isSelected()) {
            atualizarComboPesquisador(true);
        }
    }

    @FXML
    void onBtnCadastrarDesvioP(ActionEvent event) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String estudo = (estudosDesvioP.getValue() != null) ? estudosDesvioP.getValue() : "-";
            String investigador = (cmbPesquisadorResponsavel.getValue() != null) ? cmbPesquisadorResponsavel.getValue() : "-";
            String centro = nCentroDesvioP.getText();
            String nomePac = (nomePacienteDesvioP.getValue() != null) ? nomePacienteDesvioP.getValue().getNome() : "";
            String numPac = nPacienteDesvioP.getText();
            String dtOcorrencia = (dataOcorrenciaDesvioP.getValue() != null) ? dataOcorrenciaDesvioP.getValue().format(fmt) : "-";
            String dtDesvio = (dataDesvioP.getValue() != null) ? dataDesvioP.getValue().format(fmt) : "-";
            String descricao = descricaoDesvioP.getText();
            String coordenador = (cmbCoordenador.getValue() != null) ? cmbCoordenador.getValue().getNome() : "";

            String patrocinador = "";
            for (Estudo e : BancoDeDadosFake.getEstudos()) {
                if (e.getNome().equals(estudo)) {
                    patrocinador = e.getPatrocinador();
                    break;
                }
            }

            DesvioRegistro registro = new DesvioRegistro(estudo, investigador, centro, nomePac, numPac,
                    dtOcorrencia, dtDesvio, descricao, patrocinador, coordenador);

            // --- A CORREÇÃO ESTÁ AQUI: O GATILHO DA CHECKBOX ---
            // Assim que cria a linha, adicionamos uma ação na caixinha de seleção dela
            registro.getSelecionar().setOnAction(e -> {
                boolean isPrincipal = rbInvestigadorPrincipal.isSelected();
                atualizarComboPesquisador(isPrincipal);
            });
            // ---------------------------------------------------

            listaDesvios.add(registro);
            tabListaDesvioP.refresh();

            autoAjustarColunas(tabListaDesvioP);

            if (lblAvisoErro != null) {
                lblAvisoErro.setVisible(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void autoAjustarColunas(TableView<?> tabela) {
        tabela.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tabela.getColumns().stream().forEach((coluna) -> {
            if (coluna == colSelecionar || coluna == colAcoes) {
                return;
            }
            Text titulo = new Text(coluna.getText());
            double larguraMaxima = titulo.getLayoutBounds().getWidth();
            for (int i = 0; i < tabela.getItems().size(); i++) {
                if (coluna.getCellData(i) != null) {
                    String textoCelula = coluna.getCellData(i).toString();
                    Text textoMedida = new Text(textoCelula);
                    double larguraTexto = textoMedida.getLayoutBounds().getWidth();
                    if (larguraTexto > larguraMaxima) {
                        larguraMaxima = larguraTexto;
                    }
                }
            }
            coluna.setPrefWidth(larguraMaxima + 20.0);
        });
    }
}
