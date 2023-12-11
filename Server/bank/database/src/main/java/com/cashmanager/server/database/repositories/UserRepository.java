package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}