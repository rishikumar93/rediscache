package com.kvinod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

/*
 Explanation of Workflow:
Redis Cache:

When a request is made to the /data/{id} endpoint, the getData() method in DataService is invoked.

It first checks if the requested data exists in Redis cache using redisService.getFromCache(dataId).

If the data is found in Redis, it is returned immediately (cache hit).

If the data is not found in Redis, it falls back to the database (MySQL) by calling dataRepository.findById(dataId).

MySQL:

If the data is not in Redis, it is fetched from MySQL using the repository (DataRepository).

The fetched data is then stored in Redis using redisService.putToCache(data) for future requests.

Redis Expiry:

You can set an expiration time for Redis keys (TTL) to ensure that stale data is removed automatically. You can configure TTL while setting data in Redis like so:

java
Copy
redisTemplate.opsForValue().set("data:" + data.getId(), data, 1, TimeUnit.HOURS);  // TTL of 1 hour
Running the Application:
Start Redis: Make sure your Redis server is running on the default port (6379), or configure it accordingly.

Start MySQL: Ensure your MySQL database (test_db) is running and contains the data_table with appropriate data.

Run the Application: You can run the Spring Boot application by executing:

bash
Copy
mvn spring-boot:run
Now you can access the REST API at http://localhost:8080/data/{id}, and it will first check Redis for the data and fallback to MySQL if the cache is a miss.

Conclusion:
This Java-based implementation using Spring Boot, Redis, and MySQL allows your application to efficiently cache frequently accessed data in Redis. If the data is not found in the cache, it falls back to the database, reducing load on MySQL and improving response time for repeated requests.
*/