package SA4;

public class Produto extends Venda {
	//Atributos
	private int id_produto;
	private String nome;
	private double preco;
	private int quantidadeEstoque;

	//Construtores
	public Produto() {
		super();
	}
	public Produto(int id_produto, String nome, double preco, int quantidadeEstoque) {
		super();
		this.id_produto = id_produto;
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	//Getters & Setters
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
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
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	/*MÉTODOS*/
	public boolean verificarEstoque(Produto prod){
		
		if(quantidadeEstoque > 0) {
			System.out.println(getNome()+" possui estoque!");
			return true;
		}
		else{
			System.out.println(getNome()+" não possui estoque!");
			return false;
		}
	}
	
	public void cadastrarProduto(String n, double p, int qe){
        setNome(n);
        setPreco(p);
        setQuantidadeEstoque(qe);
    }
}
