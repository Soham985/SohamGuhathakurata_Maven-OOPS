package com.task.maven.EpamTask;

import java.util.*;

class Gift
{
	String name;
	int price;
	static int cost=0;
	static Map< String, Integer > items=new HashMap< String,Integer >();
	
	static int Bought(String name, int quantity, int price)
	{
		items.put(name,new Integer(quantity));
		cost+=quantity*price;
		return quantity*price;
	}
	static void GiftBox()
	{
		System.out.println("Items Bought:");
		Set< Map.Entry< String,Integer> > i = items.entrySet(); 
		for(Map.Entry<String,Integer> m:i)
		{
			System.out.println(m.getKey()+" : "+m.getValue()+" packets");
		}
		System.out.println("Total Cost:"+cost);
	}

}

class Chocolates extends Gift implements Comparable<Chocolates>
{
	float weight;
	Chocolates(String name,float weight,int price)
	{
		this.name=name;
		this.weight=weight;
		this.price=price;
	}
	static void Display(Chocolates chocolates[], int amount)
	{
		Arrays.sort(chocolates);
		System.out.println("Chocolates Available:");
		for(int i=0;i<chocolates.length;i++)
		{
			if(chocolates[i].price<=amount)
			{
				System.out.println("Name:"+chocolates[i].name);
				System.out.println("Price:"+chocolates[i].price);
				System.out.println("Weight:"+chocolates[i].weight+"g");
				
			}
			else
				break;
			System.out.println();
		}
	}
	static int Purchase(int budget,String name, int quantity,Chocolates chocolates[])
	{
		for(Chocolates c:chocolates)
		{
			if(c.name.equalsIgnoreCase(name))
			{
				if(quantity*c.price>budget)
				{	System.out.println("Cannot buy that much of "+c.name);
					return 0;
				}
				else
				{
					int cost=Gift.Bought(name, quantity,c.price);
					return cost;
				}
			}
		}
		return 0;
	}
	public int compareTo(Chocolates chocolates)
	{
		return price-chocolates.price;
	}
}

class Candies extends Gift implements Comparable<Candies>
{
	Candies(String name,int price)
	{
		this.name=name;
		this.price=price;
	}
	static void Display(Candies candies[],int amount)
	{
		Arrays.sort(candies);
		System.out.println("Candies Available:");
		for(int i=0;i<candies.length;i++)
		{
			if(candies[i].price<=amount)
			{
				System.out.println("Name:"+candies[i].name);
				System.out.println("Price:"+candies[i].price);
				
			}
			else
				break;
			System.out.println();
		}
	}
	static int Purchase(int budget,String name, int quantity, Candies candies[])
	{
		for(Candies c:candies)
		{
			if(c.name.equalsIgnoreCase(name))
			{
				if(quantity*c.price>budget)
				{
					System.out.println("Cannot buy that much of "+c.name);
					return 0;
				}
				else
				{
					int cost=Gift.Bought(name, quantity, c.price);
					return cost;
				}
			}
		}
		return 0;
	}
	public int compareTo(Candies candies)
	{
		return price-candies.price;
	}
}



public class App 
{
    public static void main( String[] args )
    {
    	Chocolates choco1=new Chocolates("Cadbury Silk", 60, 50);
    	Chocolates choco2=new Chocolates("Bournville", 80 , 85);
    	Chocolates choco3=new Chocolates("5 Star", 40, 20);
    	Chocolates choco4=new Chocolates("Perk", 13 , 10);
    	Chocolates choco5=new Chocolates("KitKat", 40 , 25);
        Chocolates chocolates[]= {choco1,choco2,choco3,choco4,choco5};
        
        Candies can1=new Candies("Pulse",1);
        Candies can2=new Candies("Eclairs",2);
        Candies can3=new Candies("Kofeeko",1);
        Candies can4=new Candies("Marbles",5);
        Candies can5=new Candies("Candiman",3);
        Candies candies[]= {can1,can2,can3,can4,can5};
        
        
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your budget");
        int budget=sc.nextInt();
        String name;
        int quantity,cost=0;
        while(true)
        {
        	System.out.print("Press 1 to buy chocolates\nPress 2 to buy candies\n");
        	int choice=sc.nextInt();
        	switch(choice)
        	{
        		case 1:
        			Chocolates.Display(chocolates,budget);
                    System.out.print("Enter the name of the chocolate to buy:");
                    sc.nextLine();
        			name=sc.nextLine();
        			System.out.print("Enter the quantity:");
        			quantity=sc.nextInt();
        			cost=Chocolates.Purchase(budget, name, quantity,chocolates);
        			break;
        		case 2:
        			Candies.Display(candies,budget);
                    System.out.print("Enter the name of the candy to buy:");
                    sc.nextLine();
        			name=sc.nextLine();
        			System.out.print("Enter the quantity:");
        			quantity=sc.nextInt();
        			cost=Candies.Purchase(budget, name, quantity,candies);
        			break;
        	}
        	budget-=cost;
        	System.out.println("Remaining Budget: "+budget);
        	System.out.print("Press Y to continue shopping and N to collect your products:");
        	char s=sc.next().charAt(0);
        	if(s=='N' || s=='n')
        	{
        		Gift.GiftBox();
        		System.exit(0);
        	}
        	
        }
       
    }
}
