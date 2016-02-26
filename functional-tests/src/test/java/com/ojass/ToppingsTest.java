package com.ojass;

import com.meterware.httpunit.*;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;

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
        String requestBody= "{\"name\":\"pineapple\",\"qty\":\"10\",\"qtyPerServing\":\"1\",\"pricePerServing\":\"2\"}";
        WebRequest request = new PostMethodWebRequest("http://localhost:8081/pizzarest-functional-tests/toppings",new ByteArrayInputStream(requestBody.getBytes()),"application/json");
        WebResponse response = conversation.getResponse(request);
        assertEquals(201, response.getResponseCode());
    }
}
