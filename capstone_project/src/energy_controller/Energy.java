package energy_controller;

public class Energy {
	String name;
	String type;	
	public Energy(String name,String type){
		this.name = name;
		this.type = type;
	}
	
	// to strings
	@Override
	public String toString() {
		return "Energy [ type: " + this.type + ", name: " + this.name + " ]";
	}
}
