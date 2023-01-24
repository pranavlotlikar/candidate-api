package com.canditate.restapi.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.canditate.restapi.entity.Candidate;

@Component
public class CandidateDao {
	
	private List<Candidate> candidates = new ArrayList<>();
	private List<String> names = new ArrayList<>(Arrays.asList(new String[] {"Pranav", "Narendra", 
			"Pramod", "Manmohan", "Atal", "Rajiv"}));
	
	public CandidateDao() {
		for (String name : names) {
			int random = (int) Math.floor(Math.random() * 10);
			candidates.add(new Candidate(name, random));
		}
	}

	public String add(Candidate candidate) {
		if (names.contains(candidate.getName()))
			return "Candidate already exists";
		
		candidates.add(candidate);
		names.add(candidate.getName());
		return candidate.getName() + " added!";
	}
	
	public void castVote(String name) {
		for (Candidate candidate : candidates) {
			if (name.equals(candidate.getName())) {
				candidate.addVote();
				break;
			}
		}
	}
	
	public List<Candidate> getCandidates() {
		return candidates;
	}
	
	public int getCount(String name) {
		for (Candidate candidate : candidates) {
			if (name.equals(candidate.getName()))
				return candidate.getVotes();
		}
		return 0;
	}

	public List<Candidate> getWinner() {
		int max = Collections.max(candidates, (c1, c2) -> 
		c1.getVotes() >= c2.getVotes() ? 1 : -1).getVotes();
		
		List<Candidate> winningCandidates = new ArrayList<>();
		for (Candidate candidate : candidates) {
			if (max == candidate.getVotes())
				winningCandidates.add(candidate);
		}
		
		return winningCandidates;
	}
}
