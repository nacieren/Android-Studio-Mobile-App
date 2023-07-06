package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class uyeActivity extends AppCompatActivity {

    private Button bttn;
    private EditText emailText;
    private EditText şifreText;
    private EditText isimText;
    private EditText soyisimText;
    private EditText doğumyılıText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye);
        bttn = (Button)findViewById(R.id.buttonUye);
        emailText = findViewById(R.id.editTextEmail);
        şifreText = findViewById(R.id.editTextPassword);
        isimText = findViewById(R.id.editTextName);
        soyisimText = findViewById(R.id.editTextSurname);
        doğumyılıText = findViewById(R.id.editTextBirthYear);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("kullanici");
                String email = emailText.getText().toString();
                String şifre = şifreText.getText().toString();
                String isim = isimText.getText().toString();
                String soyisim = soyisimText.getText().toString();
                String doğumyılı = doğumyılıText.getText().toString();
                kullanici kullanici = new kullanici(email,isim,soyisim,şifre,doğumyılı);
                String kullaniciId = email;
                reference.child(kullaniciId).setValue(kullanici);
                Toast.makeText(uyeActivity.this, "Kullanıcı bilgileriniz kaydedildi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(uyeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true; }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
                Locale locale = new Locale("tr");
                Configuration config = new Configuration(getResources().getConfiguration());
                config.setLocale(locale);
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
                break;
            case R.id.item3:
                Intent intent2 = new Intent(uyeActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}