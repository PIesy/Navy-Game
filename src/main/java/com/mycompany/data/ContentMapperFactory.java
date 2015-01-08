package com.mycompany.data;

public class ContentMapperFactory
{
    
    public static ContentMapper getMapper(MapperType type)
    {
        ContentMapper mapper = type.getMapper();
        if(mapper == null) {
            throw new IllegalArgumentException("Invalid mapper type");
        }
        return mapper;
    }
    
    public enum MapperType 
    {
        Json(new JsonContentMapper()), Xml(new XmlContentMapper()), None(null);
        
        private MapperType(ContentMapper mapper)
        {
            this.mapper = mapper;
        }
        
        public ContentMapper getMapper()
        {
            return mapper;
        }
        
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
        
        private final ContentMapper mapper;
    }
}
