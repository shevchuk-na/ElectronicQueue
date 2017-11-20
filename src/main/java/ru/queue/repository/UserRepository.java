package ru.queue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.queue.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findById(Long id);

    User findByUsername(String username);
}
