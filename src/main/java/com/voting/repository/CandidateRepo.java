package com.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entity.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
	public Candidate findById(int id);
}
