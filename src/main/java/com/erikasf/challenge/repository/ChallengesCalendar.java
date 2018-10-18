package com.erikasf.challenge.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erikasf.challenge.model.ChallengeCalendar;

public interface ChallengesCalendar extends JpaRepository <ChallengeCalendar, Long> {

	List<ChallengeCalendar> findByDate(Date date);
	
}
