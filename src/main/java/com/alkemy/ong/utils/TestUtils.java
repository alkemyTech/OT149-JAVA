package com.alkemy.ong.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class TestUtils {

    /**
     * Matches between json arrays
     *
     * @param responseString given json
     * @param expectedString expected json
     * @return true if match
     */
    public static boolean matchJson(String responseString, String expectedString) {
        try {
            ObjectMapper om = new ObjectMapper();
            JsonNode response = om.readTree(responseString);
            JsonNode expected = om.readTree(expectedString);
            if (ObjectUtils.notEqual(response, expected)) {
                log.error("\u001b[0;31mExpected <" + expectedString + ">\n but was \n<" + responseString + ">." + "\u001b[0m");
                return false;
            } else {
                return true;
            }
        } catch (IOException io) {
            return false;
        }
    }

    /**
     * Matches between json objects
     *
     * @param responseString given json
     * @param expectedString expected json
     * @return true if match
     */
    public static boolean matchJsonArray(String responseString, String expectedString) {
        List<JsonNode> response = parseJsonArray(responseString);
        List<JsonNode> expected = parseJsonArray(expectedString);
        boolean preliminary = preliminaryJsonArrayMatching(response, expected);
        return preliminary && jsonArrayMatching(response, expected);
    }

    private static boolean preliminaryJsonArrayMatching(List<JsonNode> response, List<JsonNode> expected) {
        if (response != null && expected != null) {
            return response.size() == expected.size();
        } else {
            return false;
        }
    }

    private static boolean jsonArrayMatching(List<JsonNode> response, List<JsonNode> expected) {
        for (int i = 0; i < response.size(); ++i) {
            JsonNode expectedJson = expected.get(i);
            JsonNode responseJson = response.get(i);
            if (!expectedJson.equals(responseJson)) {
                log.error("\u001b[0;31mExpected <" + expectedJson + "> but was <" + responseJson + "> (at index " + i + ")." + "\u001b[0m");
                return false;
            }
        }
        return true;
    }

    private static List<JsonNode> parseJsonArray(String jsonString) {
        try {
            return JsonUtils.jsonToGenericObject(jsonString, new TypeReference<>() {});
        } catch (IOException var2) {
            return null;
        }
    }

}
