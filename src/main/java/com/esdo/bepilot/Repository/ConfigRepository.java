package com.esdo.bepilot.Repository;

import com.esdo.bepilot.Model.Entity.Config;
import com.esdo.bepilot.Model.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
    List<Config> findAllByOrderById();
}
