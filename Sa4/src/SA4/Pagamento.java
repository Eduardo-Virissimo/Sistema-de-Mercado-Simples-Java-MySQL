package SA4;
import java.util.*;

public class Pagamento extends Venda {
	//Atributos
	int id_pagamento;
	int tipoPagamento; /*1 = dinheiro, 2 = cheque e 3 = cartão*/
	Scanner lerOpcoes = new Scanner(System.in);
	Venda concluirVenda;
	
	public void selecionarTipoPagamento(ArrayList <Pagamento> listapagamentos, int tpag) {			
		
		System.out.println("-----------------------------"
						+"\n| Qual método de pagamento? |:\n"
						+"-----------------------------");
		
		tipoPagamento = lerOpcoes.nextInt();
		
		listapagamentos.forEach(prod -> {
			if(tipoPagamento == prod.getTipoPagamento()) {
				System.out.println("Método "+prod.getTipoPagamento()+ ": pagamento validado.");
			}else { System.out.println("Método "+prod.getTipoPagamento()+ ": pagamento invalidado."); }
		});
	}
	
	public void realizarPagamento(Pagamento pag) {
		System.out.println("\nO pagamento foi concluído! método utilizado |"+tipoPagamento+"|");
	}
	
	public void visualizarPagamento(ArrayList <Pagamento> listapagamentos) {
		
		System.out.println("Pagamentos padrões do sistema: dinheiro [1] cheque [2] cartão [3]");
		listapagamentos.forEach(prod -> {
			System.out.println("Pagamento cadastrado: " + prod.getTipoPagamento());
		});
    }
	
	public int getId_pagamento() {
		return id_pagamento;
	}

	public void setId_pagamento(int id_pagamento) {
		this.id_pagamento = id_pagamento;
	}
	
	public int getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(int tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public void cadastrarPagamento(int t){
        setTipoPagamento(t);
    }
}
