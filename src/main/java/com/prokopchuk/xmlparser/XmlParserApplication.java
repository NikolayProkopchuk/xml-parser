package com.prokopchuk.xmlparser;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.net.URL;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;

@SpringBootApplication
public class XmlParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlParserApplication.class, args);
    }

    @Bean
    public Validator xmlValidator() throws SAXException {
        URL schemaFile = getClass().getClassLoader().getResource("epaperRequest.xsd");
        SchemaFactory schemaFactory = SchemaFactory
          .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);

        return schema.newValidator();
    }

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }

}
