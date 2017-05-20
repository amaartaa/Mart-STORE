package com.example.uti.mart;

        import android.app.Dialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v4.app.ShareCompat;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

public class Main2Activity extends AppCompatActivity {

    TextView mTextHarga, Qty_Makanan, Qty_Minuman;
    final Context context = this;
    Spinner Spin_makanan, Spin_minuman;
    List<String> L_Makanan = new ArrayList<>(); //membuat array list Makanan
    List<String> L_Minuman = new ArrayList<>(); //membuat array list Minuman
    Button btn_plus_minuman, btn_plus_makanan, btn_min_makanan, btn_min_minuman;
    int harga = 0, qty_makanan = 0, qty_minuman = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Qty_Makanan = (TextView) findViewById(R.id.jumlah_makanan);
        Qty_Minuman = (TextView) findViewById(R.id.jumlah_minuman);
        mTextHarga = (TextView) findViewById(R.id.total_tab2);
        btn_plus_makanan = (Button) findViewById(R.id.btn_plus_makanan);
        btn_plus_minuman = (Button) findViewById(R.id.btn_plus_minuman);
        btn_min_makanan = (Button) findViewById(R.id.btn_min_makanan);
        btn_min_minuman = (Button) findViewById(R.id.btn_min_minuman);

//======SPINNER MAKANAN=============================================================================
        Spin_makanan = (Spinner) findViewById(R.id.spinner_makanan);
        L_Makanan.add("Takoyaki");
        L_Makanan.add("Okonomiyaki");                                                                                           //memasukan info makanan ke spinner

        ArrayAdapter<String> dataAdaptermakanan = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, L_Makanan);    //Penghubung Spinner dan array
        dataAdaptermakanan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                              //dropdown ke bawah
        Spin_makanan.setAdapter(dataAdaptermakanan);


//======SPINNER MINUMAN=============================================================================
        Spin_minuman = (Spinner) findViewById(R.id.spinner_minuman);
        L_Minuman.add("Greentea Latte");
        L_Minuman.add("Es Teh");                                                                                                //memasukan info minuman ke spinner

        ArrayAdapter<String> dataAdapterminuman = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, L_Minuman);    //Penghubung Spinner dan array
        dataAdapterminuman.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                              //dropdown ke bawah
        Spin_minuman.setAdapter(dataAdapterminuman);
    }

    //==OPERASI QUANTITY MAKANAN========================================================================
    public void OnClickPlusMakanan(View view) {
        harga += 10000; //logic tombol >
        qty_makanan += 1;
        Qty_Makanan.setText(qty_makanan + "");
        mTextHarga.setText("Rp. " + harga);
    }

    public void OnClickMinMakanan(View view) { //logic button <
        if (harga != 0) {
            harga -= 10000;
            qty_makanan -= 1;
            Qty_Makanan.setText(qty_makanan + "");
            mTextHarga.setText("Rp. " + harga);
        } else {
            harga = 0;
            qty_makanan = 0;
            Qty_Makanan.setText(qty_makanan + "");
            mTextHarga.setText("Rp. " + harga);
        }
    }

    //==OPERASI QUANTITY MINUMAN========================================================================
    public void OnClickPlusMinuman(View view) {
        harga += 5000; //logic tombol >
        qty_minuman += 1;
        Qty_Minuman.setText(qty_minuman + "");
        mTextHarga.setText("Rp. " + harga);
    }

    public void OnClickMinMinuman(View view) { //logic button <
        if (harga != 0) {
            harga -= 5000;
            qty_minuman -= 1;
            Qty_Minuman.setText(qty_minuman + "");
            mTextHarga.setText("Rp. " + harga);
        } else {
            harga = 0;
            qty_minuman = 0;
            Qty_Minuman.setText(qty_minuman + "");
            mTextHarga.setText("Rp. " + harga);
        }
    }

    //==OPERASI TOMBOL RESET DAN ORDER==================================================================
    public void onClickReset(View v) {
        harga = 0; //logic reset button
        qty_minuman = 0;
        qty_makanan = 0;
        mTextHarga.setText("");
        Qty_Makanan.setText(qty_makanan + "");
        Qty_Minuman.setText(qty_minuman + "");
    }

    //==OPERASI TOMBOL ORDER============================================================================
    public void onClickOrder(View v) {

        String String_Makanan = null;
        String_Makanan = Spin_makanan.getSelectedItem().toString();
        String String_Minuman = null;
        String_Minuman = Spin_minuman.getSelectedItem().toString();


//======MEMUNCULKAN DIALOG==========================================================================
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(String_Makanan + "\t\t" + qty_makanan + "\n" + String_Minuman + "\t\t" + qty_minuman);     //Menampilkan pesanan dan jumlah pesanan

        Button dialogButtonKembali = (Button) dialog.findViewById(R.id.dialogButtonKembali);        //Tombol kembali
        dialogButtonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();                                                                   //keluar dari dialog
            }
        });

        Button dialogButtonOk = (Button) dialog.findViewById(R.id.dialogButtonOK);                  //Tombol Oke
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Iharga = new Intent(Main2Activity.this, Main3Activity.class);                //menuju layout 3
                Iharga.putExtra("harga", mTextHarga.getText().toString());                           //Membawa keterangan harga yang fix
                //PR masukin apa yang ada dispinner sama jumlahnya
                startActivity(Iharga);
            }
        });
        dialog.show();
    }
//==================================================================================================

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
