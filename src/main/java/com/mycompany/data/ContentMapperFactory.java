package com.mycompany.data;

public class ContentMapperFactory
{
    
    public static ContentMapper getMapper(MapperType type)
    {
        switch (type) {
        case Json:
            return jsonMapper;
        case Xml:
            return xmlMapper;
        default:
            throw new IllegalArgumentException("Invalid mapper type");
        }
    }
    
    public enum MapperType 
    {
        Json, Xml, None;
        
        public static MapperType parseString(String input)
        {
            switch(input){
            case "application/json":
                return Json;
            case "application/xml":
                return Xml;
            }
            return None;
        }
    }
    
    private static final JsonContentMapper jsonMapper = new JsonContentMapper();
    private static final XmlContentMapper xmlMapper = new XmlContentMapper();
}
