package ru.croc.javaschool.peopleandprojects.jaxb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;

public class XmlConverter {
    private XmlMapper xmlMapper;


    public String toXml(Object obj) throws JsonProcessingException {
        return getMapper().writeValueAsString(obj);
    }

    public String toClearXml(Object obj) throws JsonProcessingException {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return getMapper().writeValueAsString(obj);
    }

    public <T> T fromXml(String xml, Class<T> type) throws IOException {
        return getMapper().readValue(xml, type);
    }

    private XmlMapper getMapper() {
        if (xmlMapper == null) {
            this.xmlMapper = new XmlMapper();
            this.xmlMapper.setDefaultUseWrapper(false);
            this.xmlMapper.registerModule(new JaxbAnnotationModule());
        }
        return xmlMapper;
    }
}