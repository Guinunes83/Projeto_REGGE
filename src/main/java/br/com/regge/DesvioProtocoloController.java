package br.com.regge;

// --- Imports Básicos ---
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private TextField piDesvioP; // Pode ser invisível ou removido se não usar mais
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

    @FXML
    private RadioButton rbInvestigadorPrincipal;
    @FXML
    private RadioButton rbSubInvestigador;
    @FXML
    private ComboBox<String> cmbPesquisadorResponsavel;
    @FXML
    private ComboBox<Membro> cmbCoordenador;

    // --- Tabela e Colunas (Ids devem ser iguais no Scene Builder) ---
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
        System.out.println(">>> INICIANDO TELA DE DESVIO <<<");

        // Configura RadioButtons
        grupoInvestigador = new ToggleGroup();
        if (rbInvestigadorPrincipal != null && rbSubInvestigador != null) {
            rbInvestigadorPrincipal.setToggleGroup(grupoInvestigador);
            rbSubInvestigador.setToggleGroup(grupoInvestigador);

            rbInvestigadorPrincipal.setOnAction(e -> atualizarComboPesquisador(true));
            rbSubInvestigador.setOnAction(e -> atualizarComboPesquisador(false));

            // Define um padrão para não começar vazio
            rbInvestigadorPrincipal.setSelected(true);
        } else {
            System.err.println("ERRO: RadioButtons não encontrados! Verifique os fx:id no SceneBuilder.");
        }

        // Configura Tabela
        configurarTabela();

        // Carregar Estudos
        if (estudosDesvioP != null) {
            for (Estudo estudo : BancoDeDadosFake.getEstudos()) {
                estudosDesvioP.getItems().add(estudo.getNome());
            }
            estudosDesvioP.setOnAction(event -> aoSelecionarEstudo());
        }

        // Carregar Coordenadores
        if (cmbCoordenador != null) {
            for (Membro m : BancoDeDadosMembros.getMembros()) {
                if (m.getFuncao().toLowerCase().contains("coordenador")) {
                    cmbCoordenador.getItems().add(m);
                }
            }
        } else {
            System.err.println("ERRO: Combo Coordenador não encontrado! Verifique fx:id=cmbCoordenador");
        }

        // Listener Paciente
        if (nomePacienteDesvioP != null) {
            nomePacienteDesvioP.setOnAction(event -> {
                Participante p = nomePacienteDesvioP.getValue();
                if (p != null && nPacienteDesvioP != null) {
                    nPacienteDesvioP.setText(p.getNumero());
                }
            });
        }
    }

    private void configurarRadioButtons() {
        grupoInvestigador = new ToggleGroup();
        if (rbInvestigadorPrincipal != null && rbSubInvestigador != null) {
            rbInvestigadorPrincipal.setToggleGroup(grupoInvestigador);
            rbSubInvestigador.setToggleGroup(grupoInvestigador);

            // Lógica solicitada:
            rbInvestigadorPrincipal.setOnAction(e -> atualizarComboPesquisador(true));
            rbSubInvestigador.setOnAction(e -> atualizarComboPesquisador(false));
        }
    }

    private void configurarTabela() {
        // Dizendo para as colunas qual campo da classe DesvioRegistro elas devem olhar
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

    private void carregarDadosIniciais() {
        if (estudosDesvioP != null) {
            for (Estudo estudo : BancoDeDadosFake.getEstudos()) {
                estudosDesvioP.getItems().add(estudo.getNome());
            }
            estudosDesvioP.setOnAction(event -> aoSelecionarEstudo());
        }

        if (nomePacienteDesvioP != null) {
            nomePacienteDesvioP.setOnAction(event -> {
                Participante p = nomePacienteDesvioP.getValue();
                if (p != null) {
                    nPacienteDesvioP.setText(p.getNumero());
                }
            });
        }

        if (cmbCoordenador != null) {
            for (Membro m : BancoDeDadosMembros.getMembros()) {
                if (m.getFuncao().toLowerCase().contains("coordenador")) {
                    cmbCoordenador.getItems().add(m);
                }
            }
        }
    }

    // --- LÓGICA DO RADIO BUTTON ---
    private void atualizarComboPesquisador(boolean isPrincipal) {
        if (cmbPesquisadorResponsavel == null) {
            return;
        }
        cmbPesquisadorResponsavel.getItems().clear();

        if (isPrincipal) {
            // Se for Principal, pega SÓ o PI do estudo selecionado
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
            // Se for Sub-Investigador, lista TODOS os membros
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
            nPacienteDesvioP.setText("");
            for (Participante p : BancoDeDadosParticipantes.getParticipantes()) {
                if (p.getEstudo().equals(nomeEstudo)) {
                    nomePacienteDesvioP.getItems().add(p);
                }
            }
        }

        // Atualiza o combo de pesquisador caso já esteja marcado "Investigador Principal"
        if (rbInvestigadorPrincipal.isSelected()) {
            atualizarComboPesquisador(true);
        }
    }

    // --- BOTÃO CADASTRAR (ADICIONAR NA TABELA) ---
    @FXML
    void onBtnCadastrarDesvioP(ActionEvent event) {
        System.out.println(">>> CLIQUE NO BOTÃO CADASTRAR <<<");

        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String estudo = (estudosDesvioP.getValue() != null) ? estudosDesvioP.getValue() : "-";
            String investigador = (cmbPesquisadorResponsavel.getValue() != null) ? cmbPesquisadorResponsavel.getValue() : "-";
            String centro = nCentroDesvioP.getText();
            String nomePac = (nomePacienteDesvioP.getValue() != null) ? nomePacienteDesvioP.getValue().getNome() : "";
            String numPac = nPacienteDesvioP.getText();

            // Tratamento de datas vazias para não dar erro
            String dtOcorrencia = (dataOcorrenciaDesvioP.getValue() != null) ? dataOcorrenciaDesvioP.getValue().format(fmt) : "-";
            String dtDesvio = (dataDesvioP.getValue() != null) ? dataDesvioP.getValue().format(fmt) : "-";

            String descricao = descricaoDesvioP.getText();
            String coordenador = (cmbCoordenador.getValue() != null) ? cmbCoordenador.getValue().getNome() : "";

            // Busca Patrocinador
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
            tabListaDesvioP.refresh(); // Força a atualização visual

            System.out.println("SUCESSO: Registro adicionado! Total na lista: " + listaDesvios.size());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERRO AO CADASTRAR: " + e.getMessage());
        }
    }

    @FXML
    void onBtnGerarDesvioP(ActionEvent event) {
        System.out.println("Verificando itens selecionados para PDF...");

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraAtual = LocalDate.now().atTime(java.time.LocalTime.now()).format(fmt);

        boolean gerouAlgum = false;

        // Varre a tabela procurando checkboxes marcados
        for (DesvioRegistro registro : listaDesvios) {
            if (registro.getSelecionar().isSelected()) {
                // Gera o PDF para este registro
                gerarPDFIndividual(registro);

                // Atualiza a tabela
                registro.setStatus("Gerado");
                registro.setDataGeracao(dataHoraAtual);
                gerouAlgum = true;
            }
        }

        if (gerouAlgum) {
            tabListaDesvioP.refresh(); // Atualiza visualmente a tabela
            System.out.println("Processo concluído.");
        } else {
            System.out.println("Nenhum item selecionado na tabela.");
        }
    }

    // Método que gera o PDF usando os dados do OBJETO da tabela, e não da tela
    private void gerarPDFIndividual(DesvioRegistro dados) {
        try {
            // Nome do arquivo único para não sobreescrever (usa nome do paciente)
            String nomeArquivo = "Desvio_" + dados.getNomePaciente().replace(" ", "_") + ".pdf";

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
            Paragraph pTitulo = new Paragraph("Desvio de Protocolo", fonteTitulo);
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
            Paragraph pIntro = new Paragraph("A Coordenadora do Comitê de Ética... (Texto Padrão)", fonteTexto);
            pIntro.setSpacingAfter(15);
            documento.add(pIntro);

            // --- TABELA DE DADOS ---
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

            // USA OS DADOS DO REGISTRO DA TABELA
            adicionarCelula(tabelaDados, dados.getEstudo(), fonteCelula);
            adicionarCelula(tabelaDados, dados.getPatrocinador(), fonteCelula);
            adicionarCelula(tabelaDados, dados.getInvestigador(), fonteCelula);
            adicionarCelula(tabelaDados, "Desvio", fonteCelula);
            adicionarCelula(tabelaDados, dados.getNomePaciente(), fonteCelula);
            adicionarCelula(tabelaDados, dados.getDescricao(), fonteCelula);
            adicionarCelula(tabelaDados, "Reorientação", fonteCelula);
            adicionarCelula(tabelaDados, dados.getDataOcorrencia(), fonteCelula);
            adicionarCelula(tabelaDados, dados.getDataDesvio(), fonteCelula);

            documento.add(tabelaDados);

            // --- ASSINATURAS ---
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            PdfPTable tabelaAssinaturas = new PdfPTable(2);
            tabelaAssinaturas.setWidthPercentage(100);

            Font fonteAssinatura = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

            PdfPCell ass1 = new PdfPCell();
            ass1.setBorder(0);
            ass1.setHorizontalAlignment(Element.ALIGN_CENTER);
            ass1.addElement(new Paragraph("__________________________", fonteTexto));
            ass1.addElement(new Paragraph(dados.getInvestigador(), fonteTexto));
            ass1.addElement(new Paragraph("Investigador Principal", fonteAssinatura));

            PdfPCell ass2 = new PdfPCell();
            ass2.setBorder(0);
            ass2.setHorizontalAlignment(Element.ALIGN_CENTER);
            ass2.addElement(new Paragraph("__________________________", fonteTexto));
            ass2.addElement(new Paragraph(dados.getCoordenador(), fonteTexto));
            ass2.addElement(new Paragraph("Coordenador de Estudos", fonteAssinatura));

            tabelaAssinaturas.addCell(ass1);
            tabelaAssinaturas.addCell(ass2);
            documento.add(tabelaAssinaturas);

            documento.close();

            // Abre o arquivo
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
