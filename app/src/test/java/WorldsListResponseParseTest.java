import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import ua.kulku.xyrality.data.model.WorldsListResponse;

@RunWith(JUnit4.class)
public class WorldsListResponseParseTest {
    private static final String JSON = "{\n" +
            " \"serverVersion\": \"1.0.\",\n" +
            " \"allAvailableWorlds\": [\n" +
            " {\n" +
            " \"id\": \"118\",\n" +
            " \"language\": \"de\",\n" +
            " \"url\": \"http://backend2.lordsandknights.com/XYRALITY/WebObjects/LKWorldServerDE-15.woa\",\n" +
            " \"country\": \"DE\",\n" +
            " \"worldStatus\": {\n" +
            " \"id\": 3,\n" +
            " \"description\": \"online\"\n" +
            " },\n" +
            " \"mapURL\": \"http://maps2.lordsandknights.com/v2/LKWorldServer-DE-15\",\n" +
            " \"name\": \"Deutsch 15 (empfohlen)\"\n" +
            " }\n" +
            " ]}";

    @Test
    public void parse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(JSON, WorldsListResponse.class);
    }
}