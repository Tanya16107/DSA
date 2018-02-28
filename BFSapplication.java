
import java.io.*;
import java.util.*;

class Vertex
{
	public Integer data;
	Vertex next;

	public Vertex(Integer data)
	{
		this.data = data;
	}
	
} 

class VLinkedList
{
	Vertex head;
	Vertex rear;

	public void insert(int value)
	{
		if (head==null)
		{
			head = new Vertex(value);
			rear = head;
			
		}
		else
		{
			Vertex newVertex = new Vertex(value);
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
			System.out.print(cur.data + " ");
			cur = cur.next;
		}
	}


	public int traverse(int[] arr, VLinkedList[] list)
	{

		int c=0;
		Vertex cur = head;
		while(cur!=null)
		{
			if(arr[(cur.data)]==0)
			{
				c+=1;
				arr[(cur.data)]=1;
				c+=list[cur.data].traverse(arr,list);

			}
			cur = cur.next;
		}
		return c;
	}


}





class lab10App
{
	public static void main(String[] args) throws IOException
	{
		Reader rd = new Reader();
		Integer numPages = rd.nextInt();
		Integer numLinks = rd.nextInt();
		VLinkedList[] a = new VLinkedList[numPages+1];
		for (int i=1; i<=numPages; i++)
		{
			a[i]=new VLinkedList(); //THIS
			a[i].insert(i);
		}


		for (int i=0; i<numLinks; i++)
		{
			Integer x = rd.nextInt();
			Integer y = rd.nextInt();
			a[x].insert(y);
			a[y].insert(x);
		}

		/*for(int i=1; i<=numPages; i++)
		{
			a[i].disp();
			System.out.println();
		}*/

		int nDomains = 0;
		int[] varr = new int[numPages+1];
		int[] out = new int[numPages];
		int outc = 0;

		for(int i=1; i<=numPages; i++)
		{   int npagesinDomain = 0;
			if (varr[i]==0)
			{
				nDomains+=1;
				npagesinDomain+=a[i].traverse(varr, a);
				out[outc] = npagesinDomain;
				outc++;

			}


		}
		System.out.println(nDomains);
		
		Arrays.sort(out);
		for (int i=0; i<numPages; i++)
		{
			if(out[i]!=0)
			{
				System.out.print(out[i]+" ");
			}
		}




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