package com.example.aluno.teste_aplicacao;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.view.Window;
import android.widget.TextView;
import android.os.Vibrator;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {

    private NfcAdapter nfcAdapter;
    public TextView textViewResult;
    Vibrator vibrator;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("");
    }

    public void onButtonMenuClick(View view) {
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
                vibrator.vibrate(500);

                return true;
            }
        }

        return false;
    }

/*
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    public void onButtonCoresClick(View view) {
        Intent intent = new Intent(this, MenuAplicacao.class);
        startActivity(intent);
    }
/*
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
    }*/

    @Override
    protected void onResume() {
        super.onResume();

        nfcAdapter = NfcAdapter.getDefaultAdapter(getApplication());
        nfcAdapter.enableReaderMode(this, new NfcAdapter.ReaderCallback() {
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
                    if (filterMessage((NdefMessage) rawMsgs[i])) {
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.aluno.teste_aplicacao/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.aluno.teste_aplicacao/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
