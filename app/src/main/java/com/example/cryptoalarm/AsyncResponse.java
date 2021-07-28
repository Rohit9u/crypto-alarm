package com.example.cryptoalarm;

import java.util.List;

import database.coin_model;

public interface AsyncResponse {
    public void getResponse(List<coin_model> list);
}
