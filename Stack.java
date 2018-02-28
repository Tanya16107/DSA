public class Stack{
	int[] l; 
	int max_size;
	int size;

	public Stack(int n){
		l = new int[n];	
		max_size=n;	
		int size = 0;
	}

			
	public int peek(){		//returning 0 is usually used to say that the method ran successfully,
							//A better(rather standard) way is to return -1 if the operation was unsuccessfull.
		if (size == 0){
			return -1;
			
		}
		else{
			int out = l[size-1];
			return out;
		}
		

	}

	public boolean push(int x){			
		if (size == max_size){
			System.out.println("The stack is full.");
			return false;
		}
		else{
			l[size] = x;
			size+=1;
			return true;
		}
		
	}

	public int pop(){
		if (size == 0){
			System.out.println("The stack is empty.");
			return -1;

		}
		else{
			int out = l[size-1];
			size-=1;
			return out;
		}
				

	}
	public static void main(String[] args){
		Stack the = new Stack(5);
		the.peek();
		the.push(2);
		System.out.println(the.pop());
	}
}
