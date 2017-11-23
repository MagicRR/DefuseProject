package data;

public class Module {
	private int id;
	private EnigmeBoard enigmeBoard;
	private boolean isActive;
	private boolean isResolved;
	
	public Module(int id, EnigmeBoard enigmeBoard, boolean isActive, boolean isResolved) {
		super();
		this.id = id;
		this.enigmeBoard = enigmeBoard;
		this.isActive = isActive;
		this.isResolved = isResolved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EnigmeBoard getEnigmeBoard() {
		return enigmeBoard;
	}

	public void setEnigmeBoard(EnigmeBoard enigmeBoard) {
		this.enigmeBoard = enigmeBoard;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isResolved() {
		return isResolved;
	}

	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}
	
	
}
