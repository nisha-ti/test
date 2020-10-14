package com.thrivent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thrivent.model.IrserviceInputJson;
import com.thrivent.model.IrserviceOutputJson;
import com.thrivent.model.Output;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import org.json.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@Service
public class ContentServiceImpl implements ContentService {
    private static final Logger LOGGER = Logger.getLogger(ContentServiceImpl.class.getName());

    private final WebClient webClient;
    private final WebClient webClientapi2;

    @Value("${responseFileFolder}")
    private String responseFileFolder;

    @Value("${rest_url_endpath_api1}")
    private String rest_url_endpath_api1;
    @Value("${rest_url_endpath_api2}")
    private String rest_url_endpath_api2;

    public ContentServiceImpl(@Value("${content-service}") String baseURL,
                              @Value("${user_name}") String user_name,
                              @Value("${password}") String password) {
        System.out.println("usernme  *********" + user_name);
        System.out.println("password  *********" + password);
        this.webClient = WebClient.builder().baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .defaultHeaders(header -> header.setBasicAuth(user_name, password))
                .build();
        this.webClientapi2 = WebClient.builder().baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .defaultHeaders(header -> header.setBasicAuth(user_name, password))
                .build();
    }
    // method to process the json input from postman
    @Override
    public Object processData(IrserviceInputJson requestData) throws IOException,ClassNotFoundException {
        Object returnMsg= "";
        if(!requestData.getFullName_Input().isEmpty() && requestData.getFullName_Input()!=null) {
            System.out.println("fullName"+requestData.getFullName_Input());
            returnMsg = fetchDataFromApi1(requestData);
        }
        else{
            throw new IOException("Missing Value for FULLName");
        }
        return returnMsg;
    }


    public Object fetchDataFromApi1(IrserviceInputJson params) throws IOException,ClassNotFoundException {
        LOGGER.info("Entered getResult Method");
        String returnMsg = "";
        String personid = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object result = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path(rest_url_endpath_api1)
                            .queryParam("Data.FullName_Input", params.getFullName_Input())
                            .queryParam("Data.AddressLine1_Input", params.getAddressLine1_Input())
                            .queryParam("Data.City_Input", params.getCity_Input())
                            .queryParam("Data.StateProvince_Input", params.getState())
                            // .queryParam("Data.PostalCode", params.getpo())
                            .queryParam("Data.Email4_Input", params.getEmail4_Input())
                            .queryParam("Data.Email2_Input", params.getEmail2_Input())
                            .queryParam("Data.recid_input", params.getCity_Input())
                            //  .queryParam("Data.recid_input", params.getFullname_input())
                            .build())
                    .header("x-api-key", "PfSsfPiHuU39VRyOpcZtxBSEcha9bOK8X7voGCU4")
                    .header("Content-Type", "application/json").exchange().block().bodyToMono(Object.class).block();

            returnMsg = mapper.writeValueAsString(result);
            System.out.println("*******************" + returnMsg);

            JSONObject output = new JSONObject(returnMsg);
            System.out.println("after json object ************");
            JSONObject obj = output.getJSONArray("Output").getJSONObject(0);
            System.out.println("extracted output ************");
            JSONArray arr = obj.getJSONArray("user_fields");
            if(obj.has("PersonIds")) {
                personid = obj.getString("PersonIds");
            }
            Map<String, String> map = new HashMap<>();
            System.out.println("size  of  arr **********" + arr.length());
            for (int i = 0; i < arr.length(); i++) {
                System.out.println("inside for loop **********");
                map.put(arr.getJSONObject(i).getString("name"),
                        arr.getJSONObject(i).getString("value"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("************before  converted to model api 1");
        IrserviceOutputJson params2=mapper.readValue(returnMsg,IrserviceOutputJson.class);
        System.out.println("************output converted to model api 1");
        if (personid != null && params2 !=null) {
           // return fetchDataFromApi2(params2.getOutput().get(0)) + "Person_id_exists";
            return fetchDataFromApi2(params2.getOutput().get(0));

        }
        else {
            throw new IOException("Person_id_not_exists");
            //return returnMsg + "Person_id_not_exists";
        }
    }

    //Api2
    public Object fetchDataFromApi2(Output params) throws IOException,ClassNotFoundException {
        LOGGER.info("Entered getResult Method");
        String returnMsg = "";
        Object output = new JSONObject();
        String personid = null;
        try {
            Object result = webClientapi2.get()
                    .uri(uriBuilder -> uriBuilder.path(rest_url_endpath_api2)
                            .queryParam("Data.FullName_Input", params.getFullName_Input())
                            .queryParam("Data.AddressLine1_Input", params.getAddressLine1_Input())
                            .queryParam("Data.City_Input", params.getCity_Input())
                            .queryParam("Data.StateProvince_Input", params.getStateProvince_Input())
                            // .queryParam("Data.PostalCode", params.getpo())
                            .queryParam("Data.Email4_Input", params.getEmail4_Input())
                            .queryParam("Data.Email2_Input", params.getEmail2_Input())
                            .queryParam("Data.recid_input", params.getCity_Input())
                            //  .queryParam("Data.recid_input", params.getFullname_input())
                            .build())
                    .header("x-api-key", "PfSsfPiHuU39VRyOpcZtxBSEcha9bOK8X7voGCU4")
                    .header("Content-Type", "application/json").exchange().block().bodyToMono(Object.class).block();
            ObjectMapper mapper = new ObjectMapper();
            returnMsg = mapper.writeValueAsString(result);
            output = result;
            System.out.println("prettyJson **********" + returnMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}