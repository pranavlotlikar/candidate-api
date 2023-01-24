package com.canditate.restapi.entity;

public class Candidate {
	
	private String name;
	private int votes;
	
	public Candidate(String name) {
		this.name = name;
	}
	
	public Candidate(String name, int votes) {
		this.name = name;
		this.votes = votes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVotes() {
		return votes;
	}

	public void addVote() {
		this.votes += 1;
	}

	@Override
	public String toString() {
		return "Candidate [name=" + name + ", votes=" + votes + "]";
	}
}
