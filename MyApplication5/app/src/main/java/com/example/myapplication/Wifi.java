package com.example.myapplication;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Wifi extends AppCompatActivity {
    WifiManager modem = null;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi);

        final ToggleButton btn = (ToggleButton)findViewById(R.id.toggleButton);
        btn.setOnClickListener(v -> {
            if(btn.isChecked()){
                wifiAc();}
            else {
                wifiKapat();
            }
        });
    }

    private void wifiKapat(){
        modem = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(modem.getWifiState()==WifiManager.WIFI_STATE_ENABLED){
            modem.setWifiEnabled(false);
        }
        else if (modem.getWifiState()==WifiManager.WIFI_STATE_DISABLED){
            Toast.makeText(Wifi.this,"Kapalı", Toast.LENGTH_LONG).show();
        }
    }

    private void wifiAc(){
        modem = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(modem.getWifiState()==WifiManager.WIFI_STATE_DISABLED){
            modem.setWifiEnabled(true);
        }
        else if (modem.getWifiState()==WifiManager.WIFI_STATE_ENABLED){
            Toast.makeText(Wifi.this,"Açık", Toast.LENGTH_LONG).show();
        }
    }
}
