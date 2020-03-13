package Main;
/**
 * The Eliminated Candidate
 * 
 * This class stores the information that surrounds a candidate that is eliminated from the race
 * 
 * @author: Fernando J Bermudez (@bermed28)
 * @version: 1.0.0
 * @since: 2020-03-07
 */
public class EliminatedCandidate {
	String name;
	int ID;
	int ones;
	
	public EliminatedCandidate(String name, int ID, int ones) {
		this.name = name;
		this.ID = ID;
		this.ones = ones;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getOnes() {
		return ones;
	}

	public void setOnes(int ones) {
		this.ones = ones;
	}
}
