package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import model.CalculoHonorarios;
import model.Empresa;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // Campos de entrada
    private JTextField txtRazaoSocial;
    private JFormattedTextField txtCNPJ;
    private JComboBox<String> SelecaoRegime;
    private JComboBox<String> SelecaoRamoAtividade;
    private JFormattedTextField txtFuncionarios;
    private JFormattedTextField txtSocios;
    private JFormattedTextField NotaMensal;
    private JFormattedTextField FaturamentoMensal;

    private JRadioButton rbProLaboreSim;
    private JRadioButton rbProLaboreNao;
    private JRadioButton rbEmiteNfSim;
    private JRadioButton rbEmiteNfNao;

    // Checkboxes de serviços (agora Swing e como atributos)
    private JCheckBox chkDepartamentoPessoal;
    private JCheckBox chkEscritaFiscal;
    private JCheckBox chkContabilidade;
    private JCheckBox chkSocietario;

    // Observações e resultado
    private JTextArea txtObservacoes;
    private JLabel lblResultadoValor;

    // Guarda o último valor calculado (usado na impressão)
    private double ultimoHonorario = 0.0;

    // Motor de cálculo (lógica fora da tela)
    private final CalculoHonorarios calculadora = new CalculoHonorarios();

 

    public TelaPrincipal() {
        setTitle("Calculadora de Honorários Contábeis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 860, 508);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel_2.setBounds(359, 209, 93, 14);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblObservacoes = new JLabel("Observações");
        lblObservacoes.setVerticalAlignment(SwingConstants.BOTTOM);
        lblObservacoes.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblObservacoes.setForeground(new Color(0, 64, 128));
        lblObservacoes.setBounds(10, 0, 83, 14);
        panel_2.add(lblObservacoes);

        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(172, 172, 172));
        toolBar.setBounds(0, 0, 844, 22);
        contentPane.add(toolBar);

        JLabel lblBanner = new JLabel("Calculadora de Honorários Contábeis");
        lblBanner.setForeground(new Color(255, 255, 255));
        toolBar.add(lblBanner);

        JLabel lblSecaoDados = new JLabel("Dados da Empresa");
        lblSecaoDados.setForeground(new Color(0, 64, 128));
        lblSecaoDados.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSecaoDados.setBounds(10, 33, 133, 14);
        contentPane.add(lblSecaoDados);

        // ---------- Coluna da esquerda ----------
        JLabel lblRazaoSocial = new JLabel("Razão Social:");
        lblRazaoSocial.setBounds(20, 58, 76, 14);
        contentPane.add(lblRazaoSocial);

        txtRazaoSocial = new JTextField();
        txtRazaoSocial.setBounds(148, 55, 190, 20);
        contentPane.add(txtRazaoSocial);
        txtRazaoSocial.setColumns(10);

        JLabel lblCnpj = new JLabel("CNPJ:");
        lblCnpj.setBounds(20, 83, 76, 14);
        contentPane.add(lblCnpj);

        txtCNPJ = new JFormattedTextField();
        try {
            MaskFormatter mascara = new MaskFormatter("##.###.###/####-##");
            mascara.install(txtCNPJ);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtCNPJ.setBounds(148, 83, 190, 20);
        contentPane.add(txtCNPJ);

        JLabel lblRegime = new JLabel("Regime Tributário:");
        lblRegime.setBounds(20, 108, 107, 14);
        contentPane.add(lblRegime);

        SelecaoRegime = new JComboBox<>();
        SelecaoRegime.addItem("Simples Nacional");
        SelecaoRegime.addItem("Lucro Presumido");
        SelecaoRegime.addItem("Lucro Real");
        SelecaoRegime.setBounds(148, 107, 133, 22);
        contentPane.add(SelecaoRegime);

        JLabel lblRamo = new JLabel("Ramo de Atividade:");
        lblRamo.setBounds(20, 133, 123, 14);
        contentPane.add(lblRamo);

        SelecaoRamoAtividade = new JComboBox<>();
        SelecaoRamoAtividade.addItem("Indústria");
        SelecaoRamoAtividade.addItem("Comércio");
        SelecaoRamoAtividade.addItem("Serviço");
        SelecaoRamoAtividade.setBounds(148, 133, 133, 22);
        contentPane.add(SelecaoRamoAtividade);

        JLabel lblEmiteNf = new JLabel("Emite NF:");
        lblEmiteNf.setBounds(20, 161, 76, 14);
        contentPane.add(lblEmiteNf);

        rbEmiteNfSim = new JRadioButton("Sim");
        rbEmiteNfSim.setBounds(146, 157, 47, 23);
        contentPane.add(rbEmiteNfSim);

        rbEmiteNfNao = new JRadioButton("Não");
        rbEmiteNfNao.setBounds(203, 157, 47, 23);
        contentPane.add(rbEmiteNfNao);

        ButtonGroup grupoEmiteNf = new ButtonGroup();
        grupoEmiteNf.add(rbEmiteNfSim);
        grupoEmiteNf.add(rbEmiteNfNao);
        rbEmiteNfNao.setSelected(true);

        // ---------- Coluna da direita ----------
        JLabel lblFuncionarios = new JLabel("Funcionários:");
        lblFuncionarios.setBounds(393, 55, 76, 14);
        contentPane.add(lblFuncionarios);

        txtFuncionarios = new JFormattedTextField(criarFormatterInteiro());
        txtFuncionarios.setValue(0);
        txtFuncionarios.setBounds(589, 49, 63, 20);
        contentPane.add(txtFuncionarios);

        JLabel lblProLabore = new JLabel("Pró-labore:");
        lblProLabore.setBounds(393, 83, 76, 14);
        contentPane.add(lblProLabore);

        rbProLaboreSim = new JRadioButton("Sim");
        rbProLaboreSim.setBounds(589, 76, 47, 23);
        contentPane.add(rbProLaboreSim);

        rbProLaboreNao = new JRadioButton("Não");
        rbProLaboreNao.setBounds(638, 76, 47, 23);
        contentPane.add(rbProLaboreNao);

        ButtonGroup grupoProLabore = new ButtonGroup();
        grupoProLabore.add(rbProLaboreSim);
        grupoProLabore.add(rbProLaboreNao);
        rbProLaboreNao.setSelected(true);

        JLabel lblSocios = new JLabel("Sócios:");
        lblSocios.setBounds(393, 108, 76, 14);
        contentPane.add(lblSocios);

        txtSocios = new JFormattedTextField(criarFormatterInteiro());
        txtSocios.setValue(0);
        txtSocios.setBounds(589, 105, 63, 20);
        contentPane.add(txtSocios);

        JLabel lblNfMensal = new JLabel("Quantidade de NF/mês:");
        lblNfMensal.setBounds(393, 133, 133, 14);
        contentPane.add(lblNfMensal);

        NotaMensal = new JFormattedTextField(criarFormatterInteiro());
        NotaMensal.setValue(0);
        NotaMensal.setBounds(589, 130, 123, 20);
        contentPane.add(NotaMensal);

        JLabel lblFaturamento = new JLabel("Faturamento Mensal (R$):");
        lblFaturamento.setBounds(393, 161, 147, 14);
        contentPane.add(lblFaturamento);

        FaturamentoMensal = new JFormattedTextField(criarFormatterDecimal());
        FaturamentoMensal.setValue(0.0);
        FaturamentoMensal.setBounds(589, 158, 123, 20);
        contentPane.add(FaturamentoMensal);

        JPanel painelObservacao = new JPanel();
        painelObservacao.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        painelObservacao.setBounds(348, 217, 486, 98);
        contentPane.add(painelObservacao);
        painelObservacao.setLayout(null);

        txtObservacoes = new JTextArea();
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);

        JScrollPane scrollObservacoes = new JScrollPane(txtObservacoes);
        scrollObservacoes.setBounds(10, 15, 466, 73);
        scrollObservacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollObservacoes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelObservacao.add(scrollObservacoes);

        JPanel panel_2_1 = new JPanel();
        panel_2_1.setLayout(null);
        panel_2_1.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel_2_1.setBounds(359, 318, 76, 14);
        contentPane.add(panel_2_1);

        JLabel lblResultado = new JLabel("Resultado");
        lblResultado.setVerticalAlignment(SwingConstants.BOTTOM);
        lblResultado.setForeground(new Color(0, 64, 128));
        lblResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResultado.setBounds(10, 0, 62, 14);
        panel_2_1.add(lblResultado);

        JPanel resultadodoHonorario = new JPanel();
        resultadodoHonorario.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        resultadodoHonorario.setBounds(348, 326, 486, 83);
        contentPane.add(resultadodoHonorario);
        resultadodoHonorario.setLayout(null);

        JLabel lblHonorarioSugerido = new JLabel("Honorário sugerido:");
        lblHonorarioSugerido.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblHonorarioSugerido.setBounds(10, 30, 163, 24);
        resultadodoHonorario.add(lblHonorarioSugerido);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(232, 11, 244, 61);
        resultadodoHonorario.add(panel_1);
        panel_1.setLayout(null);

        // Label que mostra o valor calculado (não editável)
        lblResultadoValor = new JLabel("R$ 0,00");
        lblResultadoValor.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultadoValor.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblResultadoValor.setForeground(new Color(0, 64, 128));
        lblResultadoValor.setBounds(10, 11, 224, 39);
        panel_1.add(lblResultadoValor);

        JPanel panelServicosContratados = new JPanel();
        panelServicosContratados.setLayout(null);
        panelServicosContratados.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelServicosContratados.setBounds(20, 209, 147, 14);
        contentPane.add(panelServicosContratados);

        JLabel lblServicosContratados = new JLabel("Serviços Contratados");
        lblServicosContratados.setVerticalAlignment(SwingConstants.BOTTOM);
        lblServicosContratados.setForeground(new Color(0, 64, 128));
        lblServicosContratados.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblServicosContratados.setBounds(10, 0, 131, 14);
        panelServicosContratados.add(lblServicosContratados);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 219, 328, 123);
        contentPane.add(panel);
        panel.setLayout(null);

        chkDepartamentoPessoal = new JCheckBox("Departamento Pessoal");
        chkDepartamentoPessoal.setBounds(10, 10, 180, 22);
        panel.add(chkDepartamentoPessoal);

        chkEscritaFiscal = new JCheckBox("Escrita Fiscal");
        chkEscritaFiscal.setBounds(10, 38, 120, 22);
        panel.add(chkEscritaFiscal);

        chkContabilidade = new JCheckBox("Contabilidade");
        chkContabilidade.setBounds(10, 66, 120, 22);
        panel.add(chkContabilidade);

        chkSocietario = new JCheckBox("Societário");
        chkSocietario.setBounds(10, 94, 120, 22);
        panel.add(chkSocietario);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(451, 435, 89, 23);
        btnCalcular.addActionListener(e -> calcularHonorario());
        contentPane.add(btnCalcular);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(547, 435, 89, 23);
        btnLimpar.addActionListener(e -> limparCampos());
        contentPane.add(btnLimpar);

        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(646, 435, 89, 23);
        btnImprimir.addActionListener(e -> imprimirRelatorio());
        contentPane.add(btnImprimir);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBounds(745, 435, 89, 23);
        btnFechar.addActionListener(e -> dispose());
        contentPane.add(btnFechar);
    }

    // ================= LÓGICA DA TELA =================

    /** Lê tudo o que está na tela e devolve um objeto Empresa preenchido. */
    private Empresa getEmpresa() {
        Empresa e = new Empresa();
        e.setRazaoSocial(txtRazaoSocial.getText());
        e.setCnpj(txtCNPJ.getText());
        e.setRegime((String) SelecaoRegime.getSelectedItem());
        e.setRamo((String) SelecaoRamoAtividade.getSelectedItem());
        e.setFuncionarios(lerInteiro(txtFuncionarios));
        e.setSocios(lerInteiro(txtSocios));
        e.setNotasFiscais(lerInteiro(NotaMensal));
        e.setFaturamento(lerDouble(FaturamentoMensal));
        e.setProLabore(rbProLaboreSim.isSelected());
        e.setEmiteNf(rbEmiteNfSim.isSelected());
        e.setDepartamentoPessoal(chkDepartamentoPessoal.isSelected());
        e.setEscritaFiscal(chkEscritaFiscal.isSelected());
        e.setContabilidade(chkContabilidade.isSelected());
        e.setSocietario(chkSocietario.isSelected());
        e.setObservacoes(txtObservacoes.getText());
        return e;
    }

    private void calcularHonorario() {
        Empresa empresa = getEmpresa();

        if (empresa.getRazaoSocial() == null || empresa.getRazaoSocial().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe a Razão Social da empresa.",
                    "Dados incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ultimoHonorario = calculadora.calcular(empresa);

        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblResultadoValor.setText(moeda.format(ultimoHonorario));
    }

    private void limparCampos() {
        txtRazaoSocial.setText("");
        txtCNPJ.setValue(null);
        SelecaoRegime.setSelectedIndex(0);
        SelecaoRamoAtividade.setSelectedIndex(0);
        txtFuncionarios.setValue(0);
        txtSocios.setValue(0);
        NotaMensal.setValue(0);
        FaturamentoMensal.setValue(0.0);
        rbProLaboreNao.setSelected(true);
        rbEmiteNfNao.setSelected(true);
        chkDepartamentoPessoal.setSelected(false);
        chkEscritaFiscal.setSelected(false);
        chkContabilidade.setSelected(false);
        chkSocietario.setSelected(false);
        txtObservacoes.setText("");
        ultimoHonorario = 0.0;
        lblResultadoValor.setText("R$ 0,00");
    }

    private void imprimirRelatorio() {
        Empresa e = getEmpresa();
        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        StringBuilder sb = new StringBuilder();
        sb.append("CALCULADORA DE HONORÁRIOS CONTÁBEIS\n\n");
        sb.append("RAZÃO SOCIAL: ").append(e.getRazaoSocial()).append("\n");
        sb.append("CNPJ: ").append(e.getCnpj()).append("\n\n");
        sb.append("REGIME TRIBUTÁRIO: ").append(e.getRegime()).append("\n");
        sb.append("RAMO DE ATIVIDADE: ").append(e.getRamo()).append("\n\n");
        sb.append("FUNCIONÁRIOS: ").append(e.getFuncionarios()).append("\n");
        sb.append("PRÓ-LABORE: ").append(e.isProLabore() ? "Sim" : "Não").append("\n");
        sb.append("SÓCIOS: ").append(e.getSocios()).append("\n");
        sb.append("NF/MÊS: ").append(e.getNotasFiscais()).append("\n");
        sb.append("FATURAMENTO: ").append(moeda.format(e.getFaturamento())).append("\n");
        sb.append("EMITE NF: ").append(e.isEmiteNf() ? "Sim" : "Não").append("\n\n");
        sb.append("SERVIÇOS CONTRATADOS:\n");
        if (e.isDepartamentoPessoal()) sb.append("- Departamento Pessoal\n");
        if (e.isEscritaFiscal())       sb.append("- Escrita Fiscal\n");
        if (e.isContabilidade())       sb.append("- Contabilidade\n");
        if (e.isSocietario())          sb.append("- Societário\n");
        sb.append("\nHONORÁRIO SUGERIDO:\n");
        sb.append(moeda.format(ultimoHonorario)).append("\n\n");
        sb.append("OBSERVAÇÕES:\n");
        sb.append(e.getObservacoes()).append("\n");

        // Usamos o print() pronto do JTextArea: ele já pagina e abre
        // a caixa de diálogo de impressão do sistema. Bem mais simples
        // que implementar um Printable manualmente.
        JTextArea area = new JTextArea(sb.toString());
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        try {
            area.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível imprimir: " + ex.getMessage(),
                    "Erro de impressão", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= AUXILIARES =================

    /** Lê um JFormattedTextField como inteiro, sem quebrar. */
    private int lerInteiro(JFormattedTextField campo) {
        try {
            campo.commitEdit();
        } catch (ParseException ex) {
            // valor inválido no campo; usaremos o último valor válido/0
        }
        Object v = campo.getValue();
        return (v instanceof Number) ? ((Number) v).intValue() : 0;
    }

    /** Lê um JFormattedTextField como double, sem quebrar. */
    private double lerDouble(JFormattedTextField campo) {
        try {
            campo.commitEdit();
        } catch (ParseException ex) {
        }
        Object v = campo.getValue();
        return (v instanceof Number) ? ((Number) v).doubleValue() : 0.0;
    }

    private NumberFormatter criarFormatterInteiro() {
        NumberFormat formato = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(formato);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);
        return formatter;
    }

    private NumberFormatter criarFormatterDecimal() {
        NumberFormat formato = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);
        NumberFormatter formatter = new NumberFormatter(formato);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);
        return formatter;
    }
}