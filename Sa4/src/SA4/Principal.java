package SA4;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Principal {
	
	public static void main(String[] args) {
		
		Venda venda = new Venda(); //VENDA
		Pagamento pag = new Pagamento(); //PAGAMENTO
		ProdutoDAO produtoDAO = new ProdutoDAO(); //PRODUTO DO BANCO DE DADOS
		VendaDAO vendaDAO = new VendaDAO(); //VENDA DO BANCO DE DADOS
		PagamentoDAO pagamentoDAO = new PagamentoDAO(); //PAGAMENTO DO BANCO DE DADOS
		ArrayList<Produto> listaprodutos = new ArrayList<>(); //LISTA DE PRODUTOS
		ArrayList<Venda> carrinho = new ArrayList<>(); //LISTA DO CARRINHO
		ArrayList<Pagamento> listapagamentos = new ArrayList<>(); //LISTA DE PAGAMENTOS
		
		// LISTA DE PRODUTOS ATUAL //
		Produto produto1 = new Produto();
		produto1.setId_produto(1);
		produto1.setNome("Banana");
		produto1.setPreco(10);
		produto1.setQuantidadeEstoque(30);
		produtoDAO.create(produto1);
		
		Produto produto2 = new Produto();
		produto2.setId_produto(2);
		produto2.setNome("Leite");
		produto2.setPreco(3);
		produto2.setQuantidadeEstoque(50);
		produtoDAO.create(produto2);
		
		Produto produto3 = new Produto();
		produto3.setId_produto(3);
		produto3.setNome("Carne");
		produto3.setPreco(15);
		produto3.setQuantidadeEstoque(25);
		produtoDAO.create(produto3);
		
		Produto produto4 = new Produto();
		produto4.setId_produto(4);
		produto4.setNome("Feijão");
		produto4.setPreco(8);
		produto4.setQuantidadeEstoque(20);
		produtoDAO.create(produto4);
		
		Produto produto5 = new Produto();
		produto5.setId_produto(5);
		produto5.setNome("Cenoura");
		produto5.setPreco(7);
		produto5.setQuantidadeEstoque(18);
		produtoDAO.create(produto5);
		/////////////////////////////////////
		
		// LISTA DE PAGAMENTOS ATUAL //
		Pagamento pagamentoDinheiro = new Pagamento();
		pagamentoDinheiro.setId_pagamento(1);
		pagamentoDinheiro.setTipoPagamento(1);
		pagamentoDAO.create(pagamentoDinheiro);
		
		Pagamento pagamentoCheque = new Pagamento();
		pagamentoCheque.setId_pagamento(2);
		pagamentoCheque.setTipoPagamento(2);
		pagamentoDAO.create(pagamentoCheque);
		
		Pagamento pagamentoCartao = new Pagamento();
		pagamentoCartao.setId_pagamento(3);
		pagamentoCartao.setTipoPagamento(3);
		pagamentoDAO.create(pagamentoCartao);
		/////////////////////////////////////
		
		listapagamentos = pagamentoDAO.read(); //PUXA A LISTA DO BANCO
		listaprodutos = produtoDAO.read(); //PUXA A LISTA DO BANCO
		
		Produto produto = new Produto();
		String nome;
		double preco;
		int quantidadeEstoque;
		int id;
		int quantidadeProduto;
		int tipoPagamento;
		double valorTotal;
		
		Scanner scan = new Scanner(System.in);
		int menu = 0;
		
	while(menu != 4) {
		
		System.out.println("		< MENU - MERCADO >\n"
						+ "--------------------------------------------------------\n"
						+ "| 1 - Venda | 2 - Produtos | 3 - Pagamentos | 4 - Sair |\n"
						+ "--------------------------------------------------------");
		menu = scan.nextInt();
		
		switch(menu) {
		
			case 1 -> {     		
				int opcaovenda = 0;
				while(opcaovenda != 4) {
					
				System.out.println(   "					< VENDA - MERCADO >\n"
									+ "----------------------------------------------------------------------------------------------------\n"
									+ "| 1 - Adicionar produto a venda | 2 - Visualizar a venda | 3 - Concluir venda | 4 - Voltar ao menu |\n"
									+ "----------------------------------------------------------------------------------------------------");
				
				opcaovenda = scan.nextInt();
				switch(opcaovenda) {
					case 1 -> {
						listaprodutos = produtoDAO.read();
						venda.visualizarVenda(listaprodutos);
						vendaDAO.create(produto);
						carrinho = vendaDAO.lerCarrinho();
					}
					case 2 -> vendaDAO.mostrarCarrinho(carrinho);
					case 3 -> {
						listapagamentos = pagamentoDAO.read();
						pag.visualizarPagamento(listapagamentos);
						pag.selecionarTipoPagamento(listapagamentos, 0);
						pag.realizarPagamento(pag);
						valorTotal = vendaDAO.vlrTotalCompras(carrinho);
						System.out.println("O valor total da compra foi: " +valorTotal+"\n");
						pag.concluirVenda(pag);
						opcaovenda = 4;
						menu = 4;
					}
					case 4 -> opcaovenda = 4;
					default -> System.out.println("Opção inválida!");
				}
			}
		}
			case 2 -> {
				int opcaoprodutos = 0;
				while(opcaoprodutos != 5) {
				System.out.println(	"					    < PRODUTOS - MERCADO >\n"
									+ "--------------------------------------------------------------------------------------------------------------------\n"
									+ "| 1 - Cadastrar produto | 2 - Alterar produto | 3 - Excluir produto | 4 - Visualizar produtos | 5 - Voltar ao menu |\n"
									+ "--------------------------------------------------------------------------------------------------------------------");
				opcaoprodutos = scan.nextInt();
				switch(opcaoprodutos) {
					case 1 -> {
						listaprodutos = produtoDAO.read();
						System.out.println("Digite o nome produto que deseja adicionar:");
							nome = scan.next();
						System.out.println("Digite o preço do produto que deseja adicionar:");
							preco = scan.nextDouble();
						System.out.println("Digite a quantidade de estoque:");
							quantidadeEstoque = scan.nextInt();
							produto.cadastrarProduto(nome, preco, quantidadeEstoque);
							produtoDAO.create(produto);
							listaprodutos = produtoDAO.read();
					}
					case 2 -> {
						venda.visualizarVenda(listaprodutos);
						produtoDAO.update(produto);
						listaprodutos = produtoDAO.read();
					}
					case 3 -> {
						venda.visualizarVenda(listaprodutos);
						produtoDAO.delete(produto);
						listaprodutos = produtoDAO.read();
					}
					case 4 -> venda.visualizarVenda(listaprodutos);
					case 5 -> opcaoprodutos = 5;
					default -> System.out.println("Opção inválida!");
				}
			}
		}
			case 3 -> {
				int opcaopagamento = 0;
				while(opcaopagamento != 5) {
				System.out.println(   "					       < PAGAMENTO - MERCADO >\n"
									+ "---------------------------------------------------------------------------------------------------------------------------\n"
									+ "| 1 - Cadastrar pagamento | 2 - Alterar pagamento | 3 - Excluir pagamento | 4 - Visualizar pagamento | 5 - Voltar ao menu |\n"
									+ "---------------------------------------------------------------------------------------------------------------------------");
				opcaopagamento = scan.nextInt();
				switch(opcaopagamento) {
					case 1 -> {
						listapagamentos = pagamentoDAO.read();
						System.out.println("Digite o número a qual se refere seu novo pagamento (1, 2 e 3 já existem):");
							tipoPagamento = scan.nextInt();
						pag.cadastrarPagamento(tipoPagamento);
						pagamentoDAO.create(pag);
						listapagamentos = pagamentoDAO.read();
					}
					case 2 -> {
						pagamentoDAO.update(pag);
						listapagamentos = pagamentoDAO.read();
					}
					case 3 -> {
						pagamentoDAO.delete(pag);
						listapagamentos = pagamentoDAO.read();
					}
					case 4 -> {
						pag.visualizarPagamento(listapagamentos);
					}
					case 5 -> opcaopagamento = 5;
					default -> System.out.println("Opção inválida!");
					}
				}
			}
		}
	}
	
	System.out.println("Finalizando programa... Até outra hora =D");

		}
	}		
	