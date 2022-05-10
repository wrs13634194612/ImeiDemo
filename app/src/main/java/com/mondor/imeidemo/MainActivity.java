package com.mondor.imeidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //参考网址：https://github.com/gzu-liyujiang/Android_CN_OAID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String androidID = DeviceIdentifier.getAndroidID(this);
        String food = DeviceIdentifier.getWidevineID();
        String cook = DeviceIdentifier.getPseudoID();
        Log.e("TAG", "" + androidID + food + cook);
        /**
         ===  联想
         1: 98205e90a81cf219    a810ec021d17935f97e084ba132bd11032d63d0d9adc58a2ed73872f0adc329e     636465663674240
         ==小米
         2.  3152948386cd2a0d    ac261a4e265b15d75f551bb3f72e040f5f369b90529da1057b53d11cf683be97    638305662874247
         * */
    }
}