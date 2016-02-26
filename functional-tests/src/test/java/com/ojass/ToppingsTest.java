package com.ojass;

import com.meterware.httpunit.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.*;

@SuppressWarnings({ "unchecked" })
public class ToppingsTest {

    @Test
    public void testConnection() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest("http://localhost:8081/pizzarest-functional-tests/toppings");
        WebResponse response = conversation.getResponse(request);
        assertEquals(200, response.getResponseCode());
    }

    @Test
    public void testCreate() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new PostMethodWebRequest("http://localhost:8081/pizzarest-functional-tests/toppings");
        request.setParameter("name", "jalapenos");
        request.setParameter("qty", "100");
        request.setParameter("qtyPerServing", "1");
        request.setParameter("pricePerServing", "1");
        WebResponse response = conversation.getResponse(request);
        assertEquals(200, response.getResponseCode());
        Number toppingId = assertResponse(response);

        request = new GetMethodWebRequest("http://localhost:8081/pizzarest-functional-tests/toppings/" + toppingId);
        response = conversation.getResponse(request);
        assertEquals(200, response.getResponseCode());
        assertResponse(response);
    }

    private Number assertResponse(WebResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.readValue(response.getText(), Map.class);
        Number toppingId = (Number) result.get("id");
        assertNotNull(toppingId);
        assertEquals("jalapenos", result.get("name"));
        assertEquals("100", result.get("qty"));
        return toppingId;
    }

}
