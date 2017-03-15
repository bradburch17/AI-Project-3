import java.util.ArrayList;

public class Solving {

	private ArrayList<Remainder> solving = new ArrayList<Remainder>();
	
	public void addToSolving(Remainder r)
	{
		solving.add(r);
	}
	
	public void removeFromSolving(Remainder r)
	{
		solving.remove(r);
	}
	
	public int getSolvingSize()
	{
		return solving.size();
	}
	
	public boolean isInSolving(int iValue, int jValue)
	{
		for(int i = 0; i < solving.size(); i++)
		{
			Remainder remainders = solving.get(i);
			if (remainders.getiValue() == iValue && remainders.getjValue() == jValue)
			{
				System.out.println("It was in solving!");
				return true;
			}
		}
		
//		getSolving();
		
		return false;
	}
	
	public void getSolving()
	{
		System.out.println("This is what is in solving: ");
		for(Remainder r : solving)
		{
			System.out.println(r.getiValue() + "  " + r.getjValue());
		}
	}
}
