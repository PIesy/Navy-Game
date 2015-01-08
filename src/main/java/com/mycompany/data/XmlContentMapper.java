package com.mycompany.data;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

public class XmlContentMapper implements ContentMapper
{

    @Override
    public <T> String serialize(T value, Class<T> valueClass) throws JAXBException
    {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(valueClass);
        JAXBElement<T> element = new JAXBElement<T>(new QName(valueClass.getSimpleName()), valueClass, value);
        context.createMarshaller().marshal(element, writer);
        return writer.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(String value, Class<T> valueClass) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(valueClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T)unmarshaller.unmarshal(new StringReader(value));
    }

}
