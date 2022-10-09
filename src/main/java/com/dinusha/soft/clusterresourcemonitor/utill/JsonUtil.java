package com.dinusha.soft.clusterresourcemonitor.utill;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.function.Function;

public interface JsonUtil {

    Function<String, JSONObject> JSON_OBJECT = value -> {
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return object;
    };
}
