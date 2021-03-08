package com.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

class App {

    private static final String API_URL = "https://data.gov.gr/api/v1/query/road_traffic_attica?date_from=2021-02-25&date_to=2021-03-04";
    private static final String TOKEN = "c7ee2462dcdbf2ef109b86c89b958b4f20594e8a";


    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .setHeader("Authorization","Token " + TOKEN)
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        ObjectMapper mapper = new ObjectMapper();

        //pretty print plsss!!
//        var input = response.body();
//        Object prettyJson = mapper.readValue(input, Object.class);
//        String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prettyJson);
//        System.out.println(indented);
        ////end pretty print

        List<TrafficGov> trafficGovList = mapper.readValue(response.body(), new TypeReference<List<TrafficGov>>() {
        });

        //trafficGovList.forEach(System.out::println);

        Collections.sort(trafficGovList, new Comparator<TrafficGov>(){
            public int compare(TrafficGov o1, TrafficGov o2){
                return o1.getRoad_name().compareTo(o2.getRoad_name());
            }
        });

        //trafficGovList.forEach(System.out::println);

        ArrayList<String> roadNames = new ArrayList();
        for(TrafficGov tr: trafficGovList){
            roadNames.add(tr.getRoad_name());
        }

        System.out.println(roadNames);
        List<String> uniqueRoads = new ArrayList<String>(new HashSet<String>(roadNames));
        System.out.println(uniqueRoads);

        Collections.sort(uniqueRoads, new Comparator<String>(){
            public int compare(String o1, String o2){
                return o1.compareTo(o2);
            }
        });

        System.out.println(uniqueRoads);

        System.out.println(uniqueRoads.get(0));


        System.out.println("Hello world!");




    }
}
