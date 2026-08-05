package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	// Campos de entrada promovidos para atributos da classe,
	// para poderem ser lidos depois no cálculo do honorário.
	private JTextField razaoSocial;
	private JFormattedTextField txtCNPJ;
	private JComboBox<String> selecaoRegime;
	private JComboBox<String> ramoAtividade;
	private JFormattedTextField campoFuncionarios;
	private JFormattedTextField campoSocios;
	private JFormattedTextField nfMensal;
	private JFormattedTextField faturamentoMensal;

	// Pró-labore
	private JRadioButton rbProLaboreSim;
	private JRadioButton rbProLaboreNao;

	// Emite NF
	private JRadioButton rbEmiteNfSim;
	private JRadioButton rbEmiteNfNao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Calculadora de Honorários Contábeis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Banner interno (opcional). O título da janela já é definido em
		// setTitle(); manter aqui é só uma faixa visual para combinar com o modelo.
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(172, 172, 172));
		toolBar.setBounds(0, 0, 844, 22);
		contentPane.add(toolBar);

		JLabel lblBanner = new JLabel("Calculadora de Honorários Contábeis");
		lblBanner.setForeground(new Color(255, 255, 255));
		toolBar.add(lblBanner);

		JLabel lblSecaoDados = new JLabel("Dados da Empresa");
		lblSecaoDados.setForeground(new Color(0, 64, 128));
		lblSecaoDados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSecaoDados.setBounds(10, 33, 133, 14);
		contentPane.add(lblSecaoDados);

		// ---------- Coluna da esquerda ----------

		JLabel lblRazaoSocial = new JLabel("Razão Social:");
		lblRazaoSocial.setBounds(20, 58, 76, 14);
		contentPane.add(lblRazaoSocial);

		razaoSocial = new JTextField();
		razaoSocial.setBounds(148, 55, 190, 20);
		contentPane.add(razaoSocial);
		razaoSocial.setColumns(10);

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

		selecaoRegime = new JComboBox<>();
		selecaoRegime.addItem("Simples Nacional");
		selecaoRegime.addItem("Lucro Presumido");
		selecaoRegime.addItem("Lucro Real");
		selecaoRegime.setBounds(148, 107, 133, 22);
		contentPane.add(selecaoRegime);

		JLabel lblRamo = new JLabel("Ramo de Atividade:");
		lblRamo.setBounds(20, 133, 123, 14);
		contentPane.add(lblRamo);

		ramoAtividade = new JComboBox<>();
		ramoAtividade.addItem("Indústria");
		ramoAtividade.addItem("Comércio");
		ramoAtividade.addItem("Serviço");
		ramoAtividade.setBounds(148, 133, 133, 22);
		contentPane.add(ramoAtividade);

		JLabel lblEmiteNf = new JLabel("Emite NF:");
		lblEmiteNf.setBounds(20, 161, 76, 14);
		contentPane.add(lblEmiteNf);

		rbEmiteNfSim = new JRadioButton("Sim");
		rbEmiteNfSim.setBounds(146, 157, 47, 23);
		contentPane.add(rbEmiteNfSim);

		rbEmiteNfNao = new JRadioButton("Não");
		rbEmiteNfNao.setBounds(203, 157, 47, 23);
		contentPane.add(rbEmiteNfNao);

		// Torna Sim/Não mutuamente exclusivos.
		ButtonGroup grupoEmiteNf = new ButtonGroup();
		grupoEmiteNf.add(rbEmiteNfSim);
		grupoEmiteNf.add(rbEmiteNfNao);
		rbEmiteNfNao.setSelected(true); // valor padrão

		// ---------- Coluna da direita ----------

		JLabel lblFuncionarios = new JLabel("Funcionários:");
		lblFuncionarios.setBounds(393, 55, 76, 14);
		contentPane.add(lblFuncionarios);

		campoFuncionarios = new JFormattedTextField(criarFormatterInteiro());
		campoFuncionarios.setValue(0);
		campoFuncionarios.setBounds(589, 49, 63, 20);
		contentPane.add(campoFuncionarios);

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
		rbProLaboreNao.setSelected(true); // valor padrão

		JLabel lblSocios = new JLabel("Sócios:");
		lblSocios.setBounds(393, 108, 76, 14);
		contentPane.add(lblSocios);

		campoSocios = new JFormattedTextField(criarFormatterInteiro());
		campoSocios.setValue(0);
		campoSocios.setBounds(589, 105, 63, 20);
		contentPane.add(campoSocios);

		JLabel lblNfMensal = new JLabel("Quantidade de NF/mês:");
		lblNfMensal.setBounds(393, 133, 133, 14);
		contentPane.add(lblNfMensal);

		nfMensal = new JFormattedTextField(criarFormatterInteiro());
		nfMensal.setValue(0);
		nfMensal.setBounds(589, 130, 123, 20);
		contentPane.add(nfMensal);

		JLabel lblFaturamento = new JLabel("Faturamento Mensal (R$):");
		lblFaturamento.setBounds(393, 161, 147, 14);
		contentPane.add(lblFaturamento);

		faturamentoMensal = new JFormattedTextField(criarFormatterDecimal());
		faturamentoMensal.setValue(0.0);
		faturamentoMensal.setBounds(589, 158, 123, 20);
		contentPane.add(faturamentoMensal);
	}

	/**
	 * Formatter para valores inteiros não negativos (funcionários, sócios, NF).
	 */
	private NumberFormatter criarFormatterInteiro() {
		NumberFormat formato = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(formato);
		formatter.setValueClass(Integer.class);
		formatter.setAllowsInvalid(false);
		formatter.setMinimum(0);
		return formatter;
	}

	/**
	 * Formatter para valores monetários (2 casas decimais, padrão pt-BR).
	 */
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