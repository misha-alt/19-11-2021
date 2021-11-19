package com;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ticket {
    public static void main(String[] args) {

        long id = Long.parseLong(args[1]);
        String qwery = "http://jsonplaceholder.typicode.com/posts/"+id;

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(qwery).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);
            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");

                }
                System.out.println(sb.toString());
                Article article = Parser.fromJson(sb.toString());
                System.out.println( article.getId());
                System.out.println(article.getBody());
                article.toString();
                //parse(sb.toString());
                //com.Parser par = new com.Parser();
               // System.out.println(par.toString());

            } else {
                System.out.println("fail" + connection.getResponseCode() + " " + connection.getResponseMessage());
            }

        } catch (Throwable cause) {
            cause.printStackTrace();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        //JsonNode  node = new com.Parser(Url1.Quas());
    }
    public static String parse(String responseBody){
        JSONArray album = new JSONArray(responseBody);
        for(int i =0; i<album.length(); i++){
            JSONObject album1 = album.getJSONObject(i);
            int id = album1.getInt("id");
            int userId = album1.getInt("userId");
            String title = album1.getString("title");
            System.out.println("[Article]"+id+","+ userId +", "+title);
        }
        return null;
    }

}
