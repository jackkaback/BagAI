// bags object class
import java.util.*;

public class bags{

	//Assorted values to be stored in the object
	public boolean[] items = new boolean[24];
	public double score = 0;
	private int value = 0;
	private int weight = 0;

	//function for defining the score of the bag
	private void scoring(){

		//sum of the values over the max capacity minus the weight stored
		//this way the program encourages filling the bag to the max
		//max capcity is slightly larger that actual so there's never a divide by zero error
		double temp = value / (7312160.1 - weight);

		//if the bag is over capacity, then the ba gets a 0.1
		if (temp < 0){
			this.score = 0.1;
		}

		else{
			this.score = temp;
		}
	}

	//finds the value and weight of a given bag
	private void setValuesAndWeights(){

		this.weight = 0;
		this.value = 0;

		int[] vals = {675367, 1677009, 1676628, 1523970, 901489, 97326, 69666, 1296457, 1679693, 1902996, 1844992, 1049289,
				1319836, 1252836, 953277, 2067538, 825594, 853655, 1826027, 65731, 943972, 577243, 466257, 369261};
		int[] weights = {382745, 799601, 909247, 729069, 467902, 44328, 34610, 698150, 823460, 903959, 853665, 551830,
				610856, 670702, 488960, 951111, 323046, 446298, 931161, 31385, 496951, 264724, 224916, 169684};

		//iterates through the array to find all items included
		for (int i = 0; i < items.length; i++){
			if (this.items[i]){
				this.weight += weights[i];
				this.value += vals[i];
			}
		}

	}


	//makes the given bag randomly from the parent bags
	private void breeding(boolean[] dad, boolean[] mom){
		Random rand = new Random();

		//iterates through and randomly picks from each parent
		for (int i = 0; i < items.length; i++){

			if(rand.nextBoolean()){
				this.items[i] = dad[i];
			}

			else{
				this.items[i] = mom[i];
			}

			//Randomly mutates, 3% chance
			if(rand.nextInt(100) <= 2){
				this.items[i] = !this.items[i];
			}
		}


		//after making the bag, it sets the score and weights, then determines the score
		setValuesAndWeights();
		scoring();
	}

	//requires parent items to make a new bag
	//initializes the bag
	public bags(boolean[] dad, boolean[] mom){
		breeding(dad, mom);
	}
}