package com.erikasf.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erikasf.challenge.model.Challenge;
import com.erikasf.challenge.model.LogicChallenge;
import com.erikasf.challenge.model.Status;
import com.erikasf.challenge.model.Type;

@Controller
public class ChallengeController{

	@Autowired
	private LogicChallenge logic;
	
	
	@GetMapping("/")
	public String indChallenge() {
		
		return "index";
	}
	
	@GetMapping("/mychallenge")
	public String retChallenge(Model model) {
		Challenge challengeMyRand = new Challenge();
		challengeMyRand = logic.randomChallenge(challengeMyRand);
		model.addAttribute("challenges", challengeMyRand);
		
		return "challenge/mychallenge";
	}
	
	@GetMapping("/getchallengelist")
	public String getChallengeList(Model model) {
		List<Challenge> challengeList = null;
		challengeList = logic.getChallengeList(challengeList);
		model.addAttribute("challenges", challengeList);
		return "challenge/listchallenges";
	}
	
	@GetMapping("/edit{id}")
	public String editChallenge(@PathVariable Long id, Model model) {
		Challenge challenge = new Challenge();
		challenge = logic.getOneChallengeToEdit(id);
		model.addAttribute(challenge);
		model.addAttribute("typeChallenge", Type.values());
		model.addAttribute("statusChallenge", Status.values());
		return "challenge/editchallenge";
	}
	
	@PostMapping("/savemychallengebyid{id}")
	public String alterChallenge(@PathVariable Long id, @Valid Challenge challenge, BindingResult result, RedirectAttributes attributes, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("challenge",challenge);
			model.addAttribute("statusChallenge", Status.values());
			model.addAttribute("typeChallenge", Type.values());
			attributes.addFlashAttribute("msg", "This Challenge was not registered , please complete the fields ");
			return "redirect:/edit"+id;
		}
		
		    String msg = logic.updateChallenge(challenge, id);
		
			attributes.addFlashAttribute("msg", msg);
			return "redirect:/edit"+challenge.getId();
	}
	
	@DeleteMapping("/deletebyid{id}")
	public String deleteChallenge(@PathVariable Long id) {
		logic.deleteChallenge(id);
		return "redirect:/getchallengelist";
	}
	
	@GetMapping("/createnewchallenge")
	public String createNewChallenge(Model model) {
		model.addAttribute("challenge", new Challenge());
		model.addAttribute("typeChallenge", Type.values());
		model.addAttribute("statusChallenge", Status.values());
		
		return "challenge/newchallenge";
	}
	
	@PostMapping("/savemychallenge")
	public String save(@Valid Challenge challenge, BindingResult result, RedirectAttributes attributes, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("challenge",challenge);
			model.addAttribute("statusChallenge", Status.values());
			model.addAttribute("typeChallenge", Type.values());
			attributes.addFlashAttribute("msg", "This Challenge was not registered , please complete the fields ");
			return "redirect:/createnewchallenge";
		}
		
		String msg = logic.saveChallenge(challenge);
		
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/createnewchallenge";
		
	
		
	}
	
	
}
