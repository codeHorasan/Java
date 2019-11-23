
public class beverageHelper {
	private int amount;   
	private String type;
	private String fruit;   //This is for the Juice, null for others
	private boolean isExtra;   //Ice to the Water, Sugar to the Tea and Milk to the Coffee (False for Juice)
	private Double ucret;   //Price
	
	public beverageHelper(int amount, String type, String fruit, boolean isExtra, Double ucret) {
		super();
		this.amount = amount;
		this.type = type;
		this.fruit = fruit;
		this.isExtra = isExtra;
		this.ucret = ucret;
	}
	
	public Double getUcret() {
		return ucret;
	}
	public void setUcret(Double ucret) {
		this.ucret = ucret;
	}
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		this.fruit = fruit;
	}

	public boolean isExtra() {
		return isExtra;
	}

	public void setExtra(boolean isExtra) {
		this.isExtra = isExtra;
	}

	@Override
	public String toString() {    //For using toString() method to set the label
		if(type.equals("Juice")) 
			return String.valueOf(amount) + " glass(es) of " + fruit + " added.";   //Converted number to String for label which needs String
		else if(type.equals("Water")) {   
			if(isExtra)   //Controlling if Ice to the Water is added 
				return String.valueOf(amount) + " glass(es) of water with ice added.";
			else {
				return String.valueOf(amount) + " glass(es) of water added.";
			}
		}
		else if(type.equals("Tea")) {   
			if(isExtra)    //Controlling if Sugar to the Tea is added 
				return String.valueOf(amount) + " glass(es) of tea with sugar added.";
			else {
				return String.valueOf(amount) + " glass(es) of tea added.";
			}	
		}
		else {
			if(isExtra)   //Controlling if Milk to the Coffee is added 
				return String.valueOf(amount) + " glass(es) of coffee with milk added.";
			else {
				return String.valueOf(amount) + " glass(es) of coffee added.";
			}	
		}
	}


}
