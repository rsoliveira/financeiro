package br.com.renato.financeiro.entidade;

public class Conta {
	
	public static final String KEY_ID = "id";
	public static final String KEY_NOME = "nome";
	public static final String KEY_DESCRICAO = "descricao";
	public static final String KEY_VALOR_PAGAMENTO = "valor_pagamento";
	public static final String KEY_VALOR_VENCIMENTO = "valor_vencimento";
	public static final String KEY_DIA_PAGAMENTO = "dia_pagamento";
	public static final String KEY_MES_PAGAMENTO = "mes_pagamento";
	public static final String KEY_ANO_PAGAMENTO = "ano_pagamento";
	public static final String KEY_DIA_VENCIMENTO = "dia_vencimento";
	public static final String KEY_MES_VENCIMENTO = "mes_vencimento";
	public static final String KEY_ANO_VENCIMENTO = "ano_vencimento";
	public static final String KEY_TIPO_CONTA = "tipo_conta";
	public static final String KEY_TIPO_PESSOA = "tipo_pessoa";
	public static final String KEY_PAGO = "pago";
	public static final String KEY_NUM_PARCELAS = "num_parcelas";
	public static final String KEY_NUM_TOTAL_PARCELAS = "num_total_parcelas";
	public static final String TABLE_NAME = "conta";
	public static final String[] COLUMNS = {
			KEY_ID,
			KEY_NOME,
			KEY_DESCRICAO,
			KEY_VALOR_PAGAMENTO,
			KEY_VALOR_VENCIMENTO,
			KEY_DIA_PAGAMENTO,
			KEY_MES_PAGAMENTO,
			KEY_ANO_PAGAMENTO,
			KEY_DIA_VENCIMENTO,
			KEY_MES_VENCIMENTO,
			KEY_ANO_VENCIMENTO,
			KEY_TIPO_CONTA,
			KEY_TIPO_PESSOA,
			KEY_PAGO,
			KEY_NUM_PARCELAS,
			KEY_NUM_TOTAL_PARCELAS
	};
	
	public static final Integer TIPO_CONTA_GASTO = 1;
	public static final Integer TIPO_CONTA_PROGRAMADA = 2;
	public static final Integer TIPO_CONTA_PARCELADA = 3;
	public static final Integer TIPO_PESSOA_FISICA = 1;
	public static final Integer TIPO_PESSOA_JURIDICA = 2;
	
	private Integer id;
	private String nome;
	private String descricao;
	private String valorPagamento;
	private String valorVencimento;
	private Integer diaPagamento;
	private Integer mesPagamento;
	private Integer anoPagamento;
	private Integer diaVencimento;
	private Integer mesVencimento;
	private Integer anoVencimento;
	private Integer tipoConta;
	private Integer tipoPessoa;
	private Boolean pago;
	private Integer numParcelas;
	private Integer numTotalParcelas;
	
	public void setId(Integer id){this.id=id;}
	public void setNome(String nome){this.nome=nome;}
	public void setDescricao(String descricao){this.descricao=descricao;}
	public void setValorPagamento(String valorPagamento){this.valorPagamento=valorPagamento;}
	public void setValorVencimento(String valorVencimento){this.valorVencimento=valorVencimento;}
	public void setDiaPagamento(Integer diaPagamento){this.diaPagamento=diaPagamento;}
	public void setMesPagamento(Integer mesPagamento){this.mesPagamento=mesPagamento;}
	public void setAnoPagamento(Integer anoPagamento){this.anoPagamento=anoPagamento;}
	public void setDiaVencimento(Integer diaVencimento){this.diaVencimento=diaVencimento;}
	public void setMesVencimento(Integer mesVencimento){this.mesVencimento=mesVencimento;}
	public void setAnoVencimento(Integer anoVencimento){this.anoVencimento=anoVencimento;}
	public void setTipoConta(Integer tipoConta){this.tipoConta=tipoConta;}
	public void setTipoPessoa(Integer tipoPessoa){this.tipoPessoa=tipoPessoa;}
	public void setPago(Boolean pago){this.pago=pago;}
	public void setNumParcelas(Integer numParcelas){this.numParcelas=numParcelas;}
	public void setNumTotalParcelas(Integer numTotalParcelas){this.numTotalParcelas=numTotalParcelas;}
	
	public Integer getId(){return this.id;}
	public String getNome(){return this.nome;}
	public String getDescricao(){return this.descricao;}
	public String getValorPagamento(){return this.valorPagamento;}
	public String getValorVencimento(){return this.valorVencimento;}
	public Integer getDiaPagamento(){return this.diaPagamento;}
	public Integer getMesPagamento(){return this.mesPagamento;}
	public Integer getAnoPagamento(){return this.anoPagamento;}
	public Integer getDiaVencimento(){return this.diaVencimento;}
	public Integer getMesVencimento(){return this.mesVencimento;}
	public Integer getAnoVencimento(){return this.anoVencimento;}
	public Integer getTipoConta(){return this.tipoConta;}
	public Integer getTipoPessoa(){return this.tipoPessoa;}
	public Boolean getPago(){return this.pago;}
	public Integer getNumParcelas(){return this.numParcelas;}
	public Integer getNumTotalParcelas(){return this.numTotalParcelas;}
	
	public static String getCreateTable(){
		String sql = "";
		
		sql += "CREATE TABLE "+TABLE_NAME+" (";
		sql += KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
		sql += KEY_NOME+" TEXT, ";
		sql += KEY_DESCRICAO+" TEXT, ";
		sql += KEY_VALOR_PAGAMENTO+" TEXT, ";
		sql += KEY_VALOR_VENCIMENTO+" TEXT, ";
		sql += KEY_DIA_PAGAMENTO+" INTEGER, ";
		sql += KEY_MES_PAGAMENTO+" INTEGER, ";
		sql += KEY_ANO_PAGAMENTO+" INTEGER, ";
		sql += KEY_DIA_VENCIMENTO+" INTEGER, ";
		sql += KEY_MES_VENCIMENTO+" INTEGER, ";
		sql += KEY_ANO_VENCIMENTO+" INTEGER, ";
		sql += KEY_TIPO_CONTA+" INTEGER, ";
		sql += KEY_TIPO_PESSOA+" INTEGER, ";
		sql += KEY_PAGO+" TEXT, ";
		sql += KEY_NUM_PARCELAS+" INTEGER, ";
		sql += KEY_NUM_TOTAL_PARCELAS+" INTEGER ) ";
		
		return sql;
	}
	
	public static String getDropTable(){
		return "DROP TABLE IF EXISTS "+TABLE_NAME;
	}

}
