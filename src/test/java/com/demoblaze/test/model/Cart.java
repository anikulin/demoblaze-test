package com.demoblaze.test.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Cart {
    private List<Map<String, Object>> items;

    public Cart(List<Map<String, Object>> list) {
        this.items = list;
    }

    public Integer size() {
        return items.size();
    }

    public Map<String, Product> getItems() {
        Map<String, Product> m = new HashMap<>();
        
        for (Map<String, Object> item: items) {
            String id = item.get(Keys.ID).toString();
            int productId = (int) item.get(Keys.PROD_ID);
            
            m.put(id, new Product(productId));
        }
        
        return m;
    }

    public static Cart fromResponse(String response) throws JsonParseException, JsonMappingException, IOException {
        Map<String,List<Map<String, Object>>> map = new ObjectMapper().readValue( response, 
                new TypeReference<Map<String,List<Map<String, Object>>>>() {});

        List<Map<String, Object>> list = map.get(Keys.ITEMS);
        
        return new Cart(list);
    }
    
}
