package SA4;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Venda {
	//Atributos
	private int id_venda;
	private String nome;
	private double preco;
	private int quantidadeProdutos;
	private double vlrTotal;
    private ArrayList<Produto> listaprodutos = new ArrayList<>();

	
	//Construtores
	public Venda() {
		super();
	}
	
	public Venda(int id_venda, String nome, double preco, int quantidadeProdutos, double vlrTotal) {
		super();
		this.id_venda = id_venda;
		this.nome = nome;
		this.preco = preco;
		this.quantidadeProdutos = quantidadeProdutos;
		this.vlrTotal = vlrTotal;
	}
	
	//Getters & Setters
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}
	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}
	public double getVlrTotal() {
		return vlrTotal;
	}
	public void setVlrTotal(double vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	
	/*MÉTODOS*/
	public void concluirVenda(Pagamento pag) {
		System.out.println("Venda concluída!");	
	}
	
	public void visualizarVenda(ArrayList <Produto> listaprodutos) {
		listaprodutos.forEach(prod -> {
			System.out.println("Id: "+ prod.getId_produto() +
							   " | Nome: " + prod.getNome() + 
							   " | Preço: " + prod.getPreco() +
							   " | Quantidade Estoque: " + prod.getQuantidadeEstoque());
		});
    }
}