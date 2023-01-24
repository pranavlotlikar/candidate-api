package com.canditate.restapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import com.canditate.restapi.dao.CandidateDao;
import com.canditate.restapi.entity.Candidate;
import com.canditate.restapi.exceptions.CandidateException;

public class CandidateDaoTest {

	@Autowired
	CandidateDao dao;

	@BeforeEach
	public void setup() {
		dao = new CandidateDao();
	}

	/*** ENTER CANDIDATE API TESTS ***/

	@Test
	public void testEnterCandidateAdd() {
		Candidate candidate = new Candidate("Parikar");
		String expectedMssg = candidate.getName() + " added!";
		String actualMssg = dao.add(candidate);

		assertEquals(expectedMssg, actualMssg);
		assertTrue(dao.getCandidates().contains(candidate));
	}

	@Test
	public void testEnterCandidateExisting() {
		Candidate candidate = new Candidate("Pranav");
		String expectedMssg = "Candidate already exists";
		String actualMssg = dao.add(candidate);

		assertEquals(expectedMssg, actualMssg);
	}

	@Test
	public void testEnterCandidateInvalid() {
		Candidate invalid1 = new Candidate("   ");
		Candidate invalid2 = new Candidate("213432dv f");

		String expectedMssg = "Please enter a valid name";
		String actualMssg1 = dao.add(invalid1);
		String actualMssg2 = dao.add(invalid2);

		assertEquals(expectedMssg, actualMssg1);
		assertEquals(expectedMssg, actualMssg2);
	}

	/*** CAST VOTE API TESTS ***/

	@Test
	public void testCastVoteIncreaseBy1() {
		Candidate existingCandidate = dao.getCandidates().get(0);
		int oldVotes = existingCandidate.getVotes();
		int latestVotes = Integer.valueOf(dao.castVote(existingCandidate.getName()));

		assertTrue(latestVotes == (oldVotes + 1));
	}

	@Test
	public void testCastVoteInvalid() {
		String emptyName = "";
		String invalidName = "33t4t5bgf";

		String expectedMssg = "Please enter a valid name";
		String actualMssg1 = dao.castVote(emptyName);
		String actualMssg2 = dao.castVote(invalidName);

		assertEquals(expectedMssg, actualMssg1);
		assertEquals(expectedMssg, actualMssg2);
	}

	@Test
	public void testCastVoteNotExists() {
		String nonExistingName = "Babar";
		assertThrows(CandidateException.class, () -> dao.castVote(nonExistingName));
	}

	/*** COUNT VOTE API TESTS ***/

	@Test
	public void testCountVoteLatest() {
		Candidate existingCandidate = dao.getCandidates().get(2);
		int currentVotes = existingCandidate.getVotes();
		int latestVotes = Integer.valueOf(dao.getCount(existingCandidate.getName()));

		assertEquals(currentVotes, latestVotes);
	}

	@Test
	public void testCountVoteInvalid() {
		String emptyName = "";
		String invalidName = "33t4t5bgf";

		String expectedMssg = "Please enter a valid name";
		String actualMssg1 = dao.castVote(emptyName);
		String actualMssg2 = dao.castVote(invalidName);

		assertEquals(expectedMssg, actualMssg1);
		assertEquals(expectedMssg, actualMssg2);
	}

	@Test
	public void testCountVoteNotExists() {
		String nonExistingName = "Babar";
		assertThrows(CandidateException.class, () -> dao.castVote(nonExistingName));
	}

	/*** LIST VOTE API TEST ***/
	@Test
	public void testListVote() {
		List<Candidate> candidates = dao.getCandidates();
		assertTrue(candidates != null, "Fetched Candidates");
	}

	/*** GET WINNER API TESTS **/

	@Test
	public void testGetWinner() {
		int max = 0;
		for (Candidate candidate : dao.getCandidates()) {
			if (candidate.getVotes() > max)
				max = candidate.getVotes();
		}

		List<Candidate> winner = dao.getWinner();

		if (winner.size() == 1)
			assertEquals(max, winner.get(0).getVotes());

		else {
			for (Candidate w : winner)
				assertEquals(max, w.getVotes());
		}
	}

//	
//	@Test
//	public void testAddExistingCandidate() {
//		assertEquals("Candidate already exists", dao.add(new Candidate("Pranav")));
//	}

}
