﻿XML-XSD-validation

1. Переходим в папку с проектом (Xml-Xsd-Validation)
2. Создаем Docker-образ с сервисом (используя Gradle): 
сначала вводим:  chmod u+x ./gradlew
затем:  ./gradlew build docker
3. Запускаем Docker контейнер: docker run -p 80:80 -t com.shustenkov/xmlxsd
4. С помощью Postman отправляем POST запрос по адресу: localhost/validate

![request](/screenshot/postman.PNG)
