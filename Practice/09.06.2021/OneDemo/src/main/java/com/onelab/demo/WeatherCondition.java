package com.onelab.demo;

import camundajar.impl.com.google.gson.Gson;
import camundajar.impl.com.google.gson.reflect.TypeToken;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class WeatherCondition implements JavaDelegate {
    private static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
        return map;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String aPIKey = "0b62bc1ce17879a973eef26b14e836d6";
        String location = "Almaty,kz";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + aPIKey;
        final Double kelvin = 273.15;
        try {
            StringBuilder results = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                results.append(line);
            }
            bufferedReader.close();

            Map<String, Object> res = jsonToMap(results.toString());

            Map<String, Object> mainMap = jsonToMap(res.get("main").toString());

            Double tempObject = (Double) mainMap.get("temp");
            Double temp = tempObject - kelvin;
            int tempRound = (int) Math.round(temp);
            delegateExecution.setVariable("temperature", tempRound);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
