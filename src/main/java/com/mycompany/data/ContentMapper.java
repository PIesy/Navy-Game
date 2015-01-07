package com.mycompany.data;

public interface ContentMapper
{
    public <T> String serialize(T value, Class<T> valueClass) throws Exception;
    public <T> T deserialize(String value, Class<T> valueClass) throws Exception;
}
