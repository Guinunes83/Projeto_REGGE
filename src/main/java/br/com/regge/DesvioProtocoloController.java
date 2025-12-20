package br.com.regge;

// --- Imports do Java Básico ---
import java.io.FileOutputStream;
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DesvioProtocoloController {

    @FXML
    private ComboBox<String> estudosDesvioP; 

    @FXML
    private TextField piDesvioP;

    @FXML
    private TextField nCentroDesvioP;

    @FXML
    private ComboBox<String> nomePacienteDesvioP;

    @FXML
    private TextField nPacienteDesvioP;

    @FXML
    private DatePicker dataOcorrenciaDesvioP;

    @FXML
    private DatePicker dataDesvioP;

    @FXML
    private TextArea descricaoDesvioP;

    @FXML
    public void initialize() {
        if (estudosDesvioP != null) {
            for (Estudo estudo : BancoDeDadosFake.getEstudos()) {
                estudosDesvioP.getItems().add(estudo.getNome());
            }
        }
        if (nomePacienteDesvioP != null) {
            nomePacienteDesvioP.getItems().addAll("Paciente 01", "Paciente 02", "Paciente 03");
        }
    }

    @FXML
    void onBtnCadastrarDesvioP(ActionEvent event) {
        System.out.println("Botão Cadastrar clicado");
    }

    @FXML
    void gerarRelatorioPDF(ActionEvent event) {
        System.out.println(">>> GERANDO PDF PAISAGEM COM LOGO NOVA... <<<");

        // --- 1. CAPTURA DE DADOS ---
        String valEstudo = (estudosDesvioP.getValue() != null) ? estudosDesvioP.getValue() : "";
        String valPatrocinador = "";
        for(Estudo e : BancoDeDadosFake.getEstudos()){
            if(e.getNome().equals(valEstudo)) {
                valPatrocinador = e.getMembro();
                break;
            }
        }
        String valInvestigador = piDesvioP.getText();
        String valTipo = "Desvio"; 
        String valPaciente = nPacienteDesvioP.getText();
        String valDescricao = descricaoDesvioP.getText();
        String valAcao = "Reorientação do Paciente"; 
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String valDataMonitoria = (dataDesvioP.getValue() != null) ? dataDesvioP.getValue().format(fmt) : "";
        String valDataAtual = java.time.LocalDate.now().format(fmt);

        Document documento = new Document(PageSize.A4.rotate()); 

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("Relatorio_Desvio_Completo.pdf"));
            documento.open();

            // --- 2. CABEÇALHO ---
            PdfPTable tabelaCabecalho = new PdfPTable(2);
            tabelaCabecalho.setWidthPercentage(100);
            tabelaCabecalho.setWidths(new float[]{1, 4});

            PdfPCell celulaLogo = new PdfPCell();
            celulaLogo.setBorder(0);
            try {
                // --- ATUALIZAÇÃO DA LOGO AQUI ---
                // Certifique-se que o arquivo está na pasta src/main/resources/br/com/regge/
                String caminhoLogo = getClass().getResource("elora_assinatura_simbolo_verde.png").toExternalForm();
                Image img = Image.getInstance(caminhoLogo);
                img.scaleToFit(120, 60); // Ajuste o tamanho se precisar
                celulaLogo.addElement(img);
            } catch (Exception e) {
                System.out.println("Erro ao carregar logo: " + e.getMessage());
                celulaLogo.addElement(new Paragraph("ELORA"));
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

            // --- 3. TEXTO INTRODUTÓRIO (ESPAÇAMENTO REDUZIDO) ---
            Font fonteTexto = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);
            String textoIntro = "A Coordenadora do Comitê de Ética em Pesquisa com Seres Humanos: Sra Maria Luiza Vieira e Vieira.\n" +
                                "Vimos por meio desta, encaminhar ao Comitê de Ética, os desvios na condução do protocolo que estão abaixo descritos:";
            
            Paragraph pIntro = new Paragraph(textoIntro, fonteTexto);
            pIntro.setAlignment(Element.ALIGN_JUSTIFIED);
            
            // --- AQUI ESTÁ A MÁGICA DO ESPAÇAMENTO ---
            // O valor padrão é uns 16. Colocando 12 fica bem juntinho.
            pIntro.setLeading(12); 
            pIntro.setSpacingAfter(15);
            documento.add(pIntro);

            // --- 4. TABELA PRINCIPAL ---
            PdfPTable tabelaDados = new PdfPTable(9);
            tabelaDados.setWidthPercentage(100);
            tabelaDados.setWidths(new float[]{2, 2, 2, 1.5f, 1.5f, 4, 3, 2, 2});
            
            Font fonteHeader = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
            Font fonteCelula = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);

            String[] titulos = {"Estudo", "Patroc.", "Investigador", "Tipo", "Nº Pac.", "Descrição", "Ação Tomada", "Data Monit.", "Data Atual"};
            
            for (String t : titulos) {
                PdfPCell c = new PdfPCell(new Phrase(t, fonteHeader));
                c.setBackgroundColor(BaseColor.DARK_GRAY);
                c.setHorizontalAlignment(Element.ALIGN_CENTER);
                c.setVerticalAlignment(Element.ALIGN_MIDDLE);
                c.setPadding(4);
                tabelaDados.addCell(c);
            }
            
            tabelaDados.setHeaderRows(1); 

            // Preenche dados
            adicionarCelulaTabela(tabelaDados, valEstudo, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valPatrocinador, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valInvestigador, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valTipo, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valPaciente, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valDescricao, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valAcao, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valDataMonitoria, fonteCelula);
            adicionarCelulaTabela(tabelaDados, valDataAtual, fonteCelula);

            documento.add(tabelaDados);

            // --- 5. ASSINATURAS ---
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" ")); 

            PdfPTable tabelaAssinaturas = new PdfPTable(2);
            tabelaAssinaturas.setWidthPercentage(100);
            tabelaAssinaturas.setKeepTogether(true); 

            Font fonteAssinaturaTitulo = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

            // Assinatura 1
            PdfPCell ass1 = new PdfPCell();
            ass1.setBorder(0);
            ass1.setHorizontalAlignment(Element.ALIGN_CENTER);
            ass1.addElement(new Paragraph("___________________________________", fonteTexto));
            ass1.addElement(new Paragraph("\"Nome do Pesquisador\"", fonteTexto)); 
            ass1.addElement(new Paragraph("Investigador Principal", fonteAssinaturaTitulo)); 
            
            // Assinatura 2
            PdfPCell ass2 = new PdfPCell();
            ass2.setBorder(0);
            ass2.setHorizontalAlignment(Element.ALIGN_CENTER);
            ass2.addElement(new Paragraph("___________________________________", fonteTexto));
            ass2.addElement(new Paragraph("\"Nome do Coordenador\"", fonteTexto)); 
            ass2.addElement(new Paragraph("Coordenador de Estudos", fonteAssinaturaTitulo));

            tabelaAssinaturas.addCell(ass1);
            tabelaAssinaturas.addCell(ass2);

            documento.add(tabelaAssinaturas);

            System.out.println("PDF Paisagem gerado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (documento.isOpen()) documento.close();
        }

        try {
            java.awt.Desktop.getDesktop().open(new java.io.File("Relatorio_Desvio_Completo.pdf"));
        } catch (Exception e) { 
            System.out.println("Não foi possível abrir o PDF automaticamente.");
        }
    }
    
    // --- MÉTODO AUXILIAR AJUSTADO PARA CENTRALIZAR ---
    private void adicionarCelulaTabela(PdfPTable tabela, String texto, Font fonte) {
        PdfPCell celula = new PdfPCell(new Phrase(texto, fonte));
        celula.setPadding(4);
        // Alinhamento Horizontal (Esquerda <-> Direita) = Centro
        celula.setHorizontalAlignment(Element.ALIGN_CENTER);
        // Alinhamento Vertical (Topo <-> Base) = Meio
        celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tabela.addCell(celula);
    }
}