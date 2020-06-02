package com.sudheer.azure.azuredemoapp.util;

import com.google.gson.Gson;
import com.sudheer.azure.azuredemoapp.annotation.Util;
import com.sudheer.azure.azuredemoapp.beans.StoresBean;
import com.sudheer.azure.azuredemoapp.entity.StoresEntity;
import org.springframework.beans.BeanUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Util
public class DataUtil {

    public void populate(final StoresEntity entity, final StoresBean storesBean) {
        BeanUtils.copyProperties(storesBean, entity, "id");
        entity.setLocation(new Gson().toJson(storesBean.getLocation().toString().replaceAll("\\\\", "")));
        entity.setServices(new Gson().toJson(storesBean.getServices()));
    }

    public static String get(final String getUrl) {
        try {
            Thread.sleep(1000);
            final URL url = new URL(getUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            final int responseCode = connection.getResponseCode();
            System.out.println("GET response-code : " + responseCode);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response.toString());
                return response.toString();
            }
        } catch (Exception e) {
            System.out.println("Exception during get : " + e.getLocalizedMessage());
        }
        return null;
    }

    public static String post(final String postURl, final String body) throws IOException, InterruptedException {
        Thread.sleep(1000);
        final URL url = new URL(postURl);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        int code = con.getResponseCode();
        System.out.println("POST response-code : " + code);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Response: " + response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println("Exception during post : " + e.getLocalizedMessage());
        }
        return null;
    }

    public static void delete(final String deleteUrl) {
        try {
            Thread.sleep(1000);
            final URL url = new URL(deleteUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            final int responseCode = connection.getResponseCode();
            System.out.println("DELETE response-code : " + responseCode);
        } catch (Exception e) {
            System.out.println("Exception during delete : " + e.getLocalizedMessage());
        }
    }
}
