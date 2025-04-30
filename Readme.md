

### Running Docker container



```bash
docker run -d --name parking-container -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=parking -e POSTGRES_DB=parking -p 5435:5432 postgres:17
```

