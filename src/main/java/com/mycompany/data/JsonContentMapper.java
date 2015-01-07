package com.mycompany.data;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonContentMapper implements ContentMapper
{

    public JsonContentMapper()
    {
        mapper.setSerializationInclusion(Include.NON_EMPTY);
    }
    
    @Override
    public <T> String serialize(T value, Class<T> valueClass) throws JsonProcessingException
    {
        return mapper.writeValueAsString(value);
    }

    @Override
    public <T> T deserialize(String value, Class<T> valueClass) throws Exception
    {
        return mapper.readValue(value, valueClass);
    }

    private final ObjectMapper mapper = new ObjectMapper();
}
