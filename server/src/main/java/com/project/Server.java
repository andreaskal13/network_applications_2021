package com.project;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static com.project.Utils.dataGovApiResponseMap;
import static com.project.Utils.getJsonFromUniqueRoadList;
import static spark.Spark.*;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        port(5000);

        //TODO /roads should return a JSON in the form of
//
//        { id: 1, streetName: 'ΠΑΤΗΣΙΩΝ' },
//        { id: 2, streetName: 'ΑΛΕΞΑΝΔΡΑΣ' },
//        { id: 3, streetName: 'ΘΗΒΩΝ' },
//        { id: 4, streetName: 'ΒΟΥΛΙΑΓΜΕΝΗΣ' },
//        { id: 5, streetName: 'ΦΩΚΑΑ' },

        List<TrafficGov> dataFromGov = dataGovApiResponseMap();
        //create a list with all the road names
        ArrayList<String> roadNames = new ArrayList();
        for(TrafficGov tr: dataFromGov){
            roadNames.add(tr.getRoad_name());
        }
        //list of unique unique road names
        ArrayList<String> uniqueRoads = new ArrayList<String>(new HashSet<String>(roadNames));

        // sort alphabetically the road names
        Collections.sort(uniqueRoads, new Comparator<String>(){
            public int compare(String o1, String o2){
                return o1.compareTo(o2);
            }
        });

        // do whatever with uniqueroads
        ArrayNode jsonResponse = getJsonFromUniqueRoadList(uniqueRoads);
//
        get("/roads", (req, res) -> {



            //TODO



            //TODO map uniqueRoads to JSON like below...


            ObjectMapper mapper = new ObjectMapper();
            ArrayNode rootNode = mapper.createArrayNode();
            ObjectNode childNode1 = mapper.createObjectNode();
            childNode1.put("id", 0);
            childNode1.put("streetName", "ΠΑΤΗΣΙΩΝ");
            rootNode.add(childNode1);
            ObjectNode childNode2 = mapper.createObjectNode();
            childNode2.put("id", 1);
            childNode2.put("streetName", "ΑΛΕΞΑΝΔΡΑΣ");
            rootNode.add(childNode2);

            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            //return rootNode;
            return jsonResponse;
        });

        //TODO /roads/id should return average of countedcars for a road given its id
        // return type: just a double....
        get("/road/:id", (req, res) -> {

            String selectedRoad = uniqueRoads.get(Integer.parseInt(req.params(":id")));

            ArrayList<TrafficGov> listOfPointsWithThisId = new ArrayList<>();
            for(TrafficGov i : dataFromGov){
                if(i.getRoad_name().equals(selectedRoad)){
                    listOfPointsWithThisId.add(i);
                }
            }

            System.out.println(listOfPointsWithThisId.subList(0,10));
            int sumOfCountedCars = 0;
            int numberOfPoints = 0;
            for(TrafficGov i : listOfPointsWithThisId){
                sumOfCountedCars+=i.getCountedcars();
                numberOfPoints+=1;
            }


            double average = sumOfCountedCars/numberOfPoints;

            //return req.params(":id");
            //return "!!" + selectedRoad + "from api";
            return average;
        });
    }
}