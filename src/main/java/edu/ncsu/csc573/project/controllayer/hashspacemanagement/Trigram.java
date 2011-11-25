package edu.ncsu.csc573.project.controllayer.hashspacemanagement;
import java.io.*;

public class Trigram {

	byte a[];
	public static void main(String[] args)throws Exception 
	{
		
		int tri_count=0;
		Trigram t=new Trigram();
		String store_trigram[]=new String[(int)Math.pow(36,3)];
		int trigram_count[]=new int[(int)Math.pow(36,3)];
		//String actual_trigram[][]=new String[(int)Math.pow(36,3)][2];
		File inp=new File("dict.txt");
		FileOutputStream out;//for reading file
		FileInputStream fin=new FileInputStream(inp);//for writing file as output
		File write_trigram=new File("trigram_with_count.txt");
		
		String read=t.readfile(fin);//reading file in
		
		String word_split[]=read.split(" ");//creating each word as an entry into an array 
		
		t.create_trigrams(store_trigram);//creating trigram dictionary
		
		t.find_relevant_trigram(word_split, store_trigram,trigram_count);//find relevant trigrams from the dictionary
		
			
		
			
			tri_count=t.calc_no_trigrams(trigram_count);//calculate no of trigrams greater than a certain value we can change that
			System.out.println("Number of relevant trigrams is "+tri_count);
			
			t.bubble_sort(trigram_count,store_trigram);//sorting trigrams according to the frequency of occurrence
			
			out=new FileOutputStream(write_trigram);//for writing file
			
			t.write_file_2(out, store_trigram, trigram_count);//writing trigrams to file
			
			write_trigram=new File("trigram_7400.txt"); 
			
			out=new FileOutputStream(write_trigram);//for writing file
			
			t.write_7400_trigrams(out, store_trigram, trigram_count);//writing most frequent 7400 trigrams
			
			
			
	}

	String readfile(FileInputStream f)throws Exception
	{
		String inp="";
		int c=f.read();
		while(c!=-1)
		{
		if(c!=13&&c!=10)
			inp=inp+(char)c;
		else if (c==32)
			continue;
		else
			inp=inp+" ";
			c=f.read();
		}
		
		return inp;
	}
	
	void create_trigrams(String store_trigram[])
	{
		String create[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
		int count=0;
		
		for(int x=0;x<create.length;x++)
			for(int y=0;y<create.length;y++)
				for(int z=0;z<create.length;z++)
					store_trigram[count++]=create[x]+create[y]+create[z];
		
	}
	
	void write_file(FileOutputStream fo, String store[])throws Exception
	{
		for(int i=0;i<store.length;i++)
		{
			for(int k=0;k<store[i].length();k++)
					{
				fo.write(store[i].charAt(k));
					}
			fo.write(9);
			fo.write((char)10);	
				
		}
		
	}
	
	void write_file_2(FileOutputStream fo, String[] store, int count[])throws Exception
	{
		for(int i=0;i<store.length;i++)
		{
			if(count[i]!=0)
			{
			for(int k=0;k<store[i].length();k++)
					{
				fo.write(store[i].charAt(k));
					}
			fo.write(9);
			a=(count[i]+"").getBytes();
			fo.write(a);
			fo.write((char)10);	
			}	
		}
		
	}
	
	
	
	
	void find_relevant_trigram(String[] dict, String[] store_trigram, int trigram_count[] )
	{
		String trial="";
		int count=0;
		for(int i=0;i<dict.length;i++)
		{
			for(int j=0;j<dict[i].length()-2;j++)
			{
				trial=dict[i].substring(j,j+3);
				count=0;
				while(true)
				{
					if(count==(int)Math.pow(36,3))
						break;
					else if(trial.equalsIgnoreCase(store_trigram[count])==true)
						{
						trigram_count[count]++;
						break;
						}
					else
						count++;
				}
						
			}
				
		}
		
	}

	int calc_no_trigrams(int trigram_count[])
	{
		int k=0;
		for(int i=0;i<trigram_count.length;i++)
			if(trigram_count[i]>5)
				k++;
		return k;
	}
	
	void bubble_sort(int trigram_count[], String store_trigram[])
	{
		
		for(int i=0;i<trigram_count.length;i++)
			for(int j=0;j<trigram_count.length;j++)
			{
				int t;
				String temp;
				if(trigram_count[i]>trigram_count[j])
				{
					t=trigram_count[i];
					trigram_count[i]=trigram_count[j];
					trigram_count[j]=t;
					temp=store_trigram[i];
					store_trigram[i]=store_trigram[j];
					store_trigram[j]=temp;
				}
				
			}
		
	}
	
	void write_7400_trigrams(FileOutputStream fo,String[] store_trigram, int trigram_count[])throws Exception
	{
		for(int i=0;i<store_trigram.length;i++)
		{
			if(trigram_count[i]!=0)
			{
			for(int k=0;k<store_trigram[i].length();k++)
					{
				fo.write(store_trigram[i].charAt(k));
					}
			fo.write(9);
			a=(trigram_count[i]+"").getBytes();
			fo.write(a);
			fo.write((char)10);	
			}	
		}
		
		
	}
	
	
}

