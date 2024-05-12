package com.airline.booking_ms.repository;

import com.airline.booking_ms.model.entity.Ticket;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    @Query("SELECT t FROM Ticket t WHERE t.userId = :userId")
    List<Ticket> findAllByUserIdCheck(@Param("userId") Long userId);


    @Query("SELECT t FROM Ticket t WHERE t.ticketId = :ticketId and t.userId = :userId")
    Optional<Ticket> findByIdAndUserIdCheck(@Param("ticketId") Long ticketId, @Param("userId") Long userId);



}
