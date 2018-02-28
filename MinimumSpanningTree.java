
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

	public void disp()
	{
		Vertex cur = head;
		while (cur!=null)
		{
			System.out.print(cur.to + " " + cur.weight + ",");
			cur = cur.next;
		}
	}

	public int findl(int wt)
	{
		Vertex cur = head;
		int out =0;;
		while (cur!=null)
		{
			if(cur.weight==wt)
			{
				out=cur.to;
				break;
			}

			else
			{

			cur = cur.next;
		}
		}	
		return out;	

	}

	public int markvisited(int wt)
	{
		Vertex cur = head;
		int out =0;;
		while (cur!=null)
		{
			if(cur.weight==wt)
			{
				out=cur.to;
				head=head.next;
				break;
			}
			else if (cur.next.weight==wt)
			{
				out=cur.next.to;
					
				cur.next=cur.next.next;
				if(cur.next==null)
				{
					rear = cur;
				}
				break;
			}
			else
			{

			cur = cur.next;
		}
		}	
		return out;	
	}

}

class Graph
{
	VLinkedList[] a;
	int numV;

	Node[] inMST ;
	int sizeinMST;

		minHeap mHG ;
		

	public Graph(int vin)
	{
		a = new VLinkedList[vin+1];
		numV = vin;
		for (int i=1; i<=numV; i++)
		{
			a[i]=new VLinkedList(); 
		}
		inMST = new Node[vin];
		sizeinMST =0;

		mHG = new minHeap(numV);
		for (int i=0; i<numV; i++)
		{
			if(i==0)
			{
				mHG.insert(1,0);
			}

			else
			{
			mHG.insert(i+1, Integer.MAX_VALUE);
		}
		}


	}


	public void add(int v1, int v2, int w)
	{
		a[v1].insert(v2, w);
		//System.out.println("done1");
		a[v2].insert(v1, w);

		//System.out.println("done1");				
	}

	public void getDisp()
	{

	for(int i=1; i<=numV; i++)
		{
			System.out.print(i+ "->");
			a[i].disp();
			System.out.println();
		}
	}

	

	public Integer mst(int to1, int to2, int ed)
	{
		int tWeight=0;
		minHeap mH = mHG;



		/*for (int i=0; i<numV; i++)
		{
			System.out.println(mH.ha[i].vert+ "-"+mH.ha[i].key+ ", " );
		}*/

		while (mH.size!=0)
		{	
			Node minH =	mH.remove();

			if(minH.vert!=1 && sizeinMST<numV-1)
			{inMST[sizeinMST]=minH;
			//System.out.println(sizeinMST);
			sizeinMST+=1;
	//System.out.println(sizeinMST);
		}
		
		

			//System.out.println(sizeinMST);
			//System.out.println(minH.vert+ "-"+minH.key);
			if(minH.key!=Integer.MAX_VALUE)
			{tWeight+=minH.key;}

			/*if(minH.vert!=1)
			{int y = a[minH.vert].markvisited(minH.key);
				//System.out.println(y);
			a[y].markvisited(minH.key);}*/
			
			int u = minH.vert;

			Vertex adj = a[u].head;

			while (adj!=null)
			{
				int v = adj.to;
				Node vindex = mH.find(v);

				if(vindex.vert!=-1)
				{
					if (vindex.key>adj.weight && adj.weight!=ed)
					{
						mH.update(v, adj.weight);
					}
				}

			adj = adj.next;
			}


		}

		return(tWeight);


	}

	

	public Integer smst()
	{	
		int answer = Integer.MAX_VALUE;

		int lmst = mst(0,0,0);
		System.out.println(lmst);
		

		for(int i=0; i<sizeinMST; i++)
		{
			Node lol = inMST[i];

			int f = lol.vert;
			//System.out.println(f);
			int h = lol.key;

			//System.out.println(h);
			int g = a[lol.vert].findl(h);

			//System.out.println(g);			


		//int y = a[lol.vert].markvisited(lol.key);
		
		//a[y].markvisited(lol.key);

		//System.out.println();
		//getDisp();
		
		int stweight = mst(f,g,h);
		System.out.println(stweight);


	


		if(stweight<answer && stweight>lmst)
		{
			answer = stweight;

		//System.out.println(answer);
		}

		//System.out.println("v1= "+y);

		//System.out.println("v2= "+lol.vert);

		//System.out.println("w= "+lol.key);
		//add(y, lol.vert, lol.key);
		//getDisp();

	
	}

		if(answer==lmst || answer == Integer.MAX_VALUE || answer==0)
		{
			answer = -1;
		}
		return(answer);

	}



}
class lab11App
{
	public static void main(String[] args) throws IOException
	{
		//long st = System.currentTimeMillis();
		Reader rd = new Reader();
		int v = rd.nextInt();
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
		//int lmst = theGraph.mst();
		System.out.println(theGraph.smst());

		//System.out.println(theGraph.smst());




	}
}

class Reader
{

    BufferedReader br;
    StringTokenizer tk;
    Reader() throws IOException
    {
        br=new BufferedReader(new InputStreamReader(System.in));
        tk=new StringTokenizer("");
    }
    public int nextInt() throws IOException
    {
        while(!tk.hasMoreTokens())
        {
            tk=new StringTokenizer(br.readLine().replaceAll("\r",""));
        }
        return Integer.parseInt(tk.nextToken());
    }
    public String next() throws IOException
    {
        return br.readLine();
    }
}