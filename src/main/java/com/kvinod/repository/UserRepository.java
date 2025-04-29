package com.kvinod.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kvinod.entity.User;

@Repository
//public interface UserRepository extends MongoRepository<User, String> {
public interface UserRepository extends JpaRepository<User, String> {

	public List<User> findByCity(String city);
	public Optional<User> findById(String id);
}
