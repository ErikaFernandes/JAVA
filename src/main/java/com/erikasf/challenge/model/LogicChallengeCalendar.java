package com.erikasf.challenge.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erikasf.challenge.error.ErrorLog;
import com.erikasf.challenge.repository.ChallengesCalendar;


@Service
public class LogicChallengeCalendar {

	@Autowired
	private ChallengesCalendar challengesCalendar;
	
	private ErrorLog errorLog;
	
	public void saveChallengeCalendar(Long id, String name) {
		ChallengeCalendar cCalendar = new ChallengeCalendar();
		cCalendar.setChallenge(id);
		cCalendar.setName(name);
		cCalendar.setStatus(StatusChallengeCalendar.PROGRESS);
		cCalendar.setDate(new Date(System.currentTimeMillis()));
		try {
			challengesCalendar.save(cCalendar);
			System.out.println("calendar saved");
		}catch(Exception e) {
			errorLog.allProcess(errorLog, e);
		}
	}
	
	public List<ChallengeCalendar> getChallengeTodayList(List<ChallengeCalendar> cCalendar){
		
		try {
			cCalendar = challengesCalendar.findByDate(new Date(System.currentTimeMillis()));
			
		}catch(Exception e) {
			errorLog.allProcess(errorLog, e);
		}
		return cCalendar;
	}
	
	public ChallengeCalendar alterStatus(Long id, String name, Date date, Long idch) {
		ChallengeCalendar ch = new ChallengeCalendar();
		ch.setId(id);
		ch.setName(name);
		ch.setDate(date);
		ch.setChallenge(idch);
		ch.setStatus(StatusChallengeCalendar.DONE);
		challengesCalendar.saveAndFlush(ch);
		return ch;
	}
	
	
}
