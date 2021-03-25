package main.java;

import java.time.LocalDate;
import java.util.Date;

public class CartaoCreditoNegocio {

	private CartaoCreditoRepositorio cartRepo;
	
	public CartaoCreditoNegocio(CartaoCreditoRepositorio caRepo) {
		this.cartRepo = caRepo;
	}
	
	public boolean adicionarCartaoCredito(CartaoCredito c) {
		boolean adicionado = false;
//		Date dataAtualSistema = new Date(System.currentTimeMillis());
//		System.out.println(dataAtualSistema);
		int ano = LocalDate.now().getYear();
		int mes = LocalDate.now().getMonthValue();
//		System.out.print(ano +" " + mes + " ");
		
		
		if (   c.getNumero() != null
			&& c.getNumero().length() == 16
			&& c.getNumero().matches("[0-9]+")
			&& c.getBandeira() != null	
			&& 	this.cartRepo.buscarPorNumero(c.getNumero()) == null
				){
				if(        c.getBandeira().contains("VISA")
						|| c.getBandeira().contains("MASTERCARD")
						|| c.getBandeira().contains("ELO")
						|| c.getBandeira().contains("HIPERCARD")
						|| c.getBandeira().contains("AMERICAN EXPRESS")
					){
					if(   c.getDataExpiracaoAno() > ano) {
					        adicionado = this.cartRepo.addCartaoCredito(c);
				
					}else {
						if(c.getDataExpiracaoAno() == ano
								&& c.getDataExpiracaoMes() >= mes) {
							adicionado = this.cartRepo.addCartaoCredito(c);
						}
					}					
				}					
		}			
		return adicionado;
	}
	public boolean deletarCartaoCredito(String numero) {
		boolean ret = false;
		if (numero != null) {
			ret = this.cartRepo.delCartaoCredito(numero);
		}
		return ret;		
	}
}

