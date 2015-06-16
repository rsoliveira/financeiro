package br.com.renato.financeiro.componente;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

public class Botao extends LinearLayout{

	public Botao(Context context, String titulo, Integer id, OnClickListener click) {
		super(context);
		setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1));
		Button btn = new Button(context);
		btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		btn.setText(titulo);
		btn.setId(id);
		btn.setOnClickListener(click);
		addView(btn);
	}

}
