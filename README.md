XML-XSD-validation

1. Переходим в папку с проектом (Xml-Xsd-Validation)
2. Создаем Docker-образ с сервисом (используя Gradle): ./gradlew build docker
3. Запускаем Docker контейнер: docker run -p 80:80 -t shustenkov/xmlxsd
4. С помощью Postman отправляем POST запрос по адресу: localhost/validate
