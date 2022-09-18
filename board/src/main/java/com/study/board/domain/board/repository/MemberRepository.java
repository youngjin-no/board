package com.study.board.domain.board.repository;

import com.study.board.domain.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Override
    Optional<Member> findById(String id);
}
