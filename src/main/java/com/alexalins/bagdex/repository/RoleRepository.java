package com.alexalins.bagdex.repository;

import com.alexalins.bagdex.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long> {
}
