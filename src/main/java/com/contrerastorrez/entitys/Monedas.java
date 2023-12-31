package com.contrerastorrez.entitys;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Monedas {
    public static final String [] MONEDAS = {"DOP","USD","EUR","JPY","KRW"};
    public double convertir(String from, String to, double amount) throws MalformedURLException, IOException {
        String url_str = "https://api.exchangerate.host/convert?from="+ from +"&to="+ to + "&amount=" + amount;
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        String req_result = jsonobj.get("result").getAsString();
        request.disconnect();
        return Double.parseDouble(req_result);
    }

}
