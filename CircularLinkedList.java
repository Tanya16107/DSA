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


class Link
{
    public int Data;
    public Link next;

    public Link(int Data)
    {
        this.Data = Data;
    }
}

class LinkedList
{
    public Link current;
    public int nItems;

    public LinkedList()
    {
        nItems = 0;
        current = null;
    }

public void insert(int value)
{
    if(current==null)
    {
        current = new Link(value);
        current.next = current;
    }
    else
    {
        Link new_Link = new Link(value);
        new_Link.next = current.next;
        current.next = new_Link;
        current = new_Link;
    }
    nItems++;
}

public void play()
{
    if (current==null)
    {
        System.out.println("No players.");
    }
    else
    {
        Link index = current;
        index = index.next;
        while (nItems!=1)
            {
                index.next = index.next.next;
                nItems--;
                index = index.next;
            }
            System.out.println(index.Data);
    }
}

}

class Balloons
{
    public static void main(String[] args) throws IOException
    {
        Reader rd= new Reader();

        int T = rd.nextInt(); 
        for (int j = 1; j<=T; j++) 
        {
        LinkedList theList = new LinkedList();
        int N = rd.nextInt();
        for (int i=1; i <=N; i++)
        {
            theList.insert(i);
        }
 
        theList.play();
        
        }   
    }
}

