package com.github.alexandrgrebenkin.weatherapp.ui.loader;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.github.alexandrgrebenkin.weatherapp.ui.event.AddressLoaderEvent;
import com.github.alexandrgrebenkin.weatherapp.ui.event.UnknownExceptionEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Locale;

public class AddressLoader {
    public void loadAddress(Context context, String locationName) {
        new Thread(() -> {
            try {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                Address address = geocoder.getFromLocationName(locationName, 1).get(0);
                AddressLoaderEvent event = new AddressLoaderEvent(address);
                EventBus.getDefault().post(event);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                EventBus.getDefault().post(new UnknownExceptionEvent(e));
            }
        }).start();


    }
}
