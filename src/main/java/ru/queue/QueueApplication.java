package ru.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.queue.domain.security.Role;
import ru.queue.repository.RoleRepository;

@SpringBootApplication
public class QueueApplication implements CommandLineRunner{

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(QueueApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        if(roleUser == null) {
            roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleUser.setRoleId(1);
            roleRepository.save(roleUser);
        }
    }
}
