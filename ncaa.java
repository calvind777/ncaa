import java.io.*;
import java.util.*;
public class ncaa
{
	public static void main (String [] args) throws IOException
	{
		BufferedReader buf = new BufferedReader(new FileReader("ak2009.txt"));
		String line;
		//buf.readLine();
		int counter=0;
		ArrayList<String> theTeams=new ArrayList<String>();
		double[] theScores=new double[69];
		
		
		while((line=buf.readLine())!=null)
		{
		
			int asdf=line.indexOf("\t");
		System.out.println(line);
			theTeams.add(line.substring(0,asdf));
			double score=Double.parseDouble(line.substring(asdf+1));
			theScores[counter]=score;
		
		counter++;
		}
	//System.out.println(theTeams);
	BufferedReader buf1 = new BufferedReader(new FileReader("2009.txt"));
	String line1;
	int bracketscore=0;
	int counter1=0;
	ArrayList<String> eachRoundWinner=new ArrayList<String>();
	ArrayList<String> eachRoundLoser=new ArrayList<String>();
	int[][] fakeBracket=new int[63][3];
	int temp=0;
	int temp1=0;
	int temp2=0;
	int temp3=0;
	int temp4=0;
	while((line1=buf1.readLine())!=null)
	{
		System.out.println(line1);
		int round=Integer.parseInt(line1.substring(line1.lastIndexOf(" ")+1));
		
		//System.out.println(line1);
		String winner=line1.substring(0,line1.indexOf(","));
		eachRoundWinner.add(winner);
		
		String loser=line1.substring(line1.indexOf(",")+2,line1.lastIndexOf(","));
		eachRoundLoser.add(loser);
		fakeBracket[counter1][1]=round;
		
		if(counter1<32){
			System.out.println(counter1+" "+loser+winner);
			System.out.println(theTeams+" "+loser);
			System.out.println(theTeams.indexOf(loser));
			System.out.println(winner+" "+loser);
			fakeBracket[counter1][0]=winningTeam(winner,loser,theTeams,theScores);
			
		
		
		//System.out.println(winner+" asdpovnasd "+loser);
	}
		else if(counter1>=32&&counter1<48)
	{
		
		fakeBracket[counter1][0]=winningTeam(theTeams.get(fakeBracket[counter1-32+temp][0]),theTeams.get(fakeBracket[counter1-31+temp][0]),theTeams,theScores);
		temp++;
	}
		else if(counter1>=48&&counter1<56)
		{
			fakeBracket[counter1][0]=winningTeam(theTeams.get(fakeBracket[counter1-16+temp1][0]),theTeams.get(fakeBracket[counter1-15+temp1][0]),theTeams,theScores);
			System.out.println("counter is "+counter1+"SWEET 16 "+theTeams.get(fakeBracket[counter1-16+temp1][0])+" "+theTeams.get(fakeBracket[counter1-15+temp1][0]));
			temp1++;
		}
		else if(counter1>=56&&counter1<60)
		{
			fakeBracket[counter1][0]=winningTeam(theTeams.get(fakeBracket[counter1-8+temp2][0]),theTeams.get(fakeBracket[counter1-7+temp2][0]),theTeams,theScores);
			System.out.println("FINAL EIGHT "+theTeams.get(fakeBracket[counter1-8+temp2][0])+" "+theTeams.get(fakeBracket[counter1-7+temp2][0]));
		
			temp2++;
		}
		else if(counter1>=60&&counter1<62)
		{
			fakeBracket[counter1][0]=winningTeam(theTeams.get(fakeBracket[counter1-4+temp3][0]),theTeams.get(fakeBracket[counter1-3+temp3][0]),theTeams,theScores);
			System.out.println("WINNER OF "+theTeams.get(fakeBracket[counter1-4+temp3][0])+" "+theTeams.get(fakeBracket[counter1-3+temp3][0])+" is "+theTeams.get(winningTeam(theTeams.get(fakeBracket[counter1-4+temp3][0]),theTeams.get(fakeBracket[counter1-3+temp3][0]),theTeams,theScores)));
			temp3++;
			//System.out.println("LAST TWO "+theTeams.get(fakeBracket[counter1-4+temp3][0])+" "+theTeams.get(fakeBracket[counter1-3+temp3][0]));
		}
		else{System.out.println(counter1);
			fakeBracket[counter1][0]=winningTeam(theTeams.get(fakeBracket[counter1-2][0]),theTeams.get(fakeBracket[counter1-1][0]),theTeams,theScores);
		}
		counter1++;
	}
	for(int a=0;a<63;a++)
		System.out.println(theTeams.get(fakeBracket[a][0])+" "+fakeBracket[a][1]);
	//System.out.println(bracketscore);
	bracketscore=0;
	for(int a=0;a<63;a++)
	{
		if(eachRoundWinner.get(a).equals(theTeams.get(fakeBracket[a][0])))
		{
			bracketscore+=bracketScore(fakeBracket[a][1]);
		}
	}
	System.out.println(bracketscore);
	}
	
	public static int bracketScore(int round)
	{
		int x=(int)(Math.log((double) round) / Math.log(2.0));
		return (int) Math.pow(2, 6-x);
		//return (int)Math.pow(2, round-2);
	}
	public static int winningTeam(String a, String b,ArrayList<String> teams,double[] scores)
	{
		int indexOFA=teams.indexOf(a);
		int indexOFB=teams.indexOf(b);
		double scoreA=scores[indexOFA];
		double scoreB=scores[indexOFB];
		if(scoreA-scoreB>0)
			return indexOFA;
		else
			return indexOFB;
	}
}