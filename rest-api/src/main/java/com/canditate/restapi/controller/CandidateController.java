package com.canditate.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canditate.restapi.dao.CandidateDao;
import com.canditate.restapi.entity.Candidate;

@RestController
public class CandidateController {

	@Autowired
	private CandidateDao candidateDao;

	
	@GetMapping("/entercandidate")
	public ResponseEntity<String> enterCandidate(@RequestParam String name) {
		Candidate candidate = new Candidate(name);
		return new ResponseEntity<String>(candidateDao.add(candidate), HttpStatus.CREATED);
	}

	@GetMapping("/castvote")
	public String castVote(@RequestParam String name) {
		return candidateDao.castVote(name);
	}

	@GetMapping("/countvote")
	public String countVote(@RequestParam String name) {
		return candidateDao.getCount(name);
	}

	@GetMapping("/listvote")
	public ResponseEntity<List<Candidate>> listvote() {
		ResponseEntity<List<Candidate>> response = new ResponseEntity<List<Candidate>>(candidateDao.getCandidates(),
				HttpStatus.OK);

		return response;
	}

	@GetMapping("/getwinner")
	public List<Candidate> getWinner() {
		return candidateDao.getWinner();
	}

	@GetMapping("/candidates")
	public List<String> getCandidateNames() {
		return candidateDao.getCandidateNames();
	}

}
