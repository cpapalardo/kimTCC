package com.example.aluno.teste_aplicacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DescricaoCor extends AppCompatActivity {

    public TextView textViewColorTitle;
    public TextView textViewColorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_cor);

        textViewColorText = (TextView)findViewById(R.id.textViewColorText);
        textViewColorTitle = (TextView)findViewById(R.id.textViewColorTitle);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        EnumCores e = null;

        if(b != null){
            e = (EnumCores) b.get("EnumCores");
        }

        switch (e){
            case BRANCO:
                textViewColorTitle.setText(R.string.titulo_branco);
                textViewColorText.setText(R.string.descricao_branco);
                break;
            case PRETO:
                textViewColorTitle.setText(R.string.titulo_preto);
                textViewColorText.setText(R.string.descricao_preto);
                break;
            case AMARELO:
                textViewColorTitle.setText(R.string.titulo_amarelo);
                textViewColorText.setText(R.string.descricao_amarelo);
                break;
            case VERDE:
                textViewColorTitle.setText(R.string.titulo_verde);
                textViewColorText.setText(R.string.descricao_verde);
                break;
            case AZUL:
                textViewColorTitle.setText(R.string.titulo_azul);
                textViewColorText.setText(R.string.descricao_azul);
                break;
            case ROXO:
                textViewColorTitle.setText(R.string.titulo_roxo);
                textViewColorText.setText(R.string.descricao_roxo);
                break;
            case ROSA:
                textViewColorTitle.setText(R.string.titulo_rosa);
                textViewColorText.setText(R.string.descricao_rosa);
                break;
            case MARROM:
                textViewColorTitle.setText(R.string.titulo_marrom);
                textViewColorText.setText(R.string.descricao_marrom);
                break;
            case LARANJA:
                textViewColorTitle.setText(R.string.titulo_laranja);
                textViewColorText.setText(R.string.descricao_laranja);
                break;
            case VERMELHO:
                textViewColorTitle.setText(R.string.titulo_vermelho);
                textViewColorText.setText(R.string.descricao_vermelho);
                break;
            default:
        }


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_descricao_cor, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
