package com.example.demoredis;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

@Repository
public class EmployeeRepository {
	private HashOperations hashOperations;
	private RedisTemplate redisTemplate;
	public EmployeeRepository(HashOperations hashOperations, RedisTemplate redisTemplate) {
		super();
		this.hashOperations = hashOperations;
		this.redisTemplate = redisTemplate;
	}
	public void save(Employee employee) {
		hashOperations.put("EMPLOYEE", employee.getId(), employee);
	}

	public List<Employee> findAll() {
		return hashOperations.values("EMPLOYEE");
	}

	public Employee findById(int id) {
		return (Employee) hashOperations.get("EMPLOYEE", id);
	}

	public void update(Employee employee) {
		save(employee);
	}

	public void delete(int id) {
		hashOperations.delete("EMPLOYEE", id);
	}

}
