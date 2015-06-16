package br.com.renato.financeiro;

import br.com.renato.financeiro.componente.Botao;
import br.com.renato.financeiro.entidade.Conta;
import br.com.renato.financeiro.service.ContaService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GastarView implements IView,OnClickListener,android.content.DialogInterface.OnClickListener{

	private final Integer BTN_VOLTAR = 1;
	private final Integer BTN_GASTAR = 2;
	
	private ResumoView resumoView;
	private Activity activity;
	private ContaService contaService;
	
	private EditText edtNome;
	private EditText edtDescricao;
	private EditText edtValor;
	private RadioButton rbFisico;
	private RadioButton rbJuridico;
	
	public GastarView(Activity activity) {
		this.activity=activity;
		this.contaService = new ContaService(activity);
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==BTN_VOLTAR){
			this.resumoView = new ResumoView(activity);
			resumoView.montarTela();
		}else if(v.getId()==BTN_GASTAR){
			try{
				Integer tipoPessoa = 0;
				if(rbFisico.isChecked()){
					tipoPessoa = Conta.TIPO_PESSOA_FISICA;
				}else{
					tipoPessoa = Conta.TIPO_PESSOA_JURIDICA;
				}
				
				contaService.cadastrarGasto(edtNome.getText().toString(), 
						                    edtDescricao.getText().toString(),
						                    edtValor.getText().toString(), tipoPessoa);
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Sucesso");
				builder.setMessage("Gasto cadastrado com sucesso!");
				builder.setPositiveButton("Ok", this);
				builder.create();
				builder.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void montarTela() {
		montarTopo();
		montarRodape();
		montarConteudo();
	}

	@Override
	public void montarTopo() {
		TextView titulo = (TextView) activity.findViewById(R.id.titulo_header);
		titulo.setText("Cadastrar gasto");
	}

	@Override
	public void montarRodape() {
		RelativeLayout footer = (RelativeLayout) activity.findViewById(R.id.footer);
		footer.removeAllViews();
		LinearLayout ll = new LinearLayout(activity);
		ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.addView(new Botao(activity, "Gastar", BTN_GASTAR, this));
		ll.addView(new Botao(activity, "Voltar", BTN_VOLTAR, this));
		footer.addView(ll);
	}

	@Override
	public void montarConteudo() {
		LinearLayout conteudo = (LinearLayout) activity.findViewById(R.id.conteudo);
		conteudo.removeAllViews();
		conteudo.setOrientation(LinearLayout.VERTICAL);
		TextView txtNome = new TextView(activity);
		txtNome.setText("Nome:");
		edtNome = new EditText(activity);
		TextView txtDescricao = new TextView(activity);
		txtDescricao.setText("Descrição:");
		edtDescricao = new EditText(activity);
		TextView txtValor = new TextView(activity);
		txtValor.setText("Valor:");
		edtValor = new EditText(activity);
		
		RadioGroup rg = new RadioGroup(activity);
		rbFisico = new RadioButton(activity);
		rbJuridico = new RadioButton(activity);
		
		rbFisico.setText("Fisica");
		rbJuridico.setText("Juridica");
		
		rg.setOrientation(RadioGroup.HORIZONTAL);
		rg.addView(rbFisico);
		rg.addView(rbJuridico);
		
		
		conteudo.addView(txtNome);
		conteudo.addView(edtNome);
		conteudo.addView(txtDescricao);
		conteudo.addView(edtDescricao);
		conteudo.addView(txtValor);
		conteudo.addView(edtValor);
		conteudo.addView(rg);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		ResumoView resumoView = new ResumoView(activity);
		resumoView.montarTela();
	}

}
