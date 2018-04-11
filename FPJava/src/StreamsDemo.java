import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class StreamsDemo {
	public static void main(String[] args) {
		List<Integer> numbers = IntStream.rangeClosed(1, 2000).boxed().collect(Collectors.toList());
		Predicate<Integer> predicate = (num) -> {
			if (num >= 500)
				return true;
			else
				return false;
		};
		List<List<Integer>> subLists=sublist(numbers,predicate);
		subLists.stream().forEach((List<Integer> subList)->{
			System.out.println("size of sublist "+subList.size() +" starting element "+subList.get(0));
		});
		
		IntStream stream = IntStream.range(1, 11);
		stream.filter((int num) -> {
			if (num % 2 == 0) {
				return true;
			} else {
				return false;
			}
		}).map((int num) -> {
			return num * 2;
		}).forEach((int num) -> {
			System.out.println(num);
		});

		stream = IntStream.range(1, 100);
		int sum = stream
				.parallel()
				.reduce(0, (int num1, int num2) -> {
			return num1 + num2;
		});
		System.out.println(sum);
		
		stream = IntStream.range(1, 10);
		List<Integer> list=stream.boxed().sorted().collect(Collectors.toList());
		System.out.println(list);
		
		stream = IntStream.range(1, 11);
		stream.filter((int num) -> {
			if (num % 2 == 0) {
				return true;
			} else {
				return false;
			}
		})
		.mapToObj((int num) -> {
			List<Integer> l=new ArrayList<>();
			l.add(num*2);
			return l;
		})
		.peek((List<Integer> eachList)->{
			System.out.println(eachList);
		})
		.flatMap((List<Integer> eachList)->{
			return eachList.stream();
		})
		.forEach((Integer num) -> {
			System.out.println(num);
		});
	}
	
	public static List<List<Integer>> sublist(List<Integer> numbers, Predicate<Integer> predicate) {
		boolean shouldPartition = predicate.test(numbers.size() / 2);
		List<List<Integer>> partitionedList = new ArrayList<>();
		if (shouldPartition) {
			int middleElement=numbers.get(numbers.size()/2);
			Collection<List<Integer>> partitionedCollection = numbers.stream()
			.collect(Collectors.partitioningBy((element)->{
				if(element>=middleElement)
					return true;
				else
					return false;
					
			})).values();
			partitionedList = new ArrayList<>(partitionedCollection);
			List<Integer> leftSubList=partitionedList.get(0);
			List<List<Integer>> partitionedLeftSubList=sublist(leftSubList,predicate);
			List<Integer> rightSubList=partitionedList.get(1);
			List<List<Integer>> partitionedRightSubList=sublist(rightSubList,predicate);
			partitionedList.clear();
			partitionedList.addAll(partitionedLeftSubList);
			partitionedList.addAll(partitionedRightSubList);
		}else {
			partitionedList.add(numbers);
		}
		return partitionedList;

	}
}
