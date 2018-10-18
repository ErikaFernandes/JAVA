package com.erikasf.challenge.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erikasf.challenge.model.ChallengeCalendar;
import com.erikasf.challenge.model.LogicChallengeCalendar;
 
@Controller
public class ChallengeCalendarController implements ErrorController{

	@Autowired
	private LogicChallengeCalendar logic;
	
	
	@GetMapping("/saveCalendarChallenge/{id}/{name}")
	public String saveThisChallengeCalendar(@PathVariable  Long id, @PathVariable String name, Model model, RedirectAttributes attributes) {
		logic.saveChallengeCalendar(id, name);
		attributes.addFlashAttribute("msg", "Challenge " + id + " saved at Calendar");
		return "redirect:/getTodayList"; 
	}
	
	
	@GetMapping("/getTodayList")
	public String getTodayList(Model model) {
		List<ChallengeCalendar> challengeTodayList = null;
		challengeTodayList = logic.getChallengeTodayList(challengeTodayList);
		model.addAttribute("challengesToday", challengeTodayList);
		
		return "challenge/listtodaychallenge";
	}
	
	@GetMapping("/alterStatusCalendarChallenge/{id}/{name}/{date}/{idch}")
	public String alterStatus(@PathVariable  Long id, @PathVariable String name, @PathVariable  Date date, @PathVariable Long idch, RedirectAttributes attributes) {
		logic.alterStatus(id, name, date, idch);
		attributes.addFlashAttribute("msg", "Status " + name + " updated");
		
		return "redirect:/getTodayList";
	}


	@GetMapping("/error")
    public String error() {
		return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
	
}
