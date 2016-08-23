package com.example.aluno.teste_aplicacao.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ken on 28/06/2016.
 */
public class DataBaseController {
    private SQLiteDatabase db;
    private DataBase banco;

    public DataBaseController(Context context){
        banco = new DataBase(context);
    }

    public String insereDado(String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DataBase.NOME, titulo);
        valores.put(DataBase.DESCRICAO, autor);
        valores.put(DataBase.HEXADECIMAL, editora);

        resultado = db.insert(DataBase.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {banco.ID, banco.DESCRICAO};

        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null);

        return cursor;
    }

}
