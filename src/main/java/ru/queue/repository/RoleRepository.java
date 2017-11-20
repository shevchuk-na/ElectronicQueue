package ru.queue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.queue.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

    Role findByName(String name);
}
