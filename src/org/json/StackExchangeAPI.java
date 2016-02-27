/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.json;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;

public class StackExchangeAPI {
    
   String url = "https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=activity&accepted=True&title=java%20security&site=stackoverflow&filter=withbody";
   DefaultHttpClient httpclient = new DefaultHttpClient();
   HttpGet getRequest = new HttpGet(url);
   HttpResponse  httpResponse;
   
   
   
    private static void parseStackExchange(String jsonStr){
        JsonReader reader = null;
        try{
            reader = Json.createReader(new StringReader(jsonStr));
            JsonObject jsonObject = reader.readObject();
            reader.close();
            JsonArray array = jsonObject.getJsonArray("items");
            for (JsonObject result : array.getValuesAs(JsonObject.class)) {
                
                JsonObject ownerObject = result.getJsonObject("owner");
             // int ownerReputation = ownerObject.getInt("reputation");
              //  System.out.println("Reputation:"+ownerReputation);
                int viewCount = result.getInt("view_count");
                System.out.println("View Count :"+viewCount);
                int answerCount = result.getInt("answer_count");
                System.out.println("Answer Count :"+answerCount);
                String link = result.getString("link");
                System.out.println("URL: "+link);
                String title = result.getString("title");
                System.out.println("Title: "+title);
                String body = result.getString("body");
                System.out.println("Body: "+body);
                JsonArray tagsArray = result.getJsonArray("tags");
                StringBuilder tagBuilder = new StringBuilder();
                int i = 1;
                for(JsonValue tag : tagsArray){
                    tagBuilder.append(tag.toString());
                    if(i < tagsArray.size())
                        tagBuilder.append(",");
                    i++;
                }
                
                System.out.println("Tags: "+tagBuilder.toString());
                System.out.println("------------------------------------------");   
            }
             
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
   
    
    
}
