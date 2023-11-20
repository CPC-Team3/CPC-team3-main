package capstone;

public class Charger {
	
	int id;
	boolean occupied;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	@Override
	public String toString() {
		return "Charger [id=" + id + ", occupied=" + occupied + "]";
	}
	
}
