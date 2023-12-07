# modsen-library

Этапы запуска проекта

## 1)Клонирование репозитория

```bash
git clone https://github.com/poramental/modsen-library.git
```
## 2)Открыть проект

## 3)Создание базы данных
```
docker compose -f compose-env.yaml up -d
```

### Проверить в каждом сервисе соответствие username и password
```
spring.datasource.username=admin
spring.datasource.password=admin
```
### Возможен баг с flywaydb в security service : Не создается миграция с таблицей app_user. Если такой баг присутствует - сделать ее вручную)

## 4)Запустить проект

Запускать по порядку

### 1)Запустить EurekaServerApplication
### 2)Запустить ApiGatewayApplication
### 3)Запустить SuecurityServiceApplication
### 4)Запустить BookRecordServiceApplication
### 5)Запустить LibraryServiceApplication
