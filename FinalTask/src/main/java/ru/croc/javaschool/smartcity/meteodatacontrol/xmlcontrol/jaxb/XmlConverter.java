package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.jaxb;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;

public class XmlConverter {
    private XmlMapper xmlMapper;

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
