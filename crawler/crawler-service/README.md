# crawler-service

##### Build:
```
mvn clean install
```

##### Start server:
```
java -jar target/crawler-service-0.1-SNAPSHOT.jar server config.yaml
```

##### POST some url to crawl:
```
curl -s -H "Content-Type: application/json" -X POST -d '{"url":"<url>"}' http://localhost:8080/crawl | jsonpp && echo ""
```