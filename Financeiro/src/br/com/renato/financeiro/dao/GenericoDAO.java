package br.com.renato.financeiro.dao;

import br.com.renato.financeiro.entidade.Conta;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericoDAO extends SQLiteOpenHelper{

	private static final Integer DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "financeiro";
	
	public GenericoDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Conta.getCreateTable());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(Conta.getDropTable());
		this.onCreate(db);
	}

}
