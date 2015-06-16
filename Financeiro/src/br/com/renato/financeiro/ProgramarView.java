package br.com.renato.financeiro;

import br.com.renato.financeiro.componente.Botao;
import br.com.renato.financeiro.service.ContaService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ProgramarView implements IView, OnClickListener, android.content.DialogInterface.OnClickListener{

	private final Integer BTN_PROGRAMAR = 1;
	private final Integer BTN_VOLTAR = 2;
	
	private Activity activity;
	private ResumoView resumoView;
	private ContaService contaService;
	
	/** Componentes */
	EditText edtNome = null;
	EditText edtDescricao = null;
	EditText edtValor = null;
	EditText edtData = null;
	EditText edtParcelas = null;
	RadioButton rbFisico = null;
	RadioButton rbJuridico = null;
	RadioButton rbParcelada = null;
	RadioButton rbProgramada = null;
	
	public ProgramarView(Activity activity) {
		this.activity = activity;
		this.contaService = new ContaService(activity);
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
		titulo.setText("Cadastrar conta programada");
	}

	@Override
	public void montarRodape() {
		RelativeLayout footer = (RelativeLayout) activity.findViewById(R.id.footer);
		footer.removeAllViews();
		LinearLayout ll = new LinearLayout(activity);
		ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.addView(new Botao(activity, "Programar", BTN_PROGRAMAR, this));
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
		TextView txtData = new TextView(activity);
		txtData.setText("Dia Vencimento:");
		edtData = new EditText(activity);
		TextView txtParcelas = new TextView(activity);
		txtParcelas.setText("Qtde Parcelas:");
		edtParcelas = new EditText(activity);
		
		RadioGroup rg = new RadioGroup(activity);
		rbFisico = new RadioButton(activity);
		rbJuridico = new RadioButton(activity);
		
		RadioGroup rgTipo = new RadioGroup(activity);
		rbParcelada = new RadioButton(activity);
		rbProgramada = new RadioButton(activity);
		
		rbFisico.setText("Fisica");
		rbJuridico.setText("Juridica");
		rbParcelada.setText("Parcelada");
		rbProgramada.setText("Programada");
		
		rgTipo.setOrientation(RadioGroup.HORIZONTAL);
		rgTipo.addView(rbParcelada);
		rgTipo.addView(rbProgramada);
		
		rg.setOrientation(RadioGroup.HORIZONTAL);
		rg.addView(rbFisico);
		rg.addView(rbJuridico);
		
		View linha = new View(activity);
		linha.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,3));
		linha.setBackgroundColor(Color.BLACK);
		
		conteudo.addView(txtNome);
		conteudo.addView(edtNome);
		conteudo.addView(txtDescricao);
		conteudo.addView(edtDescricao);
		conteudo.addView(txtValor);
		conteudo.addView(edtValor);
		conteudo.addView(txtData);
		conteudo.addView(edtData);
		conteudo.addView(txtParcelas);
		conteudo.addView(edtParcelas);
		conteudo.addView(rg);
		conteudo.addView(linha);
		conteudo.addView(rgTipo);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==BTN_PROGRAMAR){
			try{
				boolean cadastrou = contaService.cadastrarContaProgramada(edtNome.getText().toString(),
													  edtDescricao.getText().toString(), 
													  edtValor.getText().toString(),
													  new Integer((edtData.getText().toString()!=null&&edtData.getText().toString().length()>0)?(edtData.getText().toString()):("0")),
													  new Integer((edtParcelas.getText().toString()!=null&&edtParcelas.getText().toString().length()>0)?(edtParcelas.getText().toString()):("0")),
													  rbFisico.isChecked(),
													  rbParcelada.isChecked());
				if(cadastrou){
					AlertDialog.Builder builder = new AlertDialog.Builder(activity);
					builder.setTitle("Sucesso");
					builder.setMessage("Conta cadastrada com sucesso!");
					builder.setPositiveButton("Ok", this);
					builder.create();
					builder.show();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(v.getId()==BTN_VOLTAR){
			this.resumoView = new ResumoView(activity);
			resumoView.montarTela();
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		this.resumoView = new ResumoView(activity);
		resumoView.montarTela();
	}
	
	

}
