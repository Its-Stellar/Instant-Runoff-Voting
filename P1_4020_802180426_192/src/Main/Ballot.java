package Main;

import DataStructures.List.ArrayList;
import DataStructures.Sets.Set;
/*
 * The Ballot
 * 
 * Stores all the votes casted by one person who votes in the Poor Harbor Election
 * 
 * @author: Fernando J. Bermudez
 * @version: 2.0.0
 * @since: 2020-03-01
 */
public class Ballot{

	private int ballotNumber;
	private ArrayList<Vote> castedVotes;
	private boolean validBallot;

	public Ballot(String[] ballotInformation) {

		castedVotes = new ArrayList<>();

		this.ballotNumber = Integer.parseInt(ballotInformation[0].trim()); //the first item in the given array is the ID 


		//If the Ballot is not empty, we proceed to extract the information from this ballot
		if(ballotInformation.length >= 2) {
			ballotInformation = remove(0, ballotInformation); //Since we extracted the ID already, we can remove it form the unprocessed array
			castVotes(ballotInformation); //We delegate the vote storing to this method
		} else {
			//If the ballot is empty, we just account it as an empty ballot
			Election.blankBallot++;
		}

		if(validateBallot() == true) //Pretty straightforward. If it passes the validation test it's a valid ballot, otherwise it's invalid
			setValidBallot(true);
		else {
			setValidBallot(false);
		}

	}

	// ballotNum,c1:r1,c2:r2,...,cn:rn => This is the format used to extract information from candidates


	/*
	 * Method that is in charge of receiving all of the candidate votes and storing them into
	 * an instance of Vote (check Vote.java for more info) and saving that vote into a set of type Vote
	 */
	private void castVotes(String[] ballotInformation) {

		for (String information : ballotInformation) { //Iterate through unprocessed information
			//Extract the ID and the rank of vote
			String ID = information.substring(0,information.indexOf(":"));
			String rank = information.substring(information.indexOf(":") + 1, information.length());

			Vote vote = new Vote(ID, rank);
			castedVotes.add(vote);
		}


	}


	/*
	 * Method that returns true if the ballot has no repeated rankings,
	 * a ranking is not higher than the candidate number or is not empty.
	 * If any of these conditions is met, then the method return false
	 * 
	 * @param: nothing
	 * @returns: true or false, depending on the condition met
	 * 
	 */
	private boolean validateBallot() {

		//We iterate through all the votes, if two votes have the same rank, it's invalid
		for (int i = 0; i < castedVotes.size(); i++) {
			for (int j = i + 1; j < castedVotes.size(); j++) {
				if (castedVotes.get(i).getRank() == castedVotes.get(j).getRank()) {
					return false;
				}
			}
		}

		//If a ballot has duplicate candidate ID's, it's invalid
		for (int i = 0; i < castedVotes.size(); i++) {
			for (int j = i + 1; j < castedVotes.size(); j++) {
				if (castedVotes.get(i).getCandidateID() == castedVotes.get(j).getCandidateID()) {
					return false;
				}
			}
		}

		//If a candidate is ranked with a higher number than the number of candidates, then its invalid
		for (Vote vote : castedVotes) {
			if (vote.getRank() > castedVotes.size()) {
				return false;
			}
		}
		
		if (castedVotes.isEmpty() == true) {
			return false;
		}

		return true;


	}

	/*
	 * Returns the candidateID of the candidate that received a rank of 1 on the ballot
	 * @param: nothing
	 * @returns: candidateID regarding the #1 ranked candidate on the ballot
	 */

	public int getRankByCandidate(int candidateId) {
		//rank for the candidate
		int rank = 0;
		for (Vote vote : castedVotes) {
			if (vote.getCandidateID() == candidateId) {
				rank = vote.getRank();
			}
		}

		return rank;
	}

	/*
	 * Returns the candidate ID of a specified rank
	 * 
	 * @param the rank of a specific candidate
	 * @returns the candidate ID of the candidate that has the rank given
	 *
	 */
	public int getCandidateByRank(int rank) {
		//candidate with that rank
		int ID = 0;
		for (Vote vote : castedVotes) {
			if (vote.getRank() == rank) {
				ID = vote.getCandidateID();
			}
		}

		return ID;
	}
	
	public int getFirstChoice() {
		return getCandidateByRank(1);
	}

	public int getBallotNum() {
		// returns the ballot number
		return ballotNumber;
	}

	public boolean eliminate(int candidateId) {
		// eliminates a candidate

		//Find candidate in casted votes by it's ID. If it doesn'f find anyone, rank = 0
		int rank = getRankByCandidate(candidateId);


		//Iterate through all votes casted
		for (Vote vote : castedVotes) {
			//If the candidate was found earlier, we remove it now using the iterator, since this is a set
			if (vote.getRank() == rank) {
				castedVotes.remove(vote);
				break;
			}
		}


		//If there is a rank saved, it means we removed someone
		if(rank != 0) {
			//By consequence, we check all of the candidates with a worse rank than the one we removed and we upgrade the rank by 1
			//Remember, lower rank = better rank
			for (Vote vote : castedVotes) {
				if (vote.getRank() > rank) {    
					vote.setRank(vote.getRank() - 1);
				}
			}
			return true;
		} else {
			//If we land here, it means we never removed anyone!
			return false;
		}

		/* Here's an example if we we removed the candidate with an ID of 3
		 * 
		 *
		 * rank = getRankByID(ID) => 3;
		 * castedVotes.remove(vote);
		 * 1,2,3,4,5,6,7,8,9
		 * 1,2,4,5,6,7,8
		 * 1,2,3,4,5,6,7
		 */
	}

	public void printBallot() {
		System.out.println("{Ballot " + getBallotNum() + "}");
		for (Vote vote : castedVotes) {
			System.out.println("\t" + "Candidate: " + vote.getCandidateID() + "," + " Rank: " + vote.getRank());
		}
	}

	//Getters
	public boolean isValidBallot() {
		return validBallot;
	}

	public void setValidBallot(boolean validBallot) {
		this.validBallot = validBallot;
	}

	public ArrayList<Vote> getCastedVotes() {
		return castedVotes;
	}

	//Method to remove ballotNumber from array so it makes the vote storing a bit easier
	public static String[] remove(int index, String[] arr) {
		String[] newArr = new String[arr.length - 1];
		if(index < 0 || index > arr.length) return arr;

		int j = 0;

		for(int i = 0; i < arr.length; i++) {
			if(i == index) {
				i++;
			}
			newArr[j++] = arr[i];
		}

		return newArr;
	}

}