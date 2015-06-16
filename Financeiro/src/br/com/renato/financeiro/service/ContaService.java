package br.com.renato.financeiro.service;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import br.com.renato.financeiro.dao.ContaDAO;
import br.com.renato.financeiro.entidade.Conta;
import br.com.renato.financeiro.exception.ServiceException;

public class ContaService {
	
	private ContaDAO contaDAO;
	
	public ContaService(Context context) {
		this.contaDAO = new ContaDAO(context);
	}
	
	public List<Conta> getContasPagar() throws ServiceException{
		try{
			return contaDAO.getContasPagar();
		}catch(Exception e){
			throw new ServiceException("Erro ao recuperar as contas a pagar", e);
		}
	}
	
	public void cadastrarGasto(String nome, String descricao, String valorVencimento, Integer tipoPessoa) throws ServiceException{
		try{
			Calendar hoje = Calendar.getInstance();
			Conta conta = new Conta();
			conta.setNome(nome);
			conta.setDescricao(descricao);
			conta.setValorVencimento(valorVencimento);
			conta.setTipoPessoa(tipoPessoa);
			conta.setDiaVencimento(hoje.get(Calendar.DATE));
			conta.setMesVencimento(hoje.get(Calendar.MONTH));
			conta.setAnoVencimento(hoje.get(Calendar.YEAR));
			conta.setTipoConta(Conta.TIPO_CONTA_GASTO);
			
			contaDAO.addConta(conta);
		}catch(Exception e){
			throw new ServiceException("Erro ao cadastrar um gasto", e);
		}
	}
	
	public List<Conta> getContasAmanha() throws ServiceException{
		try{
			List<Conta> contas = contaDAO.getProximasDesteMes();
			if(contas!=null&&contas.size()==5){
				return contas;
			}else if(contas!=null&&contas.size()<5){
				List<Conta> proxs = contaDAO.getProximasProximoMes();
				if(proxs!=null){
					for(Conta c : proxs){
						if(contas.size()<5){
							contas.add(c);
						}
					}
				}
			}
			return contas;
		}catch(Exception e){
			throw new ServiceException("Erro ao recuperar as contas de amanha", e);
		}
	}
	
	public List<Conta> getContasHoje() throws ServiceException{
		try{
			return contaDAO.getHoje();
		}catch(Exception e){
			throw new ServiceException("Erro ao recuperar as contas de hoje", e);
		}
	}
	
	public List<Conta> getContasOntem() throws ServiceException{
		try{
			List<Conta> contas = contaDAO.getPassadasDesteMes();
			if(contas!=null&&contas.size()==5){
				return contas;
			}else if(contas!=null&&contas.size()<5){
				List<Conta> antes = contaDAO.getPassadasMesPassado();
				if(antes!=null){
					for(Conta c : antes){
						if(contas.size()<5){
							contas.add(c);
						}
					}
				}
			}
			return contas;
		}catch(Exception e){
			throw new ServiceException("Erro ao recuperar as contas de ontem", e);
		}
	}
	
	public boolean cadastrarContaProgramada(String nome,
											String descricao,
											String valorVencimento,
											Integer diaVencimento,
											Integer qtdeParcelas,
											boolean fisica,
											boolean parcelada) throws ServiceException{
		try{
			Integer mesAtual = Calendar.getInstance().get(Calendar.MONTH);
			Integer diaAtual = Calendar.getInstance().get(Calendar.DATE);
			Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
			if(parcelada){
				for(int i=0;i<qtdeParcelas;i++){
					Conta conta = new Conta();
					
					//Verificar se eh fisica ou juridica
					if(fisica){
						conta.setTipoPessoa(Conta.TIPO_PESSOA_FISICA);
					}else{
						conta.setTipoPessoa(Conta.TIPO_PESSOA_JURIDICA);
					}
					
					//Verificar se a primeira para o mes atual
					if(diaVencimento>=diaAtual){
						//Comeca neste mes
						conta.setDiaVencimento(diaVencimento);
						conta.setMesVencimento(mesAtual);
						conta.setAnoVencimento(anoAtual);
					}else{
						//Comeca no proximo mes
						if(mesAtual==12){
							mesAtual=1;
							anoAtual++;
						}else{
							mesAtual++;
						}
						conta.setDiaVencimento(diaVencimento);
						conta.setMesVencimento(mesAtual);
						conta.setAnoVencimento(anoAtual);
					}
					
					conta.setNumParcelas(i+1);
					conta.setNumTotalParcelas(qtdeParcelas);
					conta.setNome(nome);
					conta.setDescricao(descricao);
					conta.setValorVencimento(valorVencimento);
					conta.setTipoConta(Conta.TIPO_CONTA_PARCELADA);
					
					contaDAO.addConta(conta);
				}
			}else{
				qtdeParcelas=12;
				for(int i=0;i<qtdeParcelas;i++){
					Conta conta = new Conta();
					
					//Verificar se eh fisica ou juridica
					if(fisica){
						conta.setTipoPessoa(Conta.TIPO_PESSOA_FISICA);
					}else{
						conta.setTipoPessoa(Conta.TIPO_PESSOA_JURIDICA);
					}
					
					//Comeca neste mes
					conta.setDiaVencimento(diaVencimento);
					conta.setMesVencimento(mesAtual);
					conta.setAnoVencimento(anoAtual);
					
					if(mesAtual==12){
						mesAtual=1;
						anoAtual++;
					}else{
						mesAtual++;
					}
					
					conta.setNumParcelas(i+1);
					conta.setNumTotalParcelas(qtdeParcelas);
					conta.setNome(nome);
					conta.setDescricao(descricao);
					conta.setValorVencimento(valorVencimento);
					conta.setTipoConta(Conta.TIPO_CONTA_PROGRAMADA);
					
					contaDAO.addConta(conta);
				}
			}
			return true;
		}catch(Exception e){
			throw new ServiceException("Erro ao tentar cadastrar conta", e);
		}
	}

}
