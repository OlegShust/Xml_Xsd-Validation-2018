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

/**
 * RestController -прослушивания POST запросов и дальнейшая валидация
 */
@org.springframework.web.bind.annotation.RestController
public class GreetingController {
    /**
     * Одидание POST запроса от клиента
     * @param xml - XML файл (получаем из POST запроса)
     * @param xsd - XSD файл (получаем из POST запроса)
     * @return возвращает 'Is valid!' - если валидация прошла успешно или 'Is invalid!'- если валидация прошла неуспешно
     */
    @PostMapping("/validate")
    public String validate(@RequestParam("xml") MultipartFile xml,
                           @RequestParam("xsd") MultipartFile xsd) {

        try {

            //Поиск и создание экземпляра фабрики для языка XML Schema
            SchemaFactory factory =
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            //Компиляция схемы
            Schema schema = factory.newSchema(new StreamSource(xsd.getInputStream()));

            //Создание валидатора для схемы
            Validator validator = schema.newValidator();

            //Разбор документа (проверяемого)
            Source source = new StreamSource(xml.getInputStream());

            //Валидация документа
            validator.validate(source);

            //Если XML соответсвует XSD, то "Is valid!"
            return "Is valid!";

            //Если XML не соответсвует XSD, то "Is invalid!"
        } catch (Exception e) {
            return "Is invalid!";
        }
    }
}
