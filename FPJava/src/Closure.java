
import java.util.function.Supplier;

public class Closure {

	public static void main(String [] args) {
		System.out.println(closureDemo().get());
	}
	
	public static Supplier<Integer> closureDemo(){
		
		final int num1=1;
		final int num2=2;
		Supplier<Integer> closure=( ) -> {
			return num1+num2;
		};
		return closure;
	}
	
}
