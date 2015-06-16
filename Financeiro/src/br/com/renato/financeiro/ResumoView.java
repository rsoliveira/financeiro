package br.com.renato.financeiro;

import java.util.List;

import br.com.renato.financeiro.componente.Botao;
import br.com.renato.financeiro.componente.Painel;
import br.com.renato.financeiro.entidade.Conta;
import br.com.renato.financeiro.service.ContaService;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResumoView implements IView,OnClickListener{
	
	private final Integer BTN_PROGRAMAR = 1;
	private final Integer BTN_GASTAR = 2;
	private final Integer BTN_SAIR = 3;
	private final Integer BTN_PAGAR = 4;
	
	private Activity activity;
	
	private ProgramarView programarView;
	private GastarView gastarView;
	private ContaService contaService;
	private PagarView pagarView;
	
	public ResumoView(Activity activity){
		this.activity=activity;
		this.contaService=new ContaService(activity);
	}
	
	@Override
	public void montarTela(){
		montarTopo();
		montarRodape();
		montarConteudo();
	}
	
	@Override
	public void montarTopo() {
		TextView titulo = (TextView) activity.findViewById(R.id.titulo_header);
		titulo.setText("Resumo");
	}

	@Override
	public void montarRodape() {
		RelativeLayout footer = (RelativeLayout) activity.findViewById(R.id.footer);
		footer.removeAllViews();
		LinearLayout ll = new LinearLayout(activity);
		ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.addView(new Botao(activity, "Gastar", BTN_GASTAR, this));
		ll.addView(new Botao(activity, "Pagar", BTN_PAGAR, this));
		ll.addView(new Botao(activity, "Marcar", BTN_PROGRAMAR, this));
		ll.addView(new Botao(activity, "Sair", BTN_SAIR, this));
		
		footer.addView(ll);
	}
	
	@Override
	public void montarConteudo() {
		try{
			LinearLayout conteudo = (LinearLayout) activity.findViewById(R.id.conteudo);
			conteudo.removeAllViews();
			
			List<Conta> contasOntem = contaService.getContasOntem();
			List<Conta> contasHoje = contaService.getContasHoje();
			List<Conta> contasAmanha = contaService.getContasAmanha();
			
			conteudo.setPadding(50, 50, 50, 10);
			
			conteudo.addView(new Painel(activity,"Próximas", contasAmanha));
			conteudo.addView(new Painel(activity,"Hoje", contasHoje));
			conteudo.addView(new Painel(activity,"Anteriores", contasOntem));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==BTN_PROGRAMAR){
			this.programarView = new ProgramarView(activity);
			programarView.montarTela();
		}else if(v.getId()==BTN_GASTAR){
			this.gastarView = new GastarView(activity);
			gastarView.montarTela();
		}else if(v.getId()==BTN_SAIR){
			activity.finish();
		}else if(v.getId()==BTN_PAGAR){
			this.pagarView = new PagarView(activity);
			pagarView.montarTela();
		}
	}

}
