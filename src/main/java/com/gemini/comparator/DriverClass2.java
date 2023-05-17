package com.gemini.comparator;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DriverClass2 {
    public static void main(String[] args) {


        JSONArray array1 = new JSONArray("[{\"id\": 1, \"name\": \"John\"}, {\"id\": 2, \"name\": \"Alice\"}]");
        JSONArray array2 = new JSONArray("[{\"id\": 1, \"name\": \"John\"}, {\"id\": 3, \"name\": \"Bob\"}]");
        List<String> primaryKeys = Arrays.asList("id");
        List<Map<String, Object>> mismatches = JsonComparator.compareJsonArrays(array1, array2, primaryKeys);
        for (Map<String, Object> mismatch : mismatches) {
            System.out.println("Mismatch: " + mismatch.get("object1") + " | " + mismatch.get("object2"));

        }
    }
}
