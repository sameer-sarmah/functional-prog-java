import java.util.function.BiFunction;

public class HigherOrderFunction {
	
	
public static void main(String [] args) {
	
	System.out.println(applyStrategy(2,2,createStrategy(Operation.ADD)));
	System.out.println(applyStrategy(4,2,createStrategy(Operation.SUB)));
	System.out.println(applyStrategy(2,2,createStrategy(Operation.MUL )));
	System.out.println(applyStrategy(4,2,createStrategy(Operation.DIV)));
	
}

public static int applyStrategy(int num1,int num2,BiFunction<Integer,Integer,Integer> operation) {
	return operation.apply(num1,num2);
}

public static BiFunction<Integer,Integer,Integer> createStrategy(Operation operation) {
	switch(operation) {
	case ADD:{
		return (Integer num1,Integer num2)->{
			return num1+num2;
		};
	}
	case SUB:{
		return (Integer num1,Integer num2)->{
			return num1-num2;
		};
	}
	case MUL:{
		return (Integer num1,Integer num2)->{
			return num1*num2;
		};
	}
	case DIV:{
		return (Integer num1,Integer num2)->{
			return num1/num2;
		};
	}
	}
	return null;
}


}

enum Operation{
	ADD,SUB,MUL,DIV
}