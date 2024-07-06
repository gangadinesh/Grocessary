package com.grocery.grocery.repository;

import com.grocery.grocery.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepo extends JpaRepository<Grocery,Long> {
}
