package com.example.aluno.teste_aplicacao;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {

    private NfcAdapter nfcAdapter;
    public TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
    }

    public void onButtonMenuClick(View view){
        Intent intent = new Intent(this, MenuAplicacao.class);
        startActivity(intent);
    }

    private boolean filterMessage(NdefMessage message) {
        if (message == null)
            return false;

        NdefRecord ndefRecord = null;

        try {
            ndefRecord = message.getRecords()[0];
        } catch (Throwable ex) {
            //apenas ignora
        }

        if (ndefRecord != null &&
                ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN &&
                ndefRecord.getType().length == 1 &&
                ndefRecord.getType()[0] == NdefRecord.RTD_TEXT[0]) {

            byte[] payload = ndefRecord.getPayload();

				/*
				0 = n (tamanho do locale)
				1
				...
				n = locale
				n+1 em diante: mensagem (utf-8)
				*/
            if (payload != null &&
                    payload.length >= 4) {

                int localeLength = payload[0];
                String locale = new String(payload, 1, localeLength);
                String text = new String(payload, 1 + localeLength, payload.length - 1 - localeLength);
                System.out.println("MIME: " + ndefRecord.toMimeType());
                System.out.println("Locale: " + locale);
                System.out.println("Text: " + text);
                textViewResult.setText(text);

                return true;
            }
        }

        return false;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onButtonCoresClick(View view){
        Intent intent = new Intent(this, MenuAplicacao.class);
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

    @Override
    protected void onResume() {
        super.onResume();

        nfcAdapter = NfcAdapter.getDefaultAdapter(getApplication());
        nfcAdapter.enableReaderMode(this,                 new NfcAdapter.ReaderCallback() {
            @Override
            public void onTagDiscovered(final Tag tag) {
                //Update the view since input was recieved.
                //recievedInput();
            }
        }, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_NFC_B | NfcAdapter.FLAG_READER_NFC_BARCODE | NfcAdapter.FLAG_READER_NFC_F | NfcAdapter.FLAG_READER_NFC_V | NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS, new Bundle());

        Intent intent = getIntent();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                for (int i = 0; i < rawMsgs.length; i++) {
                    if (filterMessage((NdefMessage)rawMsgs[i])) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        nfcAdapter.disableReaderMode(this);
    }


    @Override
    public void onTagDiscovered(Tag tag) {
        Ndef ndef = Ndef.get(tag);
        NdefMessage message = null;
        try {
            message = ndef.getNdefMessage();
        } catch (Throwable ex) {
            //apenas ignora!
        }
        if (message == null) {
            try {
                message = ndef.getCachedNdefMessage();
            } catch (Throwable ex) {
                //apenas ignora!
            }
        }
        filterMessage(message);
    }
}
