package com.airline.airplane_ms.repository;


import com.airline.airplane_ms.model.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Long> {

    List<Airplane> findAllByBusyFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Airplane SET status = false WHERE id = :id")
    void updateStatusById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Airplane SET busy = :busy WHERE id = :id")
    void updateBusyById(@Param("busy") boolean busy,@Param("id") Long id);




}
