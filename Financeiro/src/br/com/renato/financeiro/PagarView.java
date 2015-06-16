package br.com.renato.financeiro;

import java.util.List;

import br.com.renato.financeiro.componente.Botao;
import br.com.renato.financeiro.entidade.Conta;
import br.com.renato.financeiro.service.ContaService;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PagarView implements IView, OnClickListener{
	
	private Activity activity;
	private Integer BTN_PAGAR = 1;
	private Integer BTN_VOLTAR = 2;
	private ContaService contaService;
	
	public PagarView(Activity activity) {
		this.activity=activity;
		this.contaService = new ContaService(activity);
	}

	@Override
	public void montarTela() {
		montarTopo();
		montarConteudo();
		montarRodape();
	}

	@Override
	public void montarTopo() {
		TextView titulo = (TextView) activity.findViewById(R.id.titulo_header);
		titulo.setText("Pagar Conta");
	}

	@Override
	public void montarRodape() {
		RelativeLayout footer = (RelativeLayout) activity.findViewById(R.id.footer);
		footer.removeAllViews();
		LinearLayout ll = new LinearLayout(activity);
		ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.addView(new Botao(activity, "Pagar", BTN_PAGAR, this));
		ll.addView(new Botao(activity, "Voltar", BTN_VOLTAR, this));
		footer.addView(ll);
	}

	@Override
	public void montarConteudo() {
		try{
			LinearLayout conteudo = (LinearLayout) activity.findViewById(R.id.conteudo);
			conteudo.removeAllViews();
			conteudo.setOrientation(LinearLayout.VERTICAL);
			List<Conta> contas = contaService.getContasPagar();
			for(Conta c : contas){
				LinearLayout ll = new LinearLayout(activity);
				ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				TextView txt = new TextView(activity);
				txt.setText(c.getNome());
				ll.addView(txt);
				conteudo.addView(ll);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		
	}

}
