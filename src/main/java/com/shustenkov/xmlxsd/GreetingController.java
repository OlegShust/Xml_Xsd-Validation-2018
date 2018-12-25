package com.shustenkov.xmlxsd;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;


@org.springframework.web.bind.annotation.RestController
public class GreetingController {

    @PostMapping("/validate")
    public String validate(@RequestParam("xml") MultipartFile xml,
                           @RequestParam("xsd") MultipartFile xsd) {

        try {

            //Поиск и создание экземпляра фабрики для языка XML Schema
            SchemaFactory factory =
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            Schema schema = factory.newSchema(new StreamSource(xsd.getInputStream()));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xml.getInputStream());

            //Валидация документа
            validator.validate(source);

            //Валидация документа
            return "Is valid!";

        } catch (Exception e) {
            return "Is invalid!";
        }
    }
}

