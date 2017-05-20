package com.example.uti.mart;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v4.app.ShareCompat;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickPesan(View v){
        Intent iLanjut = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(iLanjut);                                                                         //Kembali ke tab sebelumnya
    }

    public void onClickMenuMM(View v){
        Intent iMenu = new Intent(MainActivity.this, MenuMM.class);
        startActivity(iMenu);                                                                         //Kembali ke tab sebelumnya
    }


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
