package br.com.regge;

// --- Imports Básicos ---
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; // <--- NOVO
import java.util.HashMap;   // <--- NOVO
import java.util.List;      // <--- NOVO
import java.util.Map;       // <--- NOVO

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ToggleGroup grupoInvestigador;
    private ObservableList<DesvioRegistro> listaDesvios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (lblAvisoErro != null) {
            lblAvisoErro.setVisible(false);
            lblAvisoErro.setText("Deve ser marcada o pesquisador rsponsavel, Coordenador do estudo e minimo de 01 linha na tabela acima");
        }

        grupoInvestigador = new ToggleGroup();
        if (rbInvestigadorPrincipal != null && rbSubInvestigador != null) {
            rbInvestigadorPrincipal.setToggleGroup(grupoInvestigador);
            rbSubInvestigador.setToggleGroup(grupoInvestigador);

            rbInvestigadorPrincipal.setOnAction(e -> atualizarComboPesquisador(true));
            rbSubInvestigador.setOnAction(e -> atualizarComboPesquisador(false));

            rbInvestigadorPrincipal.setSelected(true);
        }

        configurarTabela();

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

    private void configuringTabela() {
        // Correção de digitação: configurarTabela
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

    // Método duplicado corrigido para configurarTabela acima
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

    private void atualizarComboPesquisador(boolean isPrincipal) {
        if (cmbPesquisadorResponsavel == null) {
            return;
        }
        cmbPesquisadorResponsavel.getItems().clear();

        if (isPrincipal) {
            String nomeEstudo = estudosDesvioP.getValue();
            if (nomeEstudo != null) {
                for (Estudo e : BancoDeDadosFake.getEstudos()) {
                    if (e.getNome().equals(nomeEstudo)) {
                        cmbPesquisadorResponsavel.getItems().add(e.getPi());
                        cmbPesquisadorResponsavel.getSelectionModel().selectFirst();
                        break;
                    }
                }
            }
        } else {
            for (Membro m : BancoDeDadosMembros.getMembros()) {
                cmbPesquisadorResponsavel.getItems().add(m.getNome());
            }
        }
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
            listaDesvios.add(registro);
            tabListaDesvioP.refresh();
            if (lblAvisoErro != null) {
                lblAvisoErro.setVisible(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- NOVA LÓGICA: AGRUPAMENTO POR ESTUDO ---
    @FXML
    void onBtnGerarDesvioP(ActionEvent event) {
        System.out.println(">>> INICIANDO GERAÇÃO AGRUPADA... <<<");

        // 1. Validações
        boolean pesquisadorOk = cmbPesquisadorResponsavel.getValue() != null && !cmbPesquisadorResponsavel.getValue().isEmpty();
        boolean coordenadorOk = cmbCoordenador.getValue() != null;

        // Verifica linhas selecionadas e AGRUPA por estudo
        Map<String, List<DesvioRegistro>> mapaAgrupado = new HashMap<>();
        boolean linhaOk = false;

        for (DesvioRegistro r : listaDesvios) {
            if (r.getSelecionar().isSelected()) {
                linhaOk = true;
                // Pega a lista desse estudo (ou cria uma nova se não existir) e adiciona o registro
                mapaAgrupado.computeIfAbsent(r.getEstudo(), k -> new ArrayList<>()).add(r);
            }
        }

        if (!pesquisadorOk || !coordenadorOk || !linhaOk) {
            if (lblAvisoErro != null) {
                lblAvisoErro.setVisible(true);
            }
            return;
        }
        if (lblAvisoErro != null) {
            lblAvisoErro.setVisible(false);
        }

        // Dados de assinatura
        String nomeInvestigadorAssinatura = cmbPesquisadorResponsavel.getValue();
        String cargoInvestigadorAssinatura = rbInvestigadorPrincipal.isSelected() ? "Investigador Principal" : "Sub-Investigador";
        String nomeCoordenadorAssinatura = cmbCoordenador.getValue().getNome();

        // Data Atual para Status
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraAtual = LocalDate.now().atTime(java.time.LocalTime.now()).format(fmt);

        // --- GERAÇÃO DOS PDS (UM POR GRUPO/ESTUDO) ---
        // Percorre o mapa: para cada Estudo (chave), temos uma Lista de Registros (valor)
        for (Map.Entry<String, List<DesvioRegistro>> entrada : mapaAgrupado.entrySet()) {
            String nomeDoEstudo = entrada.getKey();
            List<DesvioRegistro> listaDeRegistrosDoEstudo = entrada.getValue();

            // Gera UM PDF contendo TODAS as linhas dessa lista
            gerarPDFAgrupado(nomeDoEstudo, listaDeRegistrosDoEstudo, nomeInvestigadorAssinatura, cargoInvestigadorAssinatura, nomeCoordenadorAssinatura);

            // Atualiza o status de todos que foram impressos
            for (DesvioRegistro reg : listaDeRegistrosDoEstudo) {
                reg.setStatus("Gerado");
                reg.setDataGeracao(dataHoraAtual);
            }
        }

        tabListaDesvioP.refresh();
        System.out.println("Processo concluído. PDFs gerados.");
    }

    // Método que gera o PDF recebendo uma LISTA de registros
    private void gerarPDFAgrupado(String nomeEstudo, List<DesvioRegistro> listaRegistros, String nomeInv, String cargoInv, String nomeCoord) {
        try {
            // Nome do arquivo: Desvio_ESTUDO_Timestamp.pdf (para evitar sobrescrever)
            String timestamp = String.valueOf(System.currentTimeMillis());
            String nomeArquivo = "Desvio_" + nomeEstudo.replace(" ", "_") + "_" + timestamp + ".pdf";

            Document documento = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(documento, new FileOutputStream(nomeArquivo));
            documento.open();

            // --- CABEÇALHO ---
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

            // --- INTRO ---
            Font fonteTexto = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);
            Paragraph pIntro = new Paragraph("A Coordenadora do Comitê de Ética em Pesquisa com Seres Humanos: Sra Maria Luiza Vieira e Vieira.\n"
                    + "Vimos por meio desta, encaminhar ao Comitê de Ética, os desvios na condução do protocolo que estão abaixo descritos:", fonteTexto);
            pIntro.setSpacingAfter(15);
            documento.add(pIntro);

            // --- TABELA PRINCIPAL ---
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

            // --- LOOP PARA ADICIONAR VÁRIAS LINHAS NA MESMA TABELA ---
            for (DesvioRegistro reg : listaRegistros) {
                adicionarCelula(tabelaDados, reg.getEstudo(), fonteCelula);
                adicionarCelula(tabelaDados, reg.getPatrocinador(), fonteCelula);
                adicionarCelula(tabelaDados, reg.getInvestigador(), fonteCelula); // Usa o investigador do registro
                adicionarCelula(tabelaDados, "Desvio", fonteCelula);
                adicionarCelula(tabelaDados, reg.getNomePaciente(), fonteCelula);
                adicionarCelula(tabelaDados, reg.getDescricao(), fonteCelula);
                adicionarCelula(tabelaDados, "Reorientação", fonteCelula);
                adicionarCelula(tabelaDados, reg.getDataOcorrencia(), fonteCelula);
                adicionarCelula(tabelaDados, reg.getDataDesvio(), fonteCelula);
            }

            documento.add(tabelaDados);

            // --- ASSINATURAS ---
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            PdfPTable tabelaAssinaturas = new PdfPTable(2);
            tabelaAssinaturas.setWidthPercentage(100);
            tabelaAssinaturas.setKeepTogether(true);

            Font fonteAssinatura = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

            // Assinatura Esquerda (Investigador Selecionado AGORA)
            PdfPCell ass1 = new PdfPCell();
            ass1.setBorder(0);
            ass1.setHorizontalAlignment(Element.ALIGN_CENTER);
            ass1.addElement(new Paragraph("__________________________", fonteTexto));
            ass1.addElement(new Paragraph(nomeInv, fonteTexto));
            ass1.addElement(new Paragraph(cargoInv, fonteAssinatura));

            // Assinatura Direita (Coordenador Selecionado AGORA)
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

            // Abre o PDF gerado
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
}
