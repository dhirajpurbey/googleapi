package com.stackroute.plasma.googleapi.controller;

import com.stackroute.plasma.googleapi.service.GoogleApiSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.StringReader;

@RestController
@RequestMapping("api/v1")
public class GoogleApiSearchController {
        @Autowired
        GoogleApiSearchService googleApiSearchService;
    public String result;
    private int initial;
    private int finall;
    @GetMapping("/search")
    public String getApi(){
       result = googleApiSearchService.read("java+android", 1, 10);
        JsonParser parser = Json.createParser(new StringReader(result));
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();

            if (event == JsonParser.Event.KEY_NAME) {

                if (parser.getString().equals("htmlTitle")) {
                    JsonParser.Event value = parser.next();

                    if (value == JsonParser.Event.VALUE_STRING)
                        System.out.println("Title (HTML): "
                                + parser.getString());
                }

                if (parser.getString().equals("link")) {

                    JsonParser.Event value = parser.next();

                    if (value == JsonParser.Event.VALUE_STRING)
                        System.out.println("Link: " + parser.getString());
                }

            }

        }

        initial = initial + 10;

        finall++;

        System.out
                .println("**************************************************");


        return result;
    }
}
