package com.example.aluno.teste_aplicacao.Utils;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ken on 28/06/2016.
 */
public class DataBase extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "Cores";
    public static final String TABELA = "Cor";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String DESCRICAO = "descricao";
    public static final String HEXADECIMAL = "hexadecimal";
    public static final int    VERSAO = 1;

    public DataBase(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE"+TABELA+"("
                + ID + "integer primary key autoincrement,"
                + NOME + "text,"
                + DESCRICAO + "text,"
                + HEXADECIMAL + "text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }

}
