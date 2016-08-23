package com.example.aluno.teste_aplicacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;

enum EnumCores{
    BRANCO,
    PRETO,
    AMARELO,
    VERDE,
    AZUL,
    ROXO,
    ROSA,
    MARROM,
    LARANJA,
    VERMELHO
}

public class ColorMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cores);
    }

    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    public void onButtonClick(View v){
        Intent intent = new Intent();
        String nome = v.getTag().toString();

        switch (nome){
            case "AMARELO":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.AMARELO);
                break;
            case "AZUL":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.AZUL);
                break;
            case "BRANCO":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.BRANCO);
                break;
            case "LARANJA":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.LARANJA);
                break;
            case "MARROM":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.MARROM);
                break;
            case "PRETO":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.PRETO);
                break;
            case "ROSA":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.ROSA);
                break;
            case "ROXO":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.ROXO);
                break;
            case "VERDE":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.VERDE);
                break;
            case "VERMELHO":
                intent = new Intent(this, DescricaoCor.class);
                intent.putExtra("EnumCores", EnumCores.VERMELHO);
                break;
            default:
                break;

        }
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
