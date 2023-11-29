package com.airline.user_ms.repository;

import com.airline.user_ms.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t inner join _user u on t.user.id = u.id " +
            "where u.id = :userId and (t.expired = false or t.revoked = false )")
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);


}
