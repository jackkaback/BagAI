public class main {

	public static void main(String[] args) {

		//boolean arrays for the initial 1st gen of bags
		boolean[] dad = new boolean[24];
		boolean[] mom = new boolean[24];
		double sum = 0;

		//sets the values of the arrays
		for (int i = 0; i < dad.length; i++){
			dad[i] = true;
			mom[i] = false;
		}

		//the two arrays of bags used
		//one is used for the current gen of bags
		//the other is used to store the next gen untill it is put back into the orginal
		bags[] baggins= new bags[40];
		bags[] tempBaggins = new bags[40];

		//initiallized the 1st generation of bags
		for (int i = 0; i < baggins.length; i++){
			baggins[i] = new bags(dad, mom, true);
			sum += baggins[i].score;

		}

		sorter(baggins);

		for (int i = 0; i < baggins.length; i++){
			System.out.println(i + " " + baggins[i].score);
		}
		System.out.println("Average: " + (sum/baggins.length));

		for (int j = 0; j < 1000; j++) {

			System.out.println("Generation: " + (j + 2));

			propagate(baggins, tempBaggins, sum);
			sum = 0;
			sorter(tempBaggins);

			//filthy bagginses
			baggins = tempBaggins;

			for (int i = 0; i < baggins.length; i++) {
				System.out.println(i + " " + baggins[i].score);
				sum += baggins[i].score;
			}

			System.out.println("Average: " + (sum/baggins.length));

		}

	}

	public static void propagate(bags[] bag1, bags[] bag2, double sum){

		//saves a copy of the best for next gen
		bag2[0] = new bags(bag1[0].items, bag1[0].items, false);


		//itterates through the whole list and breeds assorted parents
		for(int j = 1; j < bag1.length; j++) {
			int randomIndex = 0;

			double random = Math.random() * sum;

			for (int i = 0; i < bag1.length; ++i) {
				random -= bag1[i].score;
				if (random <= 0.0d) {
					randomIndex = i;
					break;
				}
			}

			bag2[j] = new bags(bag1[j].items, bag1[randomIndex].items, true);

		}
	}


	//sorts the bags by score
	public static void sorter(bags[] arr) {
		int n = arr.length;
		bags temp;

		for(int i=0; i < n; i++){

			for(int j=1; j < (n-i); j++){
				if(arr[j-1].score < arr[j].score){

					//swap elements
					temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;

				}
			}
		}

	}
}