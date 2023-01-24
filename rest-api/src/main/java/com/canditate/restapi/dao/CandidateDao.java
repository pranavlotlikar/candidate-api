package com.canditate.restapi.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.canditate.restapi.entity.Candidate;
import com.canditate.restapi.exceptions.CandidateException;

@Component
public class CandidateDao {

	private List<Candidate> candidates = new ArrayList<>();
	private List<String> names = new ArrayList<>(
			Arrays.asList(new String[] { "Pranav", "Narendra", "Pramod", "Manmohan", "Atal", "Rajiv" }));

	public CandidateDao() {
		for (String name : names) {
			int random = (int) Math.floor(Math.random() * 10);
			candidates.add(new Candidate(name, random));
		}
	}

	public String add(Candidate candidate) {
		if (!isValidName(candidate.getName()))
			return "Please enter a valid name";
		if (names.contains(candidate.getName()))
			return "Candidate already exists";

		candidates.add(candidate);
		names.add(candidate.getName());

		return candidate.getName() + " added!";
	}

	public String castVote(String name) {
		if (!isValidName(name))
			return "Please enter a valid name";

		for (Candidate candidate : candidates) {
			if (name.equals(candidate.getName())) {
				candidate.addVote();
				return String.valueOf(candidate.getVotes());
			}
		}
		throw new CandidateException("Candidate does not exist");
	}

	public String getCount(String name) {
		if (!isValidName(name))
			return "Please enter a valid name";

		for (Candidate candidate : candidates) {
			if (name.equals(candidate.getName()))
				return String.valueOf(candidate.getVotes());
		}
		throw new CandidateException("Candidate does not exist");
	}

	public List<Candidate> getWinner() {
		int max = Collections.max(candidates, (c1, c2) -> c1.getVotes() >= c2.getVotes() ? 1 : -1).getVotes();

		List<Candidate> winningCandidates = new ArrayList<>();
		for (Candidate candidate : candidates) {
			if (max == candidate.getVotes())
				winningCandidates.add(candidate);
		}

		return winningCandidates;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public List<String> getCandidateNames() {
		return names;
	}

	public boolean isValidName(String name) {
		if (name.trim().isEmpty())
			return false;

		String regex = "^[A-Za-z]\\w{4,29}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(name);
		return m.matches();
	}
}
