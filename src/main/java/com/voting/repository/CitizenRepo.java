package com.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entity.Citizen;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen, Long> {
	public Citizen findByName(String name);
}