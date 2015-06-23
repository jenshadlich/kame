# crawler-scheduler
##### Build:
```
mvn clean install
```

##### Start server:
```
java -jar target/crawler-service-0.1-SNAPSHOT.jar server config.yaml
```

##### submit a crawl job:
```
curl -s -H "Content-Type: application/json" -X POST -d '{"name":"foo", "seedUrl": "bar"}' http://localhost:9080/submitCrawlJob | jsonpp && echo ""
```
