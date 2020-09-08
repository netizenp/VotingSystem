package com.voting.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voting.entity.Candidate;
import com.voting.entity.Citizen;
import com.voting.repository.CandidateRepo;
import com.voting.repository.CitizenRepo;

@Controller
public class VotingController {
	
	@Autowired
	CandidateRepo candidateRepo;
	
	@Autowired
	CitizenRepo citizenRepo;
	
	@RequestMapping("/")
	public String doVote() {
		return "vote.html";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam String name, Model model, HttpSession session) {
		
		Citizen citizen = citizenRepo.findByName(name);
		session.setAttribute("citizen", citizen);
		
		if(!citizen.isHasVoted()) {
			List<Candidate> candidates = candidateRepo.findAll();
			model.addAttribute("candidates",candidates);
			return "/performVote.html";
		} else {
			return "/alreadyVoted.html";
		}
	}
	
	@RequestMapping("/voteFor")
	public String voteFor(@RequestParam int id, HttpSession session) {
		Citizen citizen = (Citizen) session.getAttribute("citizen");
		if(!citizen.isHasVoted()) {
			citizen.setHasVoted(true);
			Candidate candidate = candidateRepo.findById(id);
			candidate.setNoOfVotes(candidate.getNoOfVotes() + 1);
			candidateRepo.save(candidate);
			citizenRepo.save(citizen);
			return "voted.html";
		}
		return "/alreadyVoted.html";
	}
}