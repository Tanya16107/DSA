import java.io.*; 

class Node 
{
	public Integer sData;
	public Integer iData;
	public Node leftChild;
	public Node rightChild;

}

class Heap
{
	private Node[] heapArray;
	private int maxSize; // size of array
	private int currentSize; // number of nodes in array

	public Heap(int mx) // constructor
	{
	maxSize = mx;
	currentSize = 0;
	heapArray = new Node[maxSize]; // create array 
	}

	public boolean isEmpty()
	{ return currentSize==0; }

	public boolean insert(Node newNode) 
	{
	if(currentSize==maxSize) 
		return false;

	heapArray[currentSize] = newNode; 
	trickleUp(currentSize++);
	return true;
	} // end insert()


	public void trickleUp(int index) 
	{
	int parent = (index-1) / 2; 
	Node bottom = heapArray[index];

	while( index > 0 &&
	heapArray[parent].iData() >bottom.iData() )	
	{
	heapArray[index] = heapArray[parent]; // move it down 
	index = parent;
	parent = (parent-1) / 2;
	} // end while
	heapArray[index] = bottom;
	} 

	public Node remove()  { // (assumes non-empty list) 
	Node root = heapArray[0];
	heapArray[0] = heapArray[--currentSize]; 
	trickleDown(0);
	return root;
	} // end remove()

	public void trickleDown(int index) 
	{
	int largerChild;
	Node top = heapArray[index]; 
	while(index < currentSize/2)
	{
	int leftChild = 2*index+1; 
	int rightChild = leftChild+1;
	if(rightChild < currentSize && heapArray[leftChild].iData() > heapArray[rightChild].iData()) 
		largerChild = rightChild;
	else
	largerChild = leftChild;
	// top >= largerChild? 
	if( top.iData() <= heapArray[largerChild].iData() )
	break;
	// shift child up 
	heapArray[index] = heapArray[largerChild];
	index = largerChild; // go down
	} // end while
	heapArray[index] = top; // root to index
	} // end trickleDown()
}

