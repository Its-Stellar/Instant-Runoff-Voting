package Main;

/**
 * The Candidate Running for President
 * Contains it's name and Candidate ID given when he/she decided to run in the election
 * 
 * @author Fernando J. Bermudez (@bermed28)
 * @version 1.0.0
 * @since 2020-03-01
 * 
 */

public class Candidate {
	
	private String name;
	private int candidateID;
	
	public Candidate(String[] information) {
		
		this.name = information[0];
		this.candidateID = Integer.valueOf(information[1].trim());
	}
	
	public Candidate(String name, int ID) {
		this.name = name;
		this.candidateID = ID;
	}

	public String getName() {
		return name;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void printCandidateInformation() {
		System.out.println("Candidate: " + getName() + ", ID Number: " + getCandidateID());

	}

	
}
