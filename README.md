# Автоматизация тестирования API сервиса [Petstore](https://petstore.swagger.io/)

### Стек технологий

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="icons/Intelij_IDEA.svg">
<img width="6%" title="Java" src="icons/Java.svg">
<img width="6%" title="Selenide" src="icons/Selenide.svg">
<img width="6%" title="Selenoid" src="icons/Selenoid.svg">
<img width="6%" title="Allure Report" src="icons/Allure_Report.svg">
<img width="5%" title="Allure TestOps" src="icons/AllureTestOps.svg">
<img width="6%" title="Gradle" src="icons/Gradle.svg">
<img width="6%" title="JUnit5" src="icons/JUnit5.svg">
<img width="6%" title="GitHub" src="icons/GitHub.svg">
<img width="6%" title="Jenkins" src="icons/Jenkins.svg">
<img width="6%" title="Telegram" src="icons/Telegram.svg">
</p>

Вот несколько примеров простых тестов для сайта завода:

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



