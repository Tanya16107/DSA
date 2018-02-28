import java.io.*;
import java.util.*;

class Node 
{
	public Integer vert;
	public Integer key;

	public Node(Integer vert, Integer key)
	{
		this.vert = vert;
		this.key = key;
	}
}

class minHeap
{
	public Node[] ha;
	private int max; 
	public int size; 

	public minHeap(int max) 
	{
	this.max = max;
	size = 0;
	ha = new Node[max]; 
	}

	public Node find(int fvert)
	{
		Node out = new Node(-1,-1);
		for(int i=0; i<size; i++)
		{
			if(ha[i].vert==fvert)
			{
				out = new Node(fvert, ha[i].key);
			}

		}
		return out;
	}

	public void insert(int vert, int key) 
	{
	if(size==max) 
		return;

	else
		{
		Node newNode = new Node(vert, key);
		ha[size] = newNode; 
		trickleUp(size++);
		}
	} 

	public void update(int v, int weight)
	{
		for(int i=0; i<size; i++)
		{
			if(ha[i].vert==v)
			{
				ha[i].key = weight;
				trickleUp(i);
				return;

			}
		}

	}


	public void trickleUp(int i) 
	{
	int parentpos = (i-1) / 2; 
	Node last = ha[i];

	while( i > 0 && ha[parentpos].key > last.key)	
	{
	ha[i] = ha[parentpos]; 
	i = parentpos;
	parentpos = (parentpos-1) / 2;
	} 
	ha[i] = last;
	} 

	public Node remove() 
	{ 
	Node root = ha[0];
	ha[0] = ha[--size]; 
	trickleDown(0);
	return root;
	} 

	public void trickleDown(int i) 
	{
	int min;
	Node start = ha[i]; 
	while(i < size/2)
	{
	int left = 2*i+1; 
	int right = left+1;
	if(right < size && ha[left].key > ha[right].key) 
		min = right;
	else
	min = left;

	if( start.key <= ha[min].key )
	break;

	ha[i] = ha[min];
	i = min;
	} 
	ha[i] = start; 
	} 
}

class Vertex
{
	int to;
	int weight;
	Vertex next;

	public Vertex(int to, int weight)
	{
		this.to = to;
		this.weight = weight;
	}
}

class VLinkedList
{
	Vertex head;
	Vertex rear;

	public void insert(int to, int weight)
	{
		if (head==null)
		{
			head = new Vertex(to, weight);
			rear = head;
			
		}
		else
		{
			Vertex newVertex = new Vertex(to, weight);
			newVertex.next = null;
			rear.next = newVertex;
			rear = newVertex;
			return;

		}
	}
}

class Graph
{
	VLinkedList[] a;
	int numV;

	public Graph(int vin)
	{
		a = new VLinkedList[vin+1];
		numV = vin;
		for (int i=1; i<=numV; i++)
		{
			a[i]=new VLinkedList(); 
		}
	}


	public void add(int v1, int v2, int w)
	{
		a[v1].insert(v2, w);		
	}

	public void dijkstra(int src)
	{

		minHeap mH =  new minHeap(numV);
		for (int i=0; i<=numV; i++)
		{
			mH.insert(i+1, Integer.MAX_VALUE);
		
		}

		mH.update(src, 0);

		while (mH.size!=0)
		{	
			Node minH =	mH.remove();
			System.out.println(minH.vert+ " "+ minH.key);

			int u = minH.vert;

			Vertex adj = a[u].head;

			while (adj!=null)
			{
				int v = adj.to;
				Node vindex = mH.find(v);

				if(vindex.vert!=-1 && vindex.key>adj.weight+minH.key)
				{
						
						mH.update(v, adj.weight+minH.key);
				}

				adj = adj.next;		
			 }

		}

	}



}

class lab12App
{
	public static void main(String[] args) throws IOException
	{
		//long st = System.currentTimeMillis();
		Reader rd = new Reader();
		int v = rd.nextInt();

		int src = rd.nextInt();
		int dest = rd.nextInt();
		int t1 = rd.nextInt();
		int t2 = rd.nextInt();
c


		int e = rd.nextInt();

		Graph theGraph = new Graph(v);


		for(int i=0; i<e; i++)
		{
			int v1 = rd.nextInt();
			int v2 = rd.nextInt();
			int w = rd.nextInt();
			theGraph.add(v1, v2, w);
		}
		//theGraph.getDisp();
		theGraph.dijkstra(1);
		


	}
}



