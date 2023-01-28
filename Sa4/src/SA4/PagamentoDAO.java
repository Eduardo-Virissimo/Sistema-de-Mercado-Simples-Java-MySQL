package SA4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PagamentoDAO {
	// Criarmos o CRUD referente à entidade Pagamento

		public void create(Pagamento pag) {
			Connection conexao = ConexaoMySQL.iniciarConexao(); // Abrir conexão com BD
			PreparedStatement stmt = null; // Cria um Statement
			
			/*Scanner scan = new Scanner(System.in);
			System.out.println("Digite o nome do pagamento que deseja adicionar:");
				String tipoPagamento = scan.next();*/
			
			try {
				stmt = conexao.prepareStatement("INSERT INTO pagamento (tipoPagamento) VALUES (?)");
				stmt.setInt(1, pag.getTipoPagamento());
				stmt.executeUpdate(); // Executar o SQL no BD
			} catch (SQLException erro) {
				erro.printStackTrace();
			} finally {
				ConexaoMySQL.encerrarConexao(conexao, stmt);
			}
		}
		
		public ArrayList<Pagamento> read(){
			Connection conexao = ConexaoMySQL.iniciarConexao(); // Abrir conexão com BD
			PreparedStatement stmt = null; // Cria um Statement
			ResultSet rs = null; //Cria um ResultSet
			ArrayList<Pagamento> tipoPagamento = new ArrayList<>(); //Cria uma ArrayList que vai armazenar o retorno do ResultSet
			
			try {
				stmt = conexao.prepareStatement("SELECT * FROM pagamento"); //SQL
				rs = stmt.executeQuery(); //Armazenar o retorno do SQL dentro do ResultSet
				while (rs.next()) {
					Pagamento pagamento = new Pagamento(); //Cria o objeto que irá compor o ArrayList
					pagamento.setId_pagamento(rs.getInt("id_pagamento"));
					pagamento.setTipoPagamento(rs.getInt("tipoPagamento"));
					tipoPagamento.add(pagamento);
				}
			} catch (SQLException erro){
				erro.printStackTrace();
			} finally {
				ConexaoMySQL.encerrarConexao(conexao, stmt, rs);
			}
			return tipoPagamento;
		}
		
		
		public void update(Pagamento pag) {
			Connection conexao = ConexaoMySQL.iniciarConexao(); // Abrir conexão com BD
			PreparedStatement stmt = null; // Cria um Statement
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Digite o id do pagamento que deseja alterar:");
			int id = scan.nextInt();
			System.out.println("Digite o nome do pagamento que deseja alterar:");
			String tipoPagamento = scan.next();
			
			try {
				stmt = conexao.prepareStatement("UPDATE pagamento SET tipoPagamento = ? WHERE id_pagamento = ?");
				stmt.setString(1, tipoPagamento);
				stmt.setInt(2, id);
				stmt.executeUpdate(); // Executar o SQL no BD
			} catch (SQLException erro) {
				erro.printStackTrace();
			} finally {
				ConexaoMySQL.encerrarConexao(conexao, stmt);
			}
		}
		
		public void delete(Pagamento pag) {
			Connection conexao = ConexaoMySQL.iniciarConexao(); // Abrir conexão com BD
			PreparedStatement stmt = null; // Cria um Statement
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Digite o id do pagamento que deseja excluir:");
			int id = scan.nextInt();
			
			try {
				stmt = conexao.prepareStatement("DELETE FROM pagamento WHERE id_pagamento = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate(); // Executar o SQL no BD
			} catch (SQLException erro) {
				erro.printStackTrace();
			} finally {
				ConexaoMySQL.encerrarConexao(conexao, stmt);
			}
		}
}
