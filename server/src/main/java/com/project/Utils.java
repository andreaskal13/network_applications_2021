package com.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {

    private static final String API_URL = "https://data.gov.gr/api/v1/query/road_traffic_attica?date_from=2021-02-25&date_to=2021-03-04";
    private static final String TOKEN = "c7ee2462dcdbf2ef109b86c89b958b4f20594e8a";

    public static List<TrafficGov> dataGovApiResponseMap() throws IOException, InterruptedException{

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .setHeader("Authorization","Token " + TOKEN)
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        ObjectMapper mapper = new ObjectMapper();


        var input = response.body();
        List<TrafficGov> trafficGovList = mapper.readValue(response.body(), new TypeReference<List<TrafficGov>>() {
        });

        //trafficGovList.forEach(System.out::println);

        //TODO
        return trafficGovList;
    }


    public static ArrayNode getJsonFromUniqueRoadList(ArrayList<String> uniqueRoads) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode rootNode = mapper.createArrayNode();

        for(int i=0; i< uniqueRoads.size(); i++){
            ObjectNode childNode = mapper.createObjectNode();
            childNode.put("id", i);
            childNode.put("streetName", uniqueRoads.get(i));
            rootNode.add(childNode);
        }
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        return rootNode;
    }


    public static void prettyPrintJsonPls(String input) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Object prettyJson = mapper.readValue(input, Object.class);
        String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prettyJson);
        System.out.println(indented);
    }

}
