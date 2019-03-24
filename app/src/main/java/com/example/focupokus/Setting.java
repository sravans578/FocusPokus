package com.example.focupokus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.zip.Inflater;

public class Setting extends DialogFragment {

    private Switch soundSwitch;
    private Switch vibrateSwitch;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor meditor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_settings,container,false);
        soundSwitch=view.findViewById(R.id.soundSwitch);
        vibrateSwitch=view.findViewById(R.id.vibrateSwitch);
        mPreference= PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

        meditor=mPreference.edit();

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                meditor.putBoolean("soundSwitchValue",soundSwitch.isChecked());
                soundSwitch.setChecked(soundSwitch.isChecked());
                meditor.commit();
                boolean isMusic= mPreference.getBoolean("soundSwitchValue",true);
                Log.d("boolean", "boolean"+isMusic);
                //((start)getActivity()).sound=soundSwitch.isChecked();

            }
        });

        vibrateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                meditor.putBoolean("vibrateSwitchValue",vibrateSwitch.isChecked());
                vibrateSwitch.setChecked(vibrateSwitch.isChecked());
                meditor.commit();


            }
        });



        return view;
    }
}
