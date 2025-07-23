package com.scooteq.scooteqbe.Repository;

import com.scooteq.scooteqbe.Model.Scooter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Long> {
    @Modifying
    @Transactional
    @Query(value = "update scooters SET status = ?1, description = ?2 WHERE id = ?3",nativeQuery = true)
    int updateScooter(String status, String description,int id);
}
