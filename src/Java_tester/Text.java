package Java_tester;

public class Text {

	public static void main(String[] args) {
		boolean statusA;
		boolean statusB;
		
		statusA =  false;
		statusB = false;
		System.out.println("Kết quả = " + (statusA || statusB));

		statusA =  true;
		statusB = true;
		System.out.println("nam phương = " + (statusA && statusB));
		
		statusA =  false;
		statusB = true;
		System.out.println("Kết quả = " + (statusA || statusB));
	}

}
