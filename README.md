# CarService

## Описание
**CarService** - это pet-проект, реализованный на Java 17 с дополнительным использованием Redis для кэширования и ElasticSearch для логирования. Проект направлен на демонстрацию возможностей современного стека бэкэнда. В проекте используется базовый веб-дизайн с использованием Bootstrap, что обеспечивает простой и функциональный пользовательский интерфейс.

## Технологии
- Java 17
- Spring Boot (включая Spring Data JPA, Spring Security)
- HTML, CSS
- JavaScript
- Maven
- Docker
- PostgreSQL
- Redis
- ElasticSearch
- Thymeleaf

## Установка и запуск
Чтобы установить и запустить **CarService**, выполните следующие действия:

1. Клонируйте репозиторий на вашу машину.
2. Убедитесь, что у вас установлены Java 17 и Maven.
3. Откройте папку `docker-compose` в репозитории. Вы найдете там три файла `.yaml` для Docker контейнеров:
    - `docker-compose-es.yaml` для Elasticsearch
    - `docker-compose-psql.yaml` для PostgreSQL и pgAdmin
    - `docker-compose-redis.yaml` для Redis
4. Запустите эти Docker контейнеры.
5. Скачайте Logstash [по ссылке](https://mirrors.huaweicloud.com/logstash/8.8.0/logstash-8.8.0-windows-x86_64.zip) и распакуйте, желательно не на системный диск.
6. Измените файл `logstash-sample.conf` в папке конфиг Logstash, указав путь к вашему лог-файлу:
    ```conf
    input {
      file {
        path => "Путь_к_вашим_логам/spring.log"
        start_position => "beginning"
      }
    }
    output {
      stdout {
        codec => rubydebug
      }
      elasticsearch {
        hosts => ["http://localhost:9200"]   
        index => "elkdemoindex"
      }
    }
    ```
7. Запустите Logstash, используя команду в директории `bin` Logstash: `./logstash.bat -f ./config/logstash-sample.conf`
8. Проверьте и при необходимости измените следующие строки в файле `application.properties` вашего проекта:
 - `spring.datasource.url = jdbc:postgresql://localhost:49154/testdb` - строка подключения к базе данных PostgreSQL.
    - `spring.datasource.username = admin` - имя пользователя для доступа к базе данных PostgreSQL.
    - `spring.datasource.password = root` - пароль для доступа к базе данных PostgreSQL.
    - `redis.host=localhost` - адрес хоста для подключения к Redis.
    - `redis.port=6379` - порт для подключения к Redis.
    - `logging.file.path=G:\\Путь\\к\\вашим\\логам` - абсолютный путь к папке, где Logstash будет читать логи.
9. После успешного запуска всех контейнеров и Logstash, соберите и запустите проект: `mvn clean install` и `java -jar target/car-service.jar`

## Использование
После успешного запуска **CarService**, пользователи могут войти в аккаунт или создать новый. В зависимости от роли пользователя, доступен следующий функционал:
- Создание, изменение и просмотр предложений.
- Управление информацией о моделях и брендах автомобилей.

Вся функциональность полностью реализована в веб-версии проекта и доступна в соответствии с ролью пользователя. 

Стартовые данные для проекта можно настроить в классе `CommandLineRunnerImpl`, который позволяет предварительно задать необходимые параметры для первоначального использования приложения.

## Лицензия
Лицензия для данного проекта не определена.

## Контакты
Для связи с автором проекта, пожалуйста, посетите [главную страницу профиля](https://github.com/FacelessEmoji) на GitHub.
