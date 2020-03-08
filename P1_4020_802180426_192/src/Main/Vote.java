package Main;

/*
 * Vote Class
 * 
 * This class stores a specific candidate ID and rank given by a voter 
 * inside the Ballot information provided in the Ballot information
 * 
 * @author: Fernando Bermudez (@bermed28)
 * @version 1.0
 * @since 2020-02-27
 */

public class Vote {
	
	private int candidateID;
	private int rank;
	
	public Vote(String candidateID, String rank) {
		
		this.candidateID = Integer.valueOf(candidateID.trim());
		this.rank = Integer.valueOf(rank.trim());
		
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}	
	
}


