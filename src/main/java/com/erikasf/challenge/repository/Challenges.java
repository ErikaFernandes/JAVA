package com.erikasf.challenge.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.erikasf.challenge.model.Challenge;

public interface Challenges extends JpaRepository <Challenge, Long>{

}
