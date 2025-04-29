package com.kvinod.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kvinod.entity.User;
import com.kvinod.repository.UserRepository;
import com.kvinod.services.UserServices;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository repo;
	
	 @Autowired
	 private UserServices dataService;

	@Cacheable(value = "users")
	@GetMapping
	public List<User> get() {
//		log.info("returning list of all users");
		return repo.findAll();
	}

//	@Cacheable(value = "users", key = "#id")
//	@GetMapping("/{id}")
//	public User get(@PathVariable String id) {
////		log.info("returning user for id " + id);
//		return repo.findById(id).get();
//	}

	@PostMapping
	public User save(@RequestBody User user) {
//		log.info("saving user details: " + user.toString());
		repo.save(user);
		return user;
	}
	

	    // Endpoint to fetch data
	    @GetMapping("/{id}")
	    public ResponseEntity<User> getData(@PathVariable("id") int id) {
	    	User data = dataService.getUser(id);
	        if (data == null) {
	            return ResponseEntity.status(404).body(null);
	        }
	        return ResponseEntity.ok(data);
	    }
	
	    /*
	     The @Cacheable annotation in Spring is used to cache the result of a method call based on the parameters passed to it. When you apply @Cacheable to a method, it tells Spring to check if the result for that method with the given parameters is already in the cache. If the result is cached, it will return the cached value instead of executing the method again, which can improve performance by reducing expensive operations like database queries or remote service calls.

When to Use @Cacheable Annotation:
Expensive Operations (e.g., Database Access, Remote Service Calls):

Use @Cacheable when you have methods that perform expensive or time-consuming operations (like fetching data from a database or making a network request), and the results don't change often.

In your example, the repo.findById(id) is likely querying a database to retrieve the user by ID. Caching the result would prevent the same query from being executed multiple times for the same user ID.

Frequently Accessed Data:

If the data returned by a method is accessed frequently, it makes sense to cache it to avoid repeated database queries or computations.

Stable or Infrequently Changing Data:

It’s ideal for data that doesn’t change frequently. For example, if your user data doesn’t update often, caching the result of fetching a user by id would be beneficial.

Enhancing Performance and Reducing Latency:

Cacheable methods can significantly reduce latency by avoiding repeated calls to underlying services or databases.

How @Cacheable Works:
In your example:

java
Copy
@Cacheable(value = "users", key = "#id")
@GetMapping("/{id}")
public User get(@PathVariable String id) {
    log.info("returning user for id " + id);
    return repo.findById(id).get();
}
value = "users": This defines the cache name. You can think of this as a logical name for your cache storage. In this case, you’re naming the cache users.

key = "#id": This defines the key that will be used to store and retrieve the cached result. The #id means that the id parameter from the method will be used as the key for the cache. If you call get("123"), Spring will cache the result under the key "123".

Cache Eviction Consideration:
When data changes: If the data changes frequently (e.g., if you are updating a user), you'll need a mechanism to remove or update the cache. This can be done with annotations like @CacheEvict or @CachePut. For example, if a user is updated, you might want to evict that user’s cached value.

java
Copy
@CacheEvict(value = "users", key = "#id")
public void updateUser(String id, User user) {
    // update the user
}
This would ensure that the cache for the specific user is cleared when the user’s data is updated.

When Not to Use @Cacheable:
For frequently changing data: If the data changes very frequently or in real-time, caching might lead to stale data being returned to the user.

When the result of the method is not idempotent or cannot be consistently reproduced: If the method has side effects or involves non-deterministic behavior (e.g., random values), caching may not make sense.

In Your Case:
Since repo.findById(id) likely performs a database query to retrieve a User, applying @Cacheable will store the result in a cache after the first query. The next time you call this method with the same id, the cached result will be returned, avoiding an additional database query, thus improving performance.

However, ensure that:

The user data doesn't change often, or you'll need cache eviction strategies.

The cache is managed properly to avoid stale data. 
	     */
}
