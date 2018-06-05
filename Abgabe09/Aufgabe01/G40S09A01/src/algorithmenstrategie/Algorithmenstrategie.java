package algorithmenstrategie;



/**Testklasse
 *  
 * @author Kilian
 *
 */
class Klient {
	public static void main(final String[] args) {
		final Kontext k = new Kontext();
		int[] meinArray = { 1, 646, 74, 4 };
		
		k.setStrategie(new quick(), meinArray);
		k.operation(); 
	//	k.setStrategie(new bubble(), meinArray);
	//	k.operation(); 

	}
}

class Kontext {

	private IStrategie strategie = null;
	private int[] zahlen = null;

	public void setStrategie(final IStrategie STRATEGIE, final int[] ZAHLEN) {
		
		
		strategie = STRATEGIE;
		zahlen = ZAHLEN;
		
	}

	public void operation() {
		if (strategie != null) {
			strategie.algorithmus(zahlen);
		}
	}

}

interface IStrategie {
	void algorithmus(int[] zahlen);
}

class bubble implements IStrategie {
	public void algorithmus(int[] zahlen) {
		
		for (int i = 0; i < zahlen.length; i++) {
			System.out.print(zahlen[i]);
			if (i != zahlen.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("");


		int[] bubbleArray = new int[zahlen.length];
		bubbleArray = zahlen;
		bubbleArray = bubblesort(bubbleArray);
	

		for (int x = 0; x < bubbleArray.length; x++) {
			System.out.print(bubbleArray[x]);
			if (x != bubbleArray.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("");
	}

	public static int[] bubblesort(int[] zusortieren) {
		int temp;
		for (int i = 1; i < zusortieren.length; i++) {
			for (int j = 0; j < zusortieren.length - i; j++) {
				if (zusortieren[j] > zusortieren[j + 1]) {
					temp = zusortieren[j];
					zusortieren[j] = zusortieren[j + 1];
					zusortieren[j + 1] = temp;
				}

			}
		}
		return zusortieren;
	}

}

class quick implements IStrategie {
	public void algorithmus(int[] zahlen) {
		
		for (int i = 0; i < zahlen.length; i++) {
			System.out.print(zahlen[i]);
			if (i != zahlen.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("");

		
		int[] quickArray = new int[zahlen.length];
		quickArray = zahlen;
		quickSort(quickArray, 0, quickArray.length - 1);

	
		for (int x = 0; x < quickArray.length; x++) {
			System.out.print(quickArray[x]);
			if (x != quickArray.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("");

	}

	void quickSort(int arr[], int left, int right) {

		int index = partition(arr, left, right);

		if (left < index - 1)

			quickSort(arr, left, index - 1);

		if (index < right)

			quickSort(arr, index, right);

	}

	int partition(int arr[], int left, int right)

	{

		int i = left, j = right;

		int tmp;

		int pivot = arr[(left + right) / 2];

		while (i <= j) {

			while (arr[i] < pivot)

				i++;

			while (arr[j] > pivot)

				j--;

			if (i <= j) {

				tmp = arr[i];

				arr[i] = arr[j];

				arr[j] = tmp;

				i++;

				j--;

			}

		}
		;

		return i;

	}
}