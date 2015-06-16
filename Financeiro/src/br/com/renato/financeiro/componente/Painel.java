package br.com.renato.financeiro.componente;

import java.util.List;

import br.com.renato.financeiro.entidade.Conta;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Painel extends TableLayout{

	public Painel(Context context, String titulo, List<Conta> contas) {
		super(context);
		
		TextView txtTitulo = new TextView(context);
		txtTitulo.setText(titulo);
		txtTitulo.setTextColor(Color.WHITE);
		
		TableRow tr1 = new TableRow(context);
		tr1.addView(txtTitulo);
		tr1.setBackgroundColor(Color.BLACK);
		tr1.setPadding(25, 10, 0, 0);
		tr1.setMinimumHeight(65);
		addView(tr1);
		
		if(contas != null){
			for(Conta c : contas){
				TableRow tr = new TableRow(context);
				tr.setGravity(Gravity.END);
				TextView txtData = new TextView(context);
				txtData.setText(c.getDiaVencimento()+"/"+c.getMesVencimento()+"/"+c.getAnoVencimento());
				txtData.setPadding(25, 0, 50, 0);
				TextView txtNome = new TextView(context);
				txtNome.setText(c.getNome());
				txtNome.setPadding(25, 0, 50, 0);
				txtNome.setBackgroundColor(Color.GRAY);
				TextView txtValor = new TextView(context);
				txtValor.setText(c.getValorVencimento());
				txtValor.setGravity(Gravity.END);
				txtValor.setPadding(25, 0, 50, 0);
				
				tr.addView(txtData);
				tr.addView(txtNome);
				tr.addView(txtValor);
				
				addView(tr);
			}
		}
		
		TableRow trFim = new TableRow(context);
		trFim.setMinimumHeight(50);
		trFim.setBackgroundColor(Color.WHITE);
		
		addView(trFim);
	}

}
