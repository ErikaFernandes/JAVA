package com.erikasf.challenge.model;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erikasf.challenge.error.ErrorLog;
import com.erikasf.challenge.repository.Challenges;

@Service
public class LogicChallenge {

	@Autowired
	private Challenges challenges;
	
	private ErrorLog errorLog;
	
		
public Challenge randomChallenge(Challenge challengeMyRand) {
		
		List<Challenge> challenge = null;
		try {
			challenge = challenges.findAll();
		}catch(Exception e) {
			errorLog.allProcess(errorLog, e);
		}
		
		int index = (new Random()).nextInt(challenge.size());
		challengeMyRand = challenge.get(index);
		
		return challengeMyRand;
		
	}

		public List<Challenge> getChallengeList(List<Challenge> challengeList) {
		
			try {
				challengeList = challenges.findAll();
				
			}catch(Exception e) {
				errorLog.allProcess(errorLog, e);
			}
			return challengeList;
		}
		
		public String saveChallenge(Challenge challenge) {
			try {
			challenges.save(challenge);
			return "Challenge  '" + challenge.getName() +  "' was successfully registered ";
			}catch(Exception e) {
				errorLog.allProcess(errorLog, e);
				return "This Challenge was not registered , please try again later ";
			}
		}
		
		public Challenge getOneChallengeToEdit(Long id) {
			Challenge challenge = new Challenge();
			challenge = challenges.getOne(id);
			return challenge;
		}
		
		public String updateChallenge(Challenge challenge, Long id) {
			challenge.setId(id);
			try {
				challenges.saveAndFlush(challenge);
				return "Challenge  '" + challenge.getName() +  "' updated ";
				}catch(Exception e) {
					errorLog.allProcess(errorLog, e);
					return "This Challenge was not updated , please try again later  ";
			}
		}
		
		public boolean deleteChallenge(Long id) {
			try {
				challenges.deleteById(id);
				return true;
				}catch(Exception e) {
					errorLog.allProcess(errorLog, e);
					return false;
			}
		}
		
		
}
