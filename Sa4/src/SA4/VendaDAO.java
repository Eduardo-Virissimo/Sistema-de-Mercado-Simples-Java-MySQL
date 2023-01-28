package SA4;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class VendaDAO {
	// Criarmos o CRUD referente à entidade Venda
	
	//ID VENDA
		private int id_venda;
		
		public int getId_venda() {
			return id_venda;
		}

		public void setId_venda(int id_venda) {
			this.id_venda = id_venda;
		}
		
	/////////////////////////////////////////////////	
		
		public void create(Produto produto) 
		{
			Connection conexao = ConexaoMySQL.iniciarConexao(); // Abrir conexão com BD
			PreparedStatement stmt = null; // Cria um Statement
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Digite o id do produto a ser comprado:");
			int id = scan.nextInt();		
			System.out.println("Digite a quantidade do produto a ser comprado:");
			int quantidade = scan.nextInt();
			
		try {
			stmt = conexao.prepareStatement("INSERT INTO venda (id_venda, nome, preco, quantidadeProdutos, vlrTotal)" 
											+ " SELECT ?, nome, preco, ?, SUM(preco*?)" 
											+ " FROM produto WHERE id_produto = ?");
			stmt.setInt(1, id);
			stmt.setInt(2, quantidade);
			stmt.setInt(3, quantidade);
			stmt.setInt(4, id);
			stmt.executeUpdate(); // Executar o SQL no BD
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}
	}
		
		public ArrayList<Venda> lerCarrinho()
		{
			Connection connection = ConexaoMySQL.iniciarConexao();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Venda> carrinho = new ArrayList<>();
	
			try {
					stmt = connection.prepareStatement("SELECT * FROM venda");
					rs = stmt.executeQuery(); 
					while(rs.next())
					{
						Venda venda = new Produto();
						venda.setId_venda(rs.getInt("id_venda"));
						venda.setNome(rs.getString("nome"));
						venda.setPreco(rs.getDouble("preco"));
						venda.setQuantidadeProdutos(rs.getInt("quantidadeProdutos"));
						venda.setVlrTotal(rs.getDouble("vlrTotal"));
						adicionarItemVenda(carrinho, venda);     
						
					}
			} catch (SQLException erro) {
				erro.printStackTrace();
			} finally {
				ConexaoMySQL.encerrarConexao(connection, stmt, rs);
			}
			return carrinho;
		}
		
	
		public void update(Venda venda) {
			Connection conexao = ConexaoMySQL.iniciarConexao(); // Abrir conexão com BD
			PreparedStatement stmt = null; // Cria um Statement
			try {
				stmt = conexao.prepareStatement("UPDATE produto SET nome = ?, preco = ?, quantidadeProdutos = ?, vlrTotal = ? WHERE id_venda = ?");
				stmt.setString(1, venda.getNome());
				stmt.setDouble(2, venda.getPreco());
				stmt.setInt(3, venda.getQuantidadeProdutos());
				stmt.setDouble(4, venda.getVlrTotal());
				stmt.setInt(5, venda.getId_venda());
				stmt.executeUpdate(); // Executar o SQL no BD
			} catch (SQLException erro) {
				erro.printStackTrace();
			} finally {
				ConexaoMySQL.encerrarConexao(conexao, stmt);
			}
		}
		
		public void delete(Venda venda) {
			Connection conexao = ConexaoMySQL.iniciarConexao(); // Abrir conexão com BD
			PreparedStatement stmt = null; // Cria um Statement
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Digite o produto que deseja remover:");
			int id = scan.nextInt();
			
			try {
				stmt = conexao.prepareStatement("DELETE FROM venda WHERE id_venda = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate(); // Executar o SQL no BD
			} catch (SQLException erro) {
				erro.printStackTrace();
			} finally {
				ConexaoMySQL.encerrarConexao(conexao, stmt);
			}
		}
		
		public void mostrarCarrinho(ArrayList<Venda> carrinho) 
		{
			carrinho.forEach(prod -> {
				System.out.println("Id: "+ prod.getId_venda() +
								   " | Nome: " + prod.getNome() + 
								   " | Preço: " + prod.getPreco() +
								   " | Quantidade Produtos: " + prod.getQuantidadeProdutos());
			});
	    }
		
		public double vlrTotalCompras(ArrayList<Venda> carrinho) 
		{
			double valor = 0.0;
			    for (Venda venda : carrinho) {
			        valor += venda.getVlrTotal();
			    }
			return valor;
		}
	    
		public void adicionarItemVenda(ArrayList<Venda> carrinho, Venda venda) {
			carrinho.add(venda);
	}
}
