package com.example.uti.mart;

        import android.app.Dialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.SystemClock;
        import android.support.v4.app.ShareCompat;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EdgeEffect;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    TextView harga_fix;                                                                             //DeklarasI
    EditText mTextNama, no_meja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//======MEMUNCULKAN HARGA DARI MAIN ACTIVITY 2 KE TEXTVIEW DI MAIN ACTIVITY 3=======================
        harga_fix = (TextView) findViewById(R.id.total_tab3);
        harga_fix.setText(getIntent().getExtras().getString("harga"));
        //Keluar Program Otomatis
    }
    //======MEMUNCULKAN TOAST SESUAI RADIOBUTTON========================================================
    public void onClickTakeAway (View v){
        Toast.makeText(Main3Activity.this,"Packaging...", Toast.LENGTH_LONG).show();                //Toast untuk Take Away
    }
    public void onClickMakanSini (View v){
        Toast.makeText(Main3Activity.this,"Ready to serve...", Toast.LENGTH_LONG).show();           //Toast untuk Makan sini
    }
    //======PENGATURAN TOMBOL BATAL DAN BAYAR===========================================================
    public void onClickBatal(View v){
        System.exit(0);                                                                             //Kembali ke tab sebelumnya
    }
    public void onClickBayar(View v){
        //moveTaskToBack(true);                                                                     //Keluar Program Otomatis
        mTextNama = (EditText) findViewById(R.id.AN);
        no_meja = (EditText) findViewById(R.id.no_meja);
        String to = "fariz.hasabii@null.net";
        String subject = mTextNama.getText().toString();
        String message = "Pesanan saya seharga "
                + harga_fix.getText()
                + " dan saya sedang duduk di meja nomor "
                + no_meja.getText();

        Intent email = new Intent(Intent.ACTION_SEND);                                              //order email
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Kirim email dengan"));
    }
//=================================================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true; //Membuat fungsi menu
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Disini baru membuat menu inflatternya
        switch (item.getItemId()){
            case R.id.about: //Jika yang diklik about maka ke profil activity About
                Intent about = new Intent(this,ProfileActivity.class);
                startActivity(about);
                break;
            case R.id.order: //Jika yang diklik order maka ke profil activity order
                Intent order = new Intent(this,Main2Activity.class);
                startActivity(order);
                break;
            case R.id.map:
                String addressString = "Matraman";
                Uri geoLocation = Uri.parse("geo:0,0?q=" + addressString);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geoLocation);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.share:
                Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                        .setType("text/plain")
                        .setText("Mart Store, for Indonesian Good People! Try an awesome App here -> https://goo.gl/wyrKV1")
                        .getIntent();
                startActivity(shareIntent);
                break;
            case R.id.quit: //apabila yg dipilih quit maka ia akan keluar dr apk
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure want to exit ?"); // memunculkan pilihan
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog quit = builder.create();
                quit.show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

