import java.util.*;
import java.io.*;
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

class Node
{
	public int data;
	public Node left;
	public Node right;

}

class Tree
{
	Node root;
	public Tree()
	{root = null;}

	public void insert(int d)
	{
		Node newNode = new Node();
		newNode.data = d;
		if (root==null)
			root = newNode;
		else
		{
			Node current = root;
			Node parent;
			while(true)
			{
				parent = current;
				if(d<parent.data)
				{
					current = current.left;
					if(current == null)
					{
						parent.left = newNode;
						return;
					}
				}
				if(d>parent.data)
				{
					current = current.right;
					if(current==null)
					{
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
	public Node delete(Node r, int value)
	{
		if (r==null)
			return null;
		if (r.data>value)
			r.left=delete(r.left, value);
		else if (r.data<value)
			r.right = delete(r.right, value);
	
	else
	{
		if(r.left!=null && r.right!=null)
		{
			Node temp = r;
			Node minRight = minNode(temp.right);
			r.data = minRight.data;
			
			r.right= delete(r.right, minRight.data);
		}
		else if (r.left!=null)
			{r = r.left;
	}
		else if (r.right!=null)
			r = r.right;
		else
			r = null;
	}
	return r;
}

public Node minNode(Node r)
{
	if(r.left==null)
		return r;
	else
		return minNode(r.left);
}

	

	public void preOrder(Node r)
	{
		if(r!=null)
		{
			System.out.print(r.data+" ");
			preOrder(r.left);
			preOrder(r.right);
		}
	}

	public void display()
	{
		preOrder(root);
	}

	int height(Node r)
	{
		if(r==null)
			return 0;
		else
		{
			int lh = height(r.left);
			int rh = height(r.right);
			if (lh<rh)
				return(rh+1);
			else return(lh+1);
		}
	}
	int c, n;
	void print(Node r)
	{
		int h = height(r);
		for (int i=1; i<=h; i++)
			{c=0;
		n=1;
			levelOrder(r, i);}


	}
	void levelOrder(Node r, int l)
	{
		if(r==null)
			return;
		if(l==1 && c<n)
			{System.out.print(r.data+" ");
		c=n;}
		else if(l>1)
		{
			levelOrder(r.right, l-1);
			levelOrder(r.left, l-1);
			
		}
	}

}
class App
{
	 public static void main(String[] args) throws IOException
	{
		Reader rd = new Reader();
		int n = rd.nextInt();
		int q = rd.nextInt();

		Tree theTree = new Tree();

		for (int i =0; i<n; i++)
		{
			theTree.insert(rd.nextInt());
		}

		for (int j=0; j<q; j++)
		{
			int queryCode = rd.nextInt();
			if(queryCode==1)
			{
				theTree.root=theTree.delete(theTree.root,rd.nextInt());


			}
			else if(queryCode==2)
			{
				theTree.print(theTree.root);
				System.out.println();

			}
		}

		
	}	
}