package br.com.renato.financeiro.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.renato.financeiro.entidade.Conta;
import br.com.renato.financeiro.exception.DAOException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContaDAO extends GenericoDAO{

	public ContaDAO(Context context) {
		super(context);
	}
	
	public List<Conta> getContasPagar() throws DAOException{
		try{
			SQLiteDatabase db = this.getReadableDatabase();
			String sql = "("+Conta.KEY_TIPO_CONTA+" = "+Conta.TIPO_CONTA_PARCELADA+" OR ";
			sql += Conta.KEY_TIPO_CONTA+" = "+Conta.TIPO_CONTA_PROGRAMADA+" ) ";
			Cursor cursor = db.query(Conta.TABLE_NAME, Conta.COLUMNS, sql, null,null,null,null);
			if(cursor!=null&&cursor.getCount()>0){
				List<Conta> contas = new ArrayList<Conta>();
				cursor.moveToFirst();
				do{
					Conta conta = new Conta();
					conta.setId(cursor.getInt(0));
					conta.setNome(cursor.getString(1));
					conta.setDescricao(cursor.getString(2));
					conta.setValorPagamento(cursor.getString(3));
					conta.setValorVencimento(cursor.getString(4));
					conta.setDiaPagamento(cursor.getInt(5));
					conta.setMesPagamento(cursor.getInt(6));
					conta.setAnoPagamento(cursor.getInt(7));
					conta.setDiaVencimento(cursor.getInt(8));
					conta.setMesVencimento(cursor.getInt(9));
					conta.setAnoVencimento(cursor.getInt(10));
					conta.setTipoConta(cursor.getInt(11));
					conta.setTipoPessoa(cursor.getInt(12));
					conta.setPago((cursor.getInt(13)==1)?(true):(false));
					conta.setNumParcelas(cursor.getInt(14));
					conta.setNumTotalParcelas(cursor.getInt(15));
					
					if(conta.getDiaPagamento()>0){
						contas.add(conta);
					}
				}while(cursor.moveToNext());
				return contas;
			}
			return null;
		}catch(Exception e){
			throw new DAOException("Erro ao recuperar as contas a pagar", e);
		}
	}
	
	public void addConta(Conta conta) throws DAOException{
		try{
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(Conta.KEY_NOME, conta.getNome());
			values.put(Conta.KEY_DESCRICAO, conta.getDescricao());
			values.put(Conta.KEY_VALOR_PAGAMENTO, conta.getValorPagamento());
			values.put(Conta.KEY_VALOR_VENCIMENTO, conta.getValorVencimento());
			values.put(Conta.KEY_DIA_PAGAMENTO, conta.getDiaPagamento());
			values.put(Conta.KEY_MES_PAGAMENTO, conta.getMesPagamento());
			values.put(Conta.KEY_ANO_PAGAMENTO, conta.getAnoPagamento());
			values.put(Conta.KEY_DIA_VENCIMENTO, conta.getDiaVencimento());
			values.put(Conta.KEY_MES_VENCIMENTO, conta.getMesVencimento());
			values.put(Conta.KEY_ANO_VENCIMENTO, conta.getAnoVencimento());
			values.put(Conta.KEY_TIPO_CONTA, conta.getTipoConta());
			values.put(Conta.KEY_PAGO, conta.getPago());
			
			db.insert(Conta.TABLE_NAME, null, values);
			db.close();
		}catch(Exception e){
			throw new DAOException("Erro ao tentar cadastrar uma conta", e);
		}
	}
	
	public List<Conta> getPassadasMesPassado() throws DAOException{
		try{
			Calendar data = Calendar.getInstance();
			int mes = data.get(Calendar.MONTH);
			int ano = data.get(Calendar.YEAR);
			
			if(mes==1){
				mes=12;
				ano--;
			}else{
				mes--;
			}
			
			String sql = "";
			sql += Conta.KEY_MES_VENCIMENTO+" = "+mes+" AND ";
			sql += Conta.KEY_ANO_VENCIMENTO+" = "+ano;
			sql += " ORDER BY "+Conta.KEY_DIA_VENCIMENTO+" DESC";
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Conta.TABLE_NAME,Conta.COLUMNS, sql,null,null,null,null);
			if(cursor!=null&&cursor.getCount()>0){
				List<Conta> contas = new ArrayList<Conta>();
				cursor.moveToFirst();
				do{
					Conta conta = new Conta();
					conta.setId(cursor.getInt(0));
					conta.setNome(cursor.getString(1));
					conta.setDescricao(cursor.getString(2));
					conta.setValorPagamento(cursor.getString(3));
					conta.setValorVencimento(cursor.getString(4));
					conta.setDiaPagamento(cursor.getInt(5));
					conta.setMesPagamento(cursor.getInt(6));
					conta.setAnoPagamento(cursor.getInt(7));
					conta.setDiaVencimento(cursor.getInt(8));
					conta.setMesVencimento(cursor.getInt(9));
					conta.setAnoVencimento(cursor.getInt(10));
					conta.setTipoConta(cursor.getInt(11));
					conta.setTipoPessoa(cursor.getInt(12));
					conta.setPago((cursor.getInt(13)==1)?(true):(false));
					conta.setNumParcelas(cursor.getInt(14));
					conta.setNumTotalParcelas(cursor.getInt(15));
					
					contas.add(conta);
				}while(cursor.moveToNext());
				
				return contas;
			}
			return null;
		}catch(Exception e){
			throw new DAOException("Erro ao tentar recuperar as contas de amanha", e);
		}
	}
	
	public List<Conta> getPassadasDesteMes() throws DAOException{
		try{
			Calendar data = Calendar.getInstance();
			int dia = data.get(Calendar.DATE);
			int mes = data.get(Calendar.MONTH);
			int ano = data.get(Calendar.YEAR);
			String sql = "";
			sql += Conta.KEY_DIA_VENCIMENTO+" < "+dia+" AND ";
			sql += Conta.KEY_MES_VENCIMENTO+" = "+mes+" AND ";
			sql += Conta.KEY_ANO_VENCIMENTO+" = "+ano;
			sql += " ORDER BY "+Conta.KEY_DIA_VENCIMENTO+" DESC";
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Conta.TABLE_NAME,Conta.COLUMNS, sql,null,null,null,null);
			if(cursor!=null&&cursor.getCount()>0){
				List<Conta> contas = new ArrayList<Conta>();
				cursor.moveToFirst();
				do{
					Conta conta = new Conta();
					conta.setId(cursor.getInt(0));
					conta.setNome(cursor.getString(1));
					conta.setDescricao(cursor.getString(2));
					conta.setValorPagamento(cursor.getString(3));
					conta.setValorVencimento(cursor.getString(4));
					conta.setDiaPagamento(cursor.getInt(5));
					conta.setMesPagamento(cursor.getInt(6));
					conta.setAnoPagamento(cursor.getInt(7));
					conta.setDiaVencimento(cursor.getInt(8));
					conta.setMesVencimento(cursor.getInt(9));
					conta.setAnoVencimento(cursor.getInt(10));
					conta.setTipoConta(cursor.getInt(11));
					conta.setTipoPessoa(cursor.getInt(12));
					conta.setPago((cursor.getInt(13)==1)?(true):(false));
					conta.setNumParcelas(cursor.getInt(14));
					conta.setNumTotalParcelas(cursor.getInt(15));
					
					contas.add(conta);
				}while(cursor.moveToNext());
				
				return contas;
			}
			return null;
		}catch(Exception e){
			throw new DAOException("Erro ao tentar recuperar as contas de amanha", e);
		}
	}
	
	public List<Conta> getHoje() throws DAOException{
		try{
			Calendar data = Calendar.getInstance();
			int dia = data.get(Calendar.DATE);
			int mes = data.get(Calendar.MONTH);
			int ano = data.get(Calendar.YEAR);
			String sql = "";
			sql += Conta.KEY_DIA_VENCIMENTO+" = "+dia+" AND ";
			sql += Conta.KEY_MES_VENCIMENTO+" = "+mes+" AND ";
			sql += Conta.KEY_ANO_VENCIMENTO+" = "+ano;
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Conta.TABLE_NAME,Conta.COLUMNS, sql,null,null,null,null);
			if(cursor!=null&&cursor.getCount()>0){
				List<Conta> contas = new ArrayList<Conta>();
				cursor.moveToFirst();
				do{
					Conta conta = new Conta();
					conta.setId(cursor.getInt(0));
					conta.setNome(cursor.getString(1));
					conta.setDescricao(cursor.getString(2));
					conta.setValorPagamento(cursor.getString(3));
					conta.setValorVencimento(cursor.getString(4));
					conta.setDiaPagamento(cursor.getInt(5));
					conta.setMesPagamento(cursor.getInt(6));
					conta.setAnoPagamento(cursor.getInt(7));
					conta.setDiaVencimento(cursor.getInt(8));
					conta.setMesVencimento(cursor.getInt(9));
					conta.setAnoVencimento(cursor.getInt(10));
					conta.setTipoConta(cursor.getInt(11));
					conta.setTipoPessoa(cursor.getInt(12));
					conta.setPago((cursor.getInt(13)==1)?(true):(false));
					conta.setNumParcelas(cursor.getInt(14));
					conta.setNumTotalParcelas(cursor.getInt(15));
					
					contas.add(conta);
				}while(cursor.moveToNext());
				
				return contas;
			}
			return null;
		}catch(Exception e){
			throw new DAOException("Erro ao tentar recuperar as contas de amanha", e);
		}
	}
	
	public List<Conta> getProximasDesteMes() throws DAOException{
		try{
			Calendar data = Calendar.getInstance();
			int dia = data.get(Calendar.DATE);
			int mes = data.get(Calendar.MONTH);
			int ano = data.get(Calendar.YEAR);
			String sql = "";
			sql += Conta.KEY_DIA_VENCIMENTO+" > "+dia+" AND ";
			sql += Conta.KEY_MES_VENCIMENTO+" = "+mes+" AND ";
			sql += Conta.KEY_ANO_VENCIMENTO+" = "+ano;
			sql += " ORDER BY "+Conta.KEY_DIA_VENCIMENTO;
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Conta.TABLE_NAME,Conta.COLUMNS, sql,null,null,null,null);
			if(cursor!=null&&cursor.getCount()>0){
				List<Conta> contas = new ArrayList<Conta>();
				cursor.moveToFirst();
				do{
					Conta conta = new Conta();
					conta.setId(cursor.getInt(0));
					conta.setNome(cursor.getString(1));
					conta.setDescricao(cursor.getString(2));
					conta.setValorPagamento(cursor.getString(3));
					conta.setValorVencimento(cursor.getString(4));
					conta.setDiaPagamento(cursor.getInt(5));
					conta.setMesPagamento(cursor.getInt(6));
					conta.setAnoPagamento(cursor.getInt(7));
					conta.setDiaVencimento(cursor.getInt(8));
					conta.setMesVencimento(cursor.getInt(9));
					conta.setAnoVencimento(cursor.getInt(10));
					conta.setTipoConta(cursor.getInt(11));
					conta.setTipoPessoa(cursor.getInt(12));
					conta.setPago((cursor.getInt(13)==1)?(true):(false));
					conta.setNumParcelas(cursor.getInt(14));
					conta.setNumTotalParcelas(cursor.getInt(15));
					
					contas.add(conta);
				}while(cursor.moveToNext());
				
				return contas;
			}
			return null;
		}catch(Exception e){
			throw new DAOException("Erro ao tentar recuperar as contas de amanha", e);
		}
	}
	
	public List<Conta> getProximasProximoMes() throws DAOException{
		try{
			Calendar data = Calendar.getInstance();
			int mes = data.get(Calendar.MONTH);
			int ano = data.get(Calendar.YEAR);
			
			if(mes==12){
				mes=1;
				ano++;
			}else{
				mes++;
			}
			
			String sql = "";
			sql += Conta.KEY_MES_VENCIMENTO+" = "+mes+" AND ";
			sql += Conta.KEY_ANO_VENCIMENTO+" = "+ano;
			sql += " ORDER BY "+Conta.KEY_DIA_VENCIMENTO;
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(Conta.TABLE_NAME,Conta.COLUMNS, sql,null,null,null,null);
			if(cursor!=null&&cursor.getCount()>0){
				List<Conta> contas = new ArrayList<Conta>();
				cursor.moveToFirst();
				do{
					Conta conta = new Conta();
					conta.setId(cursor.getInt(0));
					conta.setNome(cursor.getString(1));
					conta.setDescricao(cursor.getString(2));
					conta.setValorPagamento(cursor.getString(3));
					conta.setValorVencimento(cursor.getString(4));
					conta.setDiaPagamento(cursor.getInt(5));
					conta.setMesPagamento(cursor.getInt(6));
					conta.setAnoPagamento(cursor.getInt(7));
					conta.setDiaVencimento(cursor.getInt(8));
					conta.setMesVencimento(cursor.getInt(9));
					conta.setAnoVencimento(cursor.getInt(10));
					conta.setTipoConta(cursor.getInt(11));
					conta.setTipoPessoa(cursor.getInt(12));
					conta.setPago((cursor.getInt(13)==1)?(true):(false));
					conta.setNumParcelas(cursor.getInt(14));
					conta.setNumTotalParcelas(cursor.getInt(15));
					
					contas.add(conta);
				}while(cursor.moveToNext());
				
				return contas;
			}
			return null;
		}catch(Exception e){
			throw new DAOException("Erro ao tentar recuperar as contas de amanha", e);
		}
	}

}
