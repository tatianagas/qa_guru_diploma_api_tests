# Автоматизация тестирования API сервиса [Petstore](https://petstore.swagger.io/)

### Стек технологий

<p align="center">
    <a href="https://www.jetbrains.com/idea" title="IntelliJ IDEA">
        <img width="6%" src="icons/Intelij_IDEA.svg">
    </a>
    <a href="https://www.java.com" title="Java">
        <img width="6%" src="icons/Java.svg">
    </a>
    <a href="https://rest-assured.io/" title="Selenide">
        <img width="6%" src="icons/Rest-Assured.png">
    </a>
    <a href="https://docs.qameta.io/allure" title="Allure Report">
        <img width="6%" src="icons/Allure_Report.svg">
    </a>
    <a href="https://gradle.org" title="Gradle">
        <img width="6%" src="icons/Gradle.svg">
    </a>
    <a href="https://junit.org/junit5" title="JUnit5">
        <img width="6%" src="icons/JUnit5.svg">
    </a>
    <a href="https://github.com" title="GitHub">
        <img width="6%" src="icons/GitHub.svg">
    </a>
    <a href="https://www.jenkins.io" title="Jenkins">
        <img width="6%" src="icons/Jenkins.svg">
    </a>
    <a href="https://telegram.org" title="Telegram">
        <img width="6%" src="icons/Telegram.svg">
    </a>
</p>

Примеры автотестов:

- [x] Создание нового питомца
- [x] Поиск питомцев по статусу
- [x] Успешный поиск питомца по ID
- [x] Поиск несуществующего питомца по ID
- [x] Обновление данных существующего питомца
- [x] Удаление питомца по ID

Запуск тестов из командной строки:

```
gradle clean test
```

Отчетность о прогоне тестов в Telegram позволит оперативно отслеживать потенциальные и уже существующие проблемы на
сайте

### Пример такого отчета в Telegram:

<img src="/images/telegram.jpg" width="300" height="270">

### Пример [Allure-отчета](https://jenkins.autotests.cloud/job/qa_guru_diploma_api_tests/allure/):

<img src="/images/allure.jpg" width="600">

### Прогон автотестов в [Jenkins](https://jenkins.autotests.cloud/job/qa_guru_diploma_api_tests/):

<img src="/images/jenkins.jpg" width="600">



