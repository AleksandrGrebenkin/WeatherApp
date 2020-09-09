package com.github.alexandrgrebenkin.weatherapp.ui.event;

import android.location.Address;

public class AddressLoaderEvent {
    private Address address;

    public AddressLoaderEvent(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
