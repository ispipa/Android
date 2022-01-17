package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;

public class Pagos extends AppCompatActivity
{
    private String paymentItemClienteSecret;
    private Stripe stripe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);

        //stripe = new Stripe(this, PaymentConfiguration.getInstance(getApplicationContext()).getPublishableKey());
    }


}