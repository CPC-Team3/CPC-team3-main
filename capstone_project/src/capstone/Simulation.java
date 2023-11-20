package capstone;

public class Simulation {

	public static void main(String[] args) {
		ChargeStation station1 = new ChargeStation();
		station1.chargers.add(new Charger());
		station1.chargers.get(0).id = 1;
		
		while(true) {
			try {
				station1.listening(null);
				station1.availability();
				station1.assigning(0, 0);
				
			}catch (listeningException e) {
	            System.out.println("Error in listening: " + e.getMessage());
	           
	            
	        }catch (assigningException e) {
	            System.out.println("Error in assigning: " + e.getMessage());
	            
	        }
			
			
		}

	}

}
