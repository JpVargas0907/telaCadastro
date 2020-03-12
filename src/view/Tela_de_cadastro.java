package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import conexao.Conexao;
import crud.Acoes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Tela_de_cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeCliente;
	private JTextField tfNomeProjeto;
	private JTextField tfPreco;
	private JTextField tfNomeGerente;
	private JTextField tfEmail;
	private JFormattedTextField tfDataInicio;
	private JFormattedTextField tfDataFinal;
	private JFormattedTextField tfTelefone;
	private JTextField tfBusca;
	private JTextField tfIdProjeto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_cadastro frame = new Tela_de_cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Tela_de_cadastro() throws ParseException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeProjetos = new JLabel("CADASTRO DE PROJETOS ");
		lblCadastroDeProjetos.setFont(new Font("Arial", Font.BOLD, 18));
		lblCadastroDeProjetos.setBounds(203, 13, 257, 16);
		contentPane.add(lblCadastroDeProjetos);
		
		JLabel lblNomeDoCliente = new JLabel("Nome do cliente:");
		lblNomeDoCliente.setBounds(12, 51, 107, 16);
		contentPane.add(lblNomeDoCliente);
		
		JLabel lblNomeDoProjeto = new JLabel("Nome do projeto:");
		lblNomeDoProjeto.setBounds(12, 80, 107, 16);
		contentPane.add(lblNomeDoProjeto);
		
		JLabel DataDeInicio = new JLabel("Data de in\u00EDcio:");
		DataDeInicio.setBounds(12, 109, 107, 16);
		contentPane.add(DataDeInicio);
		
		JLabel DataDeTermino = new JLabel("Data de t\u00E9rmino: ");
		DataDeTermino.setBounds(12, 138, 107, 16);
		contentPane.add(DataDeTermino);
		
		JLabel PrecoDeVenda = new JLabel("Pre\u00E7o de venda:");
		PrecoDeVenda.setBounds(12, 167, 107, 16);
		contentPane.add(PrecoDeVenda);
		
		JLabel NomeDoGerente = new JLabel("Nome do gerente: ");
		NomeDoGerente.setBounds(12, 196, 119, 16);
		contentPane.add(NomeDoGerente);
		
		JLabel Telefone = new JLabel("Telefone:");
		Telefone.setBounds(12, 254, 107, 16);
		contentPane.add(Telefone);
		
		JLabel Email = new JLabel("Email:");
		Email.setBounds(12, 225, 107, 16);
		contentPane.add(Email);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setBounds(118, 48, 223, 22);
		contentPane.add(tfNomeCliente);
		tfNomeCliente.setColumns(10);
		
		tfNomeProjeto = new JTextField();
		tfNomeProjeto.setColumns(10);
		tfNomeProjeto.setBounds(118, 77, 223, 22);
		contentPane.add(tfNomeProjeto);

		
		
		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBounds(118, 164, 116, 22);
		contentPane.add(tfPreco);
		
		tfNomeGerente = new JTextField();
		tfNomeGerente.setColumns(10);
		tfNomeGerente.setBounds(118, 193, 223, 22);
		contentPane.add(tfNomeGerente);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(118, 222, 223, 22);
		contentPane.add(tfEmail);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 328, 600, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDigiteOId = new JLabel("Digite o ID do projeto:");
		lblDigiteOId.setBounds(12, 13, 139, 24);
		panel.add(lblDigiteOId);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(143, 14, 59, 22);
		panel.add(tfBusca);
		tfBusca.setColumns(10);
		
		JButton btnAbrir = new JButton("ABRIR");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfBusca.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Por favor informe o ID do projeto");
				}
				else {
					
				try {	
					Connection con = Conexao.faz_conexao();
					String sql = "select *from dados_cadastro where id = ?";
						
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfBusca.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						tfIdProjeto.setText(rs.getString("id"));
						tfNomeCliente.setText(rs.getString("nomeCliente"));
						tfNomeProjeto.setText(rs.getString("nomeProjeto"));
						tfDataInicio.setText(rs.getString("dataInicio"));
						
						tfDataFinal.setText(rs.getString("dataFinal"));
						tfPreco.setText(rs.getString("precoVenda"));
						tfNomeGerente.setText(rs.getString("nomeGerente"));
						tfEmail.setText(rs.getString("email"));
						tfTelefone.setText(rs.getString("telefone"));
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
					
					
				}
			}
		});
		btnAbrir.setBounds(214, 13, 97, 25);
		panel.add(btnAbrir);
		
		tfDataInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		tfDataInicio.setBounds(118, 106, 116, 22);
		contentPane.add(tfDataInicio);
		tfDataInicio.setColumns(10);
		
		tfDataFinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		tfDataFinal.setColumns(10);
		tfDataFinal.setBounds(118, 135, 116, 22);
		contentPane.add(tfDataFinal);
		
		tfTelefone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(118, 251, 116, 22);
		contentPane.add(tfTelefone);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfIdProjeto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor informe o ID do projeto a ser excluído.");
				}else {
					
					Acoes ac = new Acoes(Integer.parseInt(tfIdProjeto.getText()));
					ac.excluir();
					
					tfNomeCliente.setText("");
					tfNomeProjeto.setText("");
					tfDataInicio.setText("");
					tfDataFinal.setText("");
					tfPreco.setText("");
					tfNomeGerente.setText("");
					tfEmail.setText("");
					tfTelefone.setText("");
					tfIdProjeto.setText("");
						
				}
			}	
		});
		btnExcluir.setBounds(515, 290, 97, 25);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("ATUALIZAR");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tfIdProjeto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do projeto a ser alterado.");
				
				}else if ((tfNomeCliente.getText().isEmpty()) || (tfNomeProjeto.getText().isEmpty()) || (tfDataInicio.getText().isEmpty()) || (tfDataFinal.getText().isEmpty()) ||
				   		(tfPreco.getText().isEmpty()) || (tfNomeGerente.getText().isEmpty()) || (tfTelefone.getText().isEmpty()) || (tfEmail.getText().isEmpty())) {
					
						JOptionPane.showMessageDialog(null, "Por favor preencher todos os campos para atualizar o cadastro.");
					
				} else if (!tfEmail.getText().contains("@")) {

			        JOptionPane.showMessageDialog(null, "Por favor informar um e-mail válido.");	
				
				}else {
					
					Acoes ac = new Acoes(Integer.parseInt(tfIdProjeto.getText()), tfNomeCliente.getText(), tfNomeProjeto.getText(), tfDataInicio.getText(), tfDataFinal.getText(),
										 tfPreco.getText(), tfNomeGerente.getText(),	tfEmail.getText(), tfTelefone.getText());
					ac.alterar();
					
					
					tfNomeCliente.setText("");
					tfNomeProjeto.setText("");
					tfDataInicio.setText("");
					tfDataFinal.setText("");
					tfPreco.setText("");
					tfNomeGerente.setText("");
					tfEmail.setText("");
					tfTelefone.setText("");
					tfIdProjeto.setText("");
					
					}	
			}
		});
		btnAlterar.setBounds(396, 290, 107, 25);
		contentPane.add(btnAlterar);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if ((tfNomeCliente.getText().isEmpty()) || (tfNomeProjeto.getText().isEmpty()) || (tfDataInicio.getText().isEmpty()) || (tfDataFinal.getText().isEmpty()) ||
				(tfPreco.getText().isEmpty()) || (tfNomeGerente.getText().isEmpty()) || (tfTelefone.getText().isEmpty()) || (tfEmail.getText().isEmpty())) {
			
				JOptionPane.showMessageDialog(null, "Por favor preencher todos os campos para efetuar o cadastro.");
				
			} else if (!tfEmail.getText().contains("@")) {

		        JOptionPane.showMessageDialog(null, "Por favor informar um e-mail válido.");
		        
			} else if (!tfIdProjeto.getText().contains("id")) {
				
		        JOptionPane.showMessageDialog(null, "O projeto já foi cadastrado.");
				
			}else{
				Acoes ac = new Acoes(tfNomeCliente.getText(), tfNomeProjeto.getText(), tfDataInicio.getText(), tfDataFinal.getText(),
									 tfPreco.getText(), tfNomeGerente.getText(),	tfEmail.getText(), tfTelefone.getText());
				ac.salvar();
				
				tfNomeCliente.setText("");
				tfNomeProjeto.setText("");
				tfDataInicio.setText("");
				tfDataFinal.setText("");
				tfPreco.setText("");
				tfNomeGerente.setText("");
				tfEmail.setText("");
				tfTelefone.setText("");
				}
			}
		});
		btnCadastrar.setBounds(277, 290, 107, 25);
		contentPane.add(btnCadastrar);
		
		tfIdProjeto = new JTextField();
		tfIdProjeto.setEditable(false);
		tfIdProjeto.setBounds(557, 48, 55, 22);
		contentPane.add(tfIdProjeto);
		tfIdProjeto.setColumns(10);
		
		JLabel lblIdProjeto = new JLabel("ID Projeto:");
		lblIdProjeto.setBounds(470, 51, 74, 16);
		contentPane.add(lblIdProjeto);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Selecione uma das op\u00E7\u00F5es>", "Carlos de Nobrega", "Silvio Santos ", "Charles Xavier"}));
		comboBox.setBounds(346, 193, 223, 22);
		contentPane.add(comboBox);
		
		
//		
		
	}
}