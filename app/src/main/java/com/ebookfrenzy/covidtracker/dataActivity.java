package com.ebookfrenzy.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class dataActivity extends AppCompatActivity {

    EditText Name, country, age, number, con;
    Button submit, search;
    RadioButton fyes, fno, syes, sno, tyes, tno, foyes, fono, fiyes, fino, siyes, sino;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Name = findViewById(R.id.txtname);
        con = findViewById(R.id.co);
        country = findViewById(R.id.country);
        age = findViewById(R.id.age);
        number = findViewById(R.id.phonenumber);
        submit = findViewById(R.id.btnsubmit);
        fyes = findViewById(R.id.fyes);
        fno = findViewById(R.id.fno);
        syes = findViewById(R.id.syes);
        sno = findViewById(R.id.sno);
        tyes = findViewById(R.id.thyes);
        tno = findViewById(R.id.thno);
        foyes = findViewById(R.id.foyes);
        fono = findViewById(R.id.fono);
        fiyes = findViewById(R.id.fiyes);
        fino = findViewById(R.id.fino);
        siyes = findViewById(R.id.siyes);
        sino = findViewById(R.id.sino);
        search = findViewById(R.id.btnsubmitc);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // declared variable as zero
                int count = 0; // for yes count
                int cnt = 0; // for no count
                if (fyes.isChecked()) {
                    count = count + 1;
                } else {
                    cnt = cnt + 1;
                }
                if (syes.isChecked()) {
                    count = count + 1;
                } else {
                    cnt = cnt + 1;
                }
                if (tyes.isChecked()) {
                    count = count + 1;
                } else {
                    cnt = cnt + 1;
                }
                if (foyes.isChecked()) {
                    count = count + 1;
                } else {
                    cnt = cnt + 1;
                }
                if (fiyes.isChecked()) {
                    count = count + 1;
                } else {
                    cnt = cnt + 1;
                }
                if (siyes.isChecked()) {
                    count = count + 1;
                } else {
                    cnt = cnt + 1;
                }

                if (!fyes.isChecked() && !fno.isChecked()) {
                    Toast.makeText(dataActivity.this, "Please select any 1 for question 1 ", Toast.LENGTH_LONG).show();
                } else if (!syes.isChecked() && !sno.isChecked()) {
                    Toast.makeText(dataActivity.this, "Please select any 1 for question 2", Toast.LENGTH_LONG).show();
                } else if (!tyes.isChecked() && !tno.isChecked()) {
                    Toast.makeText(dataActivity.this, "Please select any 1 for question 3", Toast.LENGTH_LONG).show();
                } else if (!foyes.isChecked() && !fono.isChecked()) {
                    Toast.makeText(dataActivity.this, "Please select any 1 for question 4", Toast.LENGTH_LONG).show();
                } else if (!fiyes.isChecked() && !fino.isChecked()) {
                    Toast.makeText(dataActivity.this, "Please select any 1 for question 5 ", Toast.LENGTH_LONG).show();
                } else if (!siyes.isChecked() && !sino.isChecked()) {
                    Toast.makeText(dataActivity.this, "Please select any 1 for question 6 ", Toast.LENGTH_LONG).show();
                } else {


                    database = FirebaseDatabase.getInstance();
                    ref = database.getReference("user");


                    final String nam = Name.getText().toString().trim();
                    final String num = number.getText().toString().trim();
                    final String coun = con.getText().toString().trim().toUpperCase();
                    final String ag = age.getText().toString().trim();


                    if (nam.isEmpty()) {
                        Toast.makeText(dataActivity.this, "Please enter Your Name", Toast.LENGTH_LONG).show();
                    } else if (ag.isEmpty()) {
                        Toast.makeText(dataActivity.this, "Please enter Age ", Toast.LENGTH_LONG).show();
                    } else if (ag.matches(".*[a-zA-Z]+.*")) {
                        Toast.makeText(dataActivity.this, "Please Enter Age in Digit", Toast.LENGTH_LONG).show();
                    } else if (num.isEmpty()) {
                        Toast.makeText(dataActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_LONG).show();
                    } else if (num.length() < 10 || num.length() > 12) {
                        Toast.makeText(dataActivity.this, "Please enter 10 to 12 digit Phone number", Toast.LENGTH_LONG).show();
                        Log.v("tagv", "" + num.length());
                    } else if (num.matches(".*[a-zA-Z]+.*")) {
                        Toast.makeText(dataActivity.this, "Please enter proper phone number", Toast.LENGTH_LONG).show();
                    } else if (coun.isEmpty()) {
                        Toast.makeText(dataActivity.this, "Please enter your Country", Toast.LENGTH_LONG).show();
                    } else if (coun.contains("0") || coun.contains("1") || coun.contains("2") || coun.contains("3") || coun.contains("4") || coun.contains("5") || coun.contains("6") || coun.contains("7") || coun.contains("8") || coun.contains("9")) {
                        Toast.makeText(dataActivity.this, "Please enter your Country , Not digit!!", Toast.LENGTH_LONG).show();
                    } else {

                        java.util.Locale[] list = SimpleDateFormat.getAvailableLocales();
                        Set set = new TreeSet();
                        for (int i = 0; i < list.length; i++) {
                            String a = (list[i].getDisplayCountry() + "\t\t\t:\t" + list[i].toString());
                            String data = a.substring(0, a.indexOf(":"));

                            set.add(data);
                        }

                        boolean isValidCountry = false;
                        Iterator it = set.iterator();
                        while (it.hasNext()) {
                            String country11 = it.next().toString();
                            isValidCountry = coun.trim().toLowerCase().equals(country11.trim().toLowerCase());

                            if (isValidCountry) {
                                break;
                            }
                        }

                        if (isValidCountry) {
                            DatabaseClass dataHelp;
                            dataHelp = new DatabaseClass(nam, ag, num, coun);
                            ref.child(num).setValue(dataHelp);

                            final int finalCount = count;
                            final int finalCount1 = count;
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.getValue() != null) {
                                        // will bring to users
                                        for (DataSnapshot snap : snapshot.getChildren()) {

                                            if (snap.getKey().equals(num)) {
                                                String eq = "" + snap.getKey().equals(num);
                                                Log.v("tagvv", " " + eq);
                                                String a = snap.getValue().toString();
                                                Log.v("tagvv", " " + a);

                                                String nu = a.substring(a.indexOf("number=") + 7, a.indexOf(", name="));
                                                String nam = a.substring(a.indexOf("name=") + 5, a.indexOf(", age="));
                                                String agge = a.substring(a.indexOf("age=") + 4, a.indexOf("}"));
                                                String co1 = a.substring(a.indexOf("country=") + 8, a.indexOf(", number="));


                                                if (finalCount1 >= 4) {
                                                    String report = (" It seems you have COVID-19 symptoms \n  \n What to do? \n \n 1.Stay at your home \n 2. Go for checkup \n 3. Wear mask");
                                                    // Toast.makeText(dataActivity.this, "It seems you have COVID-19 symptoms", Toast.LENGTH_LONG).show();
                                                    Intent next = new Intent(dataActivity.this, reportActivity.class);
                                                    next.putExtra("NAME", nam);
                                                    next.putExtra("Age", agge);
                                                    next.putExtra("PNumber", nu);
                                                    next.putExtra("country", co1);
                                                    Log.v("tagvv", " " + nam);
                                                    next.putExtra("report", report);
                                                    Log.v("tagvv", " " + report);

                                                    startActivity(next);

                                                } else if (finalCount1 >= 2 && finalCount1 < 4) {
                                                    String report = "It seems may be you have COVID-19 symptoms \n  Take Medicine If still you are not well then \n 1.Wear Mask and go to doctor \n 2.Stay at home and try to avoid going out  ";
                                                    //Toast.makeText(dataActivity.this, "It seems may be You have COVID-19 symptoms", Toast.LENGTH_LONG).show();
                                                    Intent next = new Intent(dataActivity.this, reportActivity.class);
                                                    next.putExtra("NAME", nam);
                                                    next.putExtra("Age", agge);
                                                    next.putExtra("PNumber", nu);
                                                    next.putExtra("country", co1);
                                                    next.putExtra("report", report);
                                                    startActivity(next);
                                                } else if (finalCount1 < 2) {
                                                    String report = "It seems You don't have COVID-19 symptoms \n 1.You are safe \n 2.Avoid going out and take safety precautions";
                                                    // Toast.makeText(dataActivity.this, "It seems You don't have COVID-19 symptoms", Toast.LENGTH_LONG).show();

                                                    Intent next = new Intent(dataActivity.this, reportActivity.class);
                                                    next.putExtra("NAME", nam);
                                                    next.putExtra("Age", agge);
                                                    next.putExtra("PNumber", nu);
                                                    next.putExtra("country", co1);
                                                    next.putExtra("report", report);
                                                    startActivity(next);

                                                }


                                            }
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        } else {

                            Toast.makeText(dataActivity.this, "Please enter Proper country", Toast.LENGTH_LONG).show();


                        }


                    }


                }
            }

        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String coun = country.getText().toString().trim().toUpperCase();
                if (coun.isEmpty()) {
                    Toast.makeText(dataActivity.this, "Please enter your Country", Toast.LENGTH_LONG).show();
                } else if (coun.contains("0") || coun.contains("1") || coun.contains("2") || coun.contains("3") || coun.contains("4") || coun.contains("5") || coun.contains("6") || coun.contains("7") || coun.contains("8") || coun.contains("9")) {
                    Toast.makeText(dataActivity.this, "Please enter your Country , Not digit!!", Toast.LENGTH_LONG).show();
                } else {
                    Intent country = new Intent(dataActivity.this, stats.class);
                    country.putExtra("country", coun);
                    startActivity(country);

                }


            }
        });
    }
}

