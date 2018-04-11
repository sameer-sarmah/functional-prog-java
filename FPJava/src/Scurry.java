import java.util.function.Function;

public class Scurry {
	public static void main(String [] args) {
		
		Function<Integer,Function<Integer,Integer>> addFunc=(Integer num1)->{
			Function<Integer,Integer> scurriedFunc=(Integer num2)->{
				return num1+num2;
			};
			return scurriedFunc;
		};
		
		
		Function<Integer,Integer> incrementTheNumber=(Integer num)->{
			return ++num;
		};
		
		Function<Integer,Integer> doubleTheNumber=(Integer num)->{
			return num*2;
		};
		
		Function<Integer,Integer> returnedFunction=addFunc.apply(1);
		System.out.println(returnedFunction.apply(2));
		System.out.println(incrementTheNumber.andThen(doubleTheNumber).apply(5));
		System.out.println(incrementTheNumber.compose(doubleTheNumber).apply(5));
	}
}
