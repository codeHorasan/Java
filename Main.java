import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
	private JLabel label1, label2, label3, label4;
	private JTextField text; 
	private JButton buttonAdd, buttonOrder;
	private JRadioButton juice, water, tea, coffee;
	private ButtonGroup group;  //For RadioButtons
	String[] boxItems = {"Small", "Medium", "Large"};  //For beverage
	private JComboBox box = new JComboBox(boxItems);
	ArrayList<beverageHelper> beverageList = new ArrayList<>();
    //Defined the variables here to access them from anywhere
	
	public Main() {
		setLayout(null);
		
		label1 = new JLabel();
		label1.setText("Select size:");
		label1.setSize(100,50);
		label1.setLocation(120, 30);
		add(label1);
		
		label2 = new JLabel();
		label2.setText("Select which type of baverage you want to order:");
		label2.setSize(300,50);
		label2.setLocation(120, 80);
		add(label2);
		
		label3 = new JLabel();
		label3.setText("Select how many glasses you want to order:");
		label3.setSize(300,50);
		label3.setLocation(120, 130);
		add(label3);
		
		text = new JTextField();
		text.setSize(300, 20);
		text.setLocation(120, 170);
		add(text);
		
		buttonAdd = new JButton();
		buttonAdd.setText("Add");
		buttonAdd.setSize(80, 30);
		buttonAdd.setLocation(120, 200);
		buttonAdd.addActionListener(this);  //Added ActionListener to use actionPerformed method
		add(buttonAdd);
		
		buttonOrder = new JButton();
		buttonOrder.setText("Order");
		buttonOrder.setSize(80, 30);
		buttonOrder.setLocation(320, 200);
		buttonOrder.addActionListener(this);  
		add(buttonOrder);
		
		group = new ButtonGroup();
		
		juice = new JRadioButton();
		juice.setText("Juice");
		juice.setBounds(120, 120, 70, 20);   //Setting size and location
		add(juice);
		
		water = new JRadioButton();
		water.setText("Water");
		water.setBounds(190, 120, 70, 20);
		add(water);
		
		tea = new JRadioButton();
		tea.setText("Tea");
		tea.setBounds(260, 120, 50, 20);
		add(tea);
		
		coffee = new JRadioButton();
		coffee.setText("Coffee");
		coffee.setBounds(310, 120, 100, 20);
		add(coffee);
		
		//Adding RadioButtons to ButtonGroup
		group.add(juice);
		group.add(water);
		group.add(tea);
		group.add(coffee);
		

		box.setBounds(120, 70, 85, 20);
		add(box);
		
		label4 = new JLabel();
		label4.setText("");
		label4.setSize(300, 25);
		label4.setLocation(120, 240);
		add(label4);
		
		setSize(600, 400);
		setVisible(true);
		setTitle("GUI APP - Liquid Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
			
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonAdd)) {    //Controlling that if buttonAdd is pressed
			if((group.isSelected(null)) && (text.getText().equals(""))) {
				JOptionPane.showMessageDialog(this, "Choose a beverage type and enter an amount.");
			}
			else if (group.isSelected(null)) {
				JOptionPane.showMessageDialog(this, "Choose a beverage type.");
			}
			else if (text.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Enter an amount.");
			}  //Controlling every unexpected conditions
			
			else {
				String x = (String) box.getSelectedItem().toString();  //Accessing the selected item from the comboBox
				double boxCode = 0;  //Here, I have used a variable called boxCode and gave it random double numbers in order to calculate the price(ucret)
				if(x.equals("Small"))
					boxCode = 0.87;
				else if(x.equals("Medium")) {
					boxCode = 1.13;
				}
				else if(x.equals("Large")) {
					boxCode = 1.25;
				}

				if(juice.isSelected()) {
					double ucret;    //price
					int amount = Integer.parseInt(text.getText());   //Converting String(from the textfield) to an integer
					Object[] fruits = {"Apple","Orange","Pinapple"};  //Type is Object for adding it to ComboBox
					JComboBox fruitList = new JComboBox(fruits);
					JLabel fruitLabel = new JLabel();
					fruitLabel.setText("Select a fruit!");
					Object[] obje = {fruitLabel, fruitList};   //I have created an arrayList of type Object in order to show both the Label and the ComboBox in the JOptionPane window
					JOptionPane.showConfirmDialog(this, obje, "Select a fruit", JOptionPane.OK_CANCEL_OPTION);
					ucret = boxCode * (amount * 1.30) * ((fruitList.getSelectedIndex() + 1) * 0.6);    //Constants are random but they make sense
					beverageList.add(new beverageHelper(amount, "Juice", (String) fruitList.getSelectedItem(), false, ucret));   //Adding the newly created beverageList type object to the ArrayList called beverageList
					label4.setText(beverageList.get(beverageList.size() - 1).toString());    //Here, the label shows us the last index of the ArrayList which the user had added last
					group.clearSelection();   //Clearing for better use
					text.setText("");
					
					
				}
				
				else if(water.isSelected()) {
					int amount = Integer.parseInt(text.getText());
					int i = JOptionPane.showConfirmDialog(this, "Would you like ice?", "Ice", JOptionPane.YES_NO_OPTION);   //JOptionPane is equal to an int in order to understand which button is pressed
					//System.out.println(i); -- Yes is 0, No is 1
					double ucret = 0;
					double iceExtra = 1;   //Initial value is 1, if the user wants extra ice than this value is going to be increased
					if(i == 0) {   //Clicked "Yes"
						iceExtra = 1.35;   //The "ice" constant is 1.35
						ucret = boxCode * (amount * 1.20) * iceExtra;   //The constant of amount in "water" is given as 1.20 
						beverageList.add(new beverageHelper(amount, "Water", null, true, ucret));  //Adding it to the list, true part is if the user wants an extra thing, there is a "null" because that part is special to "Juice"
						label4.setText(beverageList.get(beverageList.size() - 1).toString());   //Accessing the last index of the arraylist and writes according to toString() method of the Constructor
					}
					else if(i == 1) {   //Clicked "No"
						ucret = boxCode * (amount * 1.20) * iceExtra;
						beverageList.add(new beverageHelper(amount, "Water", null, false, ucret));
						label4.setText(beverageList.get(beverageList.size() - 1).toString());
					}
					
					group.clearSelection();
					text.setText("");
				}
				
				//Same thing with Water but Ice is Sugar
				else if(tea.isSelected()) {
					int amount = Integer.parseInt(text.getText());
					int i = JOptionPane.showConfirmDialog(this, "Would you like sugar?", "Sugar", JOptionPane.YES_NO_OPTION);
					double ucret = 0;
					double sugarExtra = 1;
					
					if(i == 0) {		
						sugarExtra = 1.50;
						ucret = boxCode * (amount * 1.50) * sugarExtra;
						beverageList.add(new beverageHelper(amount, "Tea", null, true, ucret));
						label4.setText(beverageList.get(beverageList.size() - 1).toString());
					}
					else if(i == 1) {
						ucret = boxCode * (amount * 1.50) * sugarExtra;
						beverageList.add(new beverageHelper(amount, "Tea", null, false, ucret));
						label4.setText(beverageList.get(beverageList.size() - 1).toString());
					}
					
					group.clearSelection();
					text.setText("");
				}
				
				//Same thing with Water but Ice is Milk
				else if(coffee.isSelected()) {
					int amount = Integer.parseInt(text.getText());
					int i = JOptionPane.showConfirmDialog(this, "Would you like milk?", "Milk", JOptionPane.YES_NO_OPTION);
					double ucret = 0;
					double milkExtra = 1;
					
					if(i == 0) {
						milkExtra = 1.65;
						ucret = boxCode * (amount * 1.70) * milkExtra;
						beverageList.add(new beverageHelper(amount, "Coffee", null, true, ucret));
						label4.setText(beverageList.get(beverageList.size() - 1).toString());
					}
					else if(i == 1) {
						ucret = boxCode * (amount * 1.70) * milkExtra;
						beverageList.add(new beverageHelper(amount, "Coffee", null, false, ucret));
						label4.setText(beverageList.get(beverageList.size() - 1).toString());
					}
					
					group.clearSelection();
					text.setText("");
				}
			}
				
		}
		
		else if(e.getSource().equals(buttonOrder)) {   //Controlling that if buttonOrder is pressed
			String message = "";
			double total = 0;   //Initial value of the total price is 0
			for (beverageHelper beverageHelper : beverageList) {   //For-each loop
				if(beverageHelper.getType().equals("Juice")) {
					//Here, I keep adding the "added" thing by the user which are on our ArrayList and with he help of the loop, we can get the information of each added thing and get their price and calculate the total price
					message += beverageHelper.getFruit() + " juice   -  " + String.valueOf(Math.round(beverageHelper.getUcret() * 100d) / 100d) + "\n" ;  //Rounded the number to 2 digits with * 100d / 100d and converted the number to String in order to use it in the label 
					total += beverageHelper.getUcret();  //To find the total(sum) price
				}
				else if(beverageHelper.getType().equals("Water")) {
					if(beverageHelper.isExtra()) {   //Controlled if the extra(Ice to the Water or Sugar to the Tea or Milk to the Coffee added or not)
						message += "water with ice  -  " + String.valueOf(Math.round(beverageHelper.getUcret() * 100d) / 100d) + "\n";
						total += beverageHelper.getUcret();
					}		
					else {   //If the extra thing not selected by the user
						message += "water  -  " + String.valueOf(Math.round(beverageHelper.getUcret() * 100d) / 100d) + "\n";
						total += beverageHelper.getUcret();
					}
				}
				else if(beverageHelper.getType().equals("Tea")) {
					if(beverageHelper.isExtra()) {   //Controlled if the extra(Ice to the Water or Sugar to the Tea or Milk to the Coffee added or not)
						message += "tea with sugar  -   " + String.valueOf(Math.round(beverageHelper.getUcret() * 100d) / 100d) + "\n";
						total += beverageHelper.getUcret();
					}
						
					else {   //If the extra thing not selected by the user
						message += "tea  -   " + String.valueOf(Math.round(beverageHelper.getUcret() * 100d) / 100d) + "\n";
						total += beverageHelper.getUcret();
					}
				}
				else if(beverageHelper.getType().equals("Coffee")) {
					if(beverageHelper.isExtra()) {   //Controlled if the extra(Ice to the Water or Sugar to the Tea or Milk to the Coffee added or not)
						message += "coffee with milk  -   " + String.valueOf(Math.round(beverageHelper.getUcret() * 100d) / 100d) + "\n";
						total += beverageHelper.getUcret();
					}
						
					else {   //If the extra thing not selected by the user
						message += "coffee  -  " + String.valueOf(Math.round(beverageHelper.getUcret() * 100d) / 100d) + "\n";
						total += beverageHelper.getUcret();
					}
				}
				//Same comments are available for the Water, Tea and Coffee parts
			}
			JOptionPane.showMessageDialog(this, message);    //Adding the created "message" to the OptionPane
			JOptionPane.showMessageDialog(this, "You should pay " + String.valueOf(Math.round(total * 100d) / 100d) + "TL.");   //Gives us the total price 
		}
		
	}
	
	
	public static void main(String[] args) {
		new Main();
	}

}
