package basic_java;

import org.testng.annotations.Test;

public class topic11_arrary {
int number[] = {5, 6, 72, 88, 9000, 1000};
	
@Test

	public void TC_01_Find_Max_number() {
	int x = 0;
		for (int i = 0; i < number.length; i++) {
			if (x < number[i]) {
				x = number[i];
			}
		}
System.out.println("Max number = " + x);
}

@Test

public void TC_01_even_number() {
	for(int i = 0; i < number.length; i++) {
	if (number[i] % 2 ==0) {
		
		System.out.println("even number =" + number[i]);
		
	}
	
	
	
}
}


@Test

public void TC_01_average() {
	int sum = 0;
	for(int i = 0; i < number.length; i++) {
		sum += number[i];
		
	}
	System.out.println(" tong bang " + sum);
	System.out.println("Trung binh cong = " + sum/ number.length);
}
	
			
	
}



