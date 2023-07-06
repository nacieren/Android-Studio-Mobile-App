package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button girişButton;
    private Button kayıtButton;
    private EditText emailText;
    private EditText şifreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girişButton = (Button) findViewById(R.id.buttonLogin);
        kayıtButton = (Button) findViewById(R.id.buttonSignup);
        emailText = findViewById(R.id.editTextUsername);
        şifreText = findViewById(R.id.editTextPassword);

        girişButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("kullanici");
                String email = emailText.getText().toString();
                String şifre = şifreText.getText().toString();

                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("email", emailText.getText().toString());
                startActivity(intent);
                // Firebase'den veri kontrolü yapma
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean emailExists = false;
                        boolean passwordMatches = false;

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            kullanici user = snapshot.getValue(kullanici.class);
                            if (user.getEmail().equals(email)) {
                                emailExists = true;
                                if (user.getŞifre().equals(şifre)) {
                                    passwordMatches = true;
                                    break;
                                }
                            }
                        }

                        if (emailExists && passwordMatches) {
                            Toast.makeText(MainActivity.this, "Kullanıcı girişi başarılı", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Kullanıcı adı veya şifre yanlış!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Database Error!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        kayıtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, uyeActivity.class);
                startActivity(intent);
            }
        });
    }
    public EditText getEmailText() {
        return emailText;
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
                Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent2);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

}
