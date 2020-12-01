package com.example.telefoniapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.resultado);
        //instance of TelephonyManager
        TelephonyManager tele_man = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String nwcountryISO = tele_man.getNetworkCountryIso();
        String SIMCountryISO = tele_man.getSimCountryIso();
        String SIMOperatorName = tele_man.getSimOperatorName();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String SIMVoiceMail = tele_man.getVoiceMailNumber();
        String PhoneType = "";
        int phoneType = tele_man.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                PhoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                PhoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                PhoneType = "NONE";
                break;
            case (TelephonyManager.PHONE_TYPE_SIP):
                PhoneType = "SIP";
                break;
        }
        // true or false for roaming or not
        boolean checkRoaming = tele_man.isNetworkRoaming();
        String data = "Los detalles de la red móvil son: \n";
        data += "\n ISO del país de la red es- " + nwcountryISO;
        data += "\n SIM, el país ISO es - " + SIMCountryISO;
        data += "\n El tipo de red es- " + PhoneType;
        data += "\n Roaming es - " + checkRoaming;
        data += "\n Nombre de la operadora - " + SIMOperatorName;
        data += "\n Numero del correo de voz - " + SIMVoiceMail;
        //Now we'll display the information
        tv.setText(data);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}