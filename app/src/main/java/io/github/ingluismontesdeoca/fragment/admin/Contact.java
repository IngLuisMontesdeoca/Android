package io.github.ingluismontesdeoca.fragment.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.github.ingluismontesdeoca.fragment.R;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toast.makeText(Contact.this, "Created" , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(Contact.this, "Stoped" , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(Contact.this, "Resumed" , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(Contact.this, "Paused" , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(Contact.this, "Destroyed" , Toast.LENGTH_SHORT).show();
    }



    public void actionSend(View view){
        EditText _eName = (EditText) view.findViewById(R.id.contact_name);
        EditText _enum = (EditText) view.findViewById(R.id.contact_number);
        EditText _emil = (EditText) view.findViewById(R.id.contact_email);
        EditText _eMsg = (EditText) view.findViewById(R.id.contact_message);
        //Toast.makeText(Contact.this, _eName.getText() + "," + _enum.getText() + "," + _emil.getText() + "," + _eMsg.getText(),Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void actionCancel(View view){
        this.finish();
    }
}

