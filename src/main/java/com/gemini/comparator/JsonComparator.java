package com.gemini.comparator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonComparator {
    public static List<Map<String, Object>> compareJsonArrays(JSONArray array1, JSONArray array2, List<String> primaryKeys) {
        List<Map<String, Object>> mismatches = new ArrayList<>();
        for (int i = 0; i < array1.length(); i++) {
            JSONObject obj1 = array1.getJSONObject(i);
            String primaryKey1 = generatePrimaryKey(obj1, primaryKeys);
            boolean matchFound = false;
            for (int j = 0; j < array2.length(); j++) {
                JSONObject obj2 = array2.getJSONObject(j);
                String primaryKey2 = generatePrimaryKey(obj2, primaryKeys);
                if (primaryKey1.equals(primaryKey2)) {
                    matchFound = true;
                    if (!compareJsonObjects(obj1, obj2)) {
                        mismatches.add(createMismatchMap(obj1, obj2));
                    }
                    break;
                }
            }
            if (!matchFound) {
                mismatches.add(createMismatchMap(obj1, null));
            }
        }
        return mismatches;
    }


    private static String generatePrimaryKey(JSONObject obj, List<String> primaryKeys) {
        StringBuilder primaryKey = new StringBuilder();

        for (String key : primaryKeys) {
            if (obj.has(key)) {
                primaryKey.append(obj.get(key).toString());
            }
        }

        return primaryKey.toString();
    }

    private static boolean compareJsonObjects(JSONObject obj1, JSONObject obj2) {
// Implementation of object comparison logic
// Modify this method based on your specific comparison requirements
        return obj1.toString().equals(obj2.toString());
    }

    private static Map<String, Object> createMismatchMap(JSONObject obj1, JSONObject obj2) {
        Map<String, Object> mismatchMap = new HashMap<>();
        mismatchMap.put("object1", obj1);
        mismatchMap.put("object2", obj2);
        return mismatchMap;
    }
}