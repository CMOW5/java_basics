package generics_example;

public class Main {

	public static void main(String[] args) {
		Integer[] intArray = {1,2,3,4,5};
		String[] strArray = {"hola","mundo","como","estas"};
		
		printArray(intArray);
		printArray(strArray);
		
		String strA = "hola";
		String strB = "chao";
		Integer ia = 2;
		Integer ib = 3;
		Double da = 2.0;
		Double db = 3.0;
		System.out.println(findMax(strA, strB));
		System.out.println(findMax(ia, ib));
		System.out.println(findMax(da, db));
	}
	
	public static <E> void printArray(E[] array) {
		for (E element : array) {
			System.out.print(element + " ");
		}
		System.out.println();
	}
	
	public static <E, T> T anotherGenericMethod(E[] array, T otherParam) {
		for (E element : array) {
			System.out.print(element + " ");
		}
		System.out.println();
		return otherParam;
	}
	
	public static <T extends Comparable <T>> T findMax(T a, T b) {
		int n = a.compareTo(b);
		if (n < 0) {
			return b;
		} else {
			return a;
		}
	}

}
