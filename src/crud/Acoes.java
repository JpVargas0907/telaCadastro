package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import conexao.Conexao;

public class Acoes {
	private int id;
    private String nomeCliente;
    private String nomeProjeto;
    private String dataInicio;
    private String dataFinal;
    private String preco;
    private String nomeGerente;
    private String email;
    private String telefone;
    
    public Acoes(int i) {
    	this.id = id;
    }
    public Acoes(String nc, String np, String di, String df, String pc, String ng, String em, String tel) {
    	this.nomeCliente = nc;
    	this.nomeProjeto = np;
    	this.dataInicio = di;
    	this.dataFinal = df;
    	this.preco = pc;
    	this.nomeGerente = ng;
    	this.email = em;
    	this.telefone = tel;
    }
    public Acoes(int id, String nc, String np, String di, String df, String pc, String ng, String em, String tel) {
    	this.id = id;
    	this.nomeCliente = nc;
    	this.nomeProjeto = np;
    	this.dataInicio = di;
    	this.dataFinal = df;
    	this.preco = pc;
    	this.nomeGerente = ng;
    	this.email = em;
    	this.telefone = tel;
    }
    
    public void salvar() {
    	try {		
			Connection con = Conexao.faz_conexao();
			String sql = "insert into dados_cadastro(nomeCliente, nomeProjeto, dataInicio, dataFinal, precoVenda, nomeGerente, Email, telefone) value(?,?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
		
			stmt.setString(1, nomeCliente);
			stmt.setString(2, nomeProjeto);
			stmt.setString(3, dataInicio);
			stmt.setString(4, dataFinal);
			stmt.setString(5, preco);
			stmt.setString(6, nomeGerente);
			stmt.setString(7, email);
			stmt.setString(8, telefone);
		
			stmt.execute();
			stmt.close();
			con.close();
		
			JOptionPane.showMessageDialog(null, "Projeto cadastrado com sucesso.");

			}catch(SQLException e) {
				e.printStackTrace();
			}
    }
    
    public void alterar() {
		try {
			Connection con = Conexao.faz_conexao();
			String sql = "update dados_cadastro set nomeCliente = ?, nomeProjeto = ?, dataInicio = ?, dataFinal = ?,"
				+ "	precoVenda = ?, nomeGerente = ?, email = ?, telefone = ? where id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
		
			stmt.setString(1, nomeCliente);
			stmt.setString(2, nomeProjeto);
			stmt.setString(3, dataInicio);
			stmt.setString(4, dataFinal);
			stmt.setString(5, preco);
			stmt.setString(6, nomeGerente);
			stmt.setString(7, email);
			stmt.setString(8, telefone);
			stmt.setLong(9, id);
		
			stmt.execute();
			stmt.close();
			con.close();
		
			JOptionPane.showMessageDialog(null, "Dados do projeto atualizados com sucesso.");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void excluir() {
    	try {
			Connection con = Conexao.faz_conexao();
			String sql = "delete from dados_cadastro where id = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
		
			stmt.setLong(1, id);
	
			stmt.execute();
			stmt.close();
			con.close();
	
			JOptionPane.showMessageDialog(null, "Dados do projeto excluido com sucesso.");			
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
}
