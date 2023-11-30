package com.example.java_train.Repositories;

import com.example.java_train.Entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    // JPQL
    @Query(value = """
      select t from Token t inner join Account u\s
      on t.account.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);// lấy ra tất cả các token của người dùng

    Optional<Token> findByToken(String token); // lấy ra 1 token cụ thể
}
