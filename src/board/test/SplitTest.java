package board.test;

public class SplitTest {

	public static void main(String[] args) {
		
		String str = "hello~java~world";
		
		String[] arr = str.split("~");
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
	}

}
