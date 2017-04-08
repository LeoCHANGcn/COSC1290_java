import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;


public class Ozlympic {
	private static Scanner input;
	private static int gameSelected;
	private static int predictedWinnerID;
	private final static String prophetMessage="Congratulations! You make a correct prediction for the result!";
	private static Officer[] referee;
	private static Althlete[] athleteData;
	private static Game[] games;
	private static boolean haveChosenGame;
	private static boolean haveSelectedWinner;
	private static boolean predictRightly=false;
	private static boolean exit=true;
	private static Random random;
	private static int[] participants;
	private static int[] currentGameResults;
	
	public static void menu()
	{
		System.out.println("Ozlympic Game");
		System.out.println("=============================================");
		System.out.println("1. Select a game to run");
		System.out.println("2. Predict the winner of the game");
		System.out.println("3. Start the game");
		System.out.println("4. Display the final results of all games");
		System.out.println("5. Display the points of all althletes");
		System.out.println("6. Exit\n");
		System.out.print("Enter an option: ");
	}
	
	public static void delay()
	{
		for(int i=0;i<=3;++i)
		{
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("... ");
		}
		System.out.print("\n");
	}
	
	public static void fakeClear()
	{
		//Try to clear the console in a simple way
		/*for(int i=0;i<30;++i)
			System.out.println("");*/
		//Reference:https://www.quora.com/How-do-I-clear-console-screen-CMD-screen-in-Java-Is-there-any-function-in-Java-like-clrscr-and-system-cls-in-C
		System.out.print("\033[H\033[2J"); 
	}
	
	public static void iniAthlete()
	{
		athleteData=new Althlete[10];
		for(int i=0;i<10;++i)
		{
			athleteData[i]=new Swimmer(i,"swim",i*5,"VIC");
			//athleteData[i].showPerson();
		}
		
		
		
	}
	
	public static void iniReferee()
	{
		referee=new Officer[4];
		for(int i=0;i!=4;++i)
		{
			referee[i]=new Officer(i,"referee"+(i+1),(i+3)*6,"VIC");
			//referee[i].showPerson();
		}
		
	}
	public static boolean isIn(int a,int[] b)
	{
		boolean sign=false;
		for(int i=0;i<b.length;++i)
		{
			if(a==b[i])
			{
				sign=true;
			}
		}
		return sign;
	}
	
	public static void iniGame()
	{
		iniAthlete();
		iniReferee();
		haveChosenGame=false;
		haveSelectedWinner=false;
		gameSelected=0;
		games=new Game[5];
		games[0]=new Game("S01",referee[0],8,"Swimming");
		games[1]=new Game("R01",referee[1],3,"Running");
		games[2]=new Game("C01",referee[2],9,"Cycling");
		games[3]=new Game("S02",referee[3],6,"Swimming");
		games[4]=new Game("S03",referee[1],2,"Swimming");
	}
	public static void selectGame()
	{
		fakeClear();
		System.out.println("Please select a game to run:(Please input the index of the game)");
		for(int i=0;i!=5;++i)
		{
			games[i].showGame();
			System.out.println("GameIndex:"+(i+1));
		}
		gameSelected=input.nextInt();
		if(gameSelected>=1 && gameSelected<=5)
			haveChosenGame=true;
		else
		{
			System.out.println("Wrong Selection!!!\nBack to the main menu in 3 seconds...");
			delay();
		}
	}
	
	public static void predictWinners()
	{
		fakeClear();
		if(haveChosenGame==false)
		{
			System.out.println("Please select a game to run fist.\nBack to the main menu in 3 seconds...");
			delay();
			//break;
		}
		else
		{
			System.out.println("You have chosen "+games[gameSelected-1].getID());
			random=new Random();
			//save some random selected participants in the list
			participants=new int[games[gameSelected-1].getNumOfAlthlete()];
			for (int i=0;i<participants.length;++i)
			{
				participants[i]=random.nextInt(9);
				for(int j=0;j<i;++j)
				{
					//Remove the duplicated numbers for every athlete is unique
					if(participants[j]==participants[i])
					{
						i--;
						//break;
					}
						//--i;
				}
				
			}
			for (int i=0;i<participants.length;++i)
			{
				System.out.println("Index "+participants[i]);
				athleteData[participants[i]].showPerson();
				System.out.println("*********");
			}
			System.out.println("Please enter the your winner(Only one, please input the incex number :)");
			predictedWinnerID=input.nextInt();
			
			if(isIn(predictedWinnerID,participants))
			{
				haveSelectedWinner=true;
				System.out.println("You have chosen "+predictedWinnerID+" as winner.\nBack to main menu in 3 seconds");
			}
			else
			{
				System.out.println("Cannot find the selected Athlete index, back to main menu in 3 seconds...");
			}
			
			delay();
		}
			
	}

	
	public static void runGame()
	{
		fakeClear();
		if(haveChosenGame==false || haveSelectedWinner==false)
		{
			System.out.println("Please make sure you have chosen a game and have predicted a winner...");
			System.out.println("Back to main menu in 3 seconds...");
			delay();
		}
		else if(games[gameSelected-1].getNumOfAlthlete()<=4)
		{
			System.out.println("At least 4 athletes...\nCancelled");
			System.out.println("Back to main menu in 3 seconds...");
			haveChosenGame=false;
			haveSelectedWinner=false;
			delay();
		}
		else if(games[gameSelected-1].getNumOfAlthlete()>8)
		{
			System.out.println("Too many athletes...");
			System.out.println("Back to main menu in 3 seconds...");
			haveChosenGame=false;
			haveSelectedWinner=false;
			delay();
		}
		else
		{
			System.out.println("Started\nRunning..\nComplete!!!\n3 seconds later back to the main menu...");
			//complete time for each athlete
			currentGameResults=new int[participants.length];
			for(int i=0;i<participants.length;++i)
			{
				athleteData[participants[i]].complete();
				currentGameResults[i]=athleteData[participants[i]].getCompleteTime();
			}
			Arrays.sort(currentGameResults);
			for(int i=0;i<participants.length;++i)
			{
				
				if(athleteData[participants[i]].getCompleteTime()==currentGameResults[0])
				{
					athleteData[participants[i]].setCurrentPoinnts(5);
					athleteData[participants[i]].setTotalPoints();
					if(predictedWinnerID==participants[i])
						predictRightly=true;
				}
				else if(athleteData[participants[i]].getCompleteTime()==currentGameResults[1])
				{
					athleteData[participants[i]].setCurrentPoinnts(2);
					athleteData[participants[i]].setTotalPoints();
					if(predictedWinnerID==participants[i])
						predictRightly=true;
				}
				else if(athleteData[participants[i]].getCompleteTime()==currentGameResults[2])
				{
					athleteData[participants[i]].setCurrentPoinnts(1);
					athleteData[participants[i]].setTotalPoints();
					if(predictedWinnerID==participants[i])
						predictRightly=true;
				}
				else
				{
					athleteData[participants[i]].setCurrentPoinnts(0);
					athleteData[participants[i]].setTotalPoints();
					//predictRightly=true;
				}
			}
			System.out.println("AthleteID  currentPoints  totalPoints");
			for(int i=0;i<participants.length;++i)
			{
				System.out.println(athleteData[participants[i]].getID()+"            "+athleteData[participants[i]].getCurrentPoinnts()+"            "+athleteData[participants[i]].getTotalPoints());
			}
			if(predictRightly)
			{
				System.out.println(prophetMessage);
			}
			haveChosenGame=false;
			haveSelectedWinner=false;
			predictRightly=false;
			for(int i=0;i<participants.length;++i)
				System.out.println(currentGameResults[i]);
			delay();
		}
		
		
	}
	
	public static void displayAllGames()
	{
		fakeClear();
		System.out.println("Final results of all games");
		System.out.println("\n*****\nWant to back to main menu?(y or n)");
		//String s=input.next();
		while(!input.next().equals("y"))
		{
			System.out.println("\n*****\nWant to back to main menu?(y or n)");
		}
		System.out.println("Back to main menu in 3 seconds...");
	}
	public static void displayAllPoints()
	{
		fakeClear();
		System.out.println("Final points of all athletes:");
		for(int i=0;i<athleteData.length;++i)
		{
			athleteData[i].showPerson();
			System.out.println("Total points: "+athleteData[i].getTotalPoints());
		}
		System.out.println("\n*****\nWant to back to main menu?(y or n)");
		//String s=input.next();
		while(!input.next().equals("y"))
		{
			System.out.println("\n*****\nWant to back to main menu?(y or n)");
		}
		System.out.println("Back to main menu in 3 seconds...");
	}
	public static void exitGame()
	{
		System.out.println("If quit, all the game results will be lost.");
		System.out.print("Really want to quit?(y or n)");
		String quit=input.next();
		if (quit.equals("y"))
		{
			exit=false;
		}
	}
	public static void main(String args[])
	{
		//prepare some games ready to run; 
		iniGame();
		input = new Scanner(System.in);
		
		while(exit)
		{
			fakeClear();
			menu();
			
			int option=input.nextInt();
			switch(option)
			{
			case 1:
				selectGame();	
				break;
			case 2:
				predictWinners();
				break;
			case 3:
				runGame();
				break;
			case 4:
				displayAllGames();
				delay();
				break;
			case 5:
				displayAllPoints();
				delay();
				break;
			case 6:
				exitGame();
				break;
			default:
				System.out.println("Wrong option!!!\nPlease check your input and do it again!");
				delay();
				continue;
				//break;	
			}
			
		}
		System.out.println("\n***\nGame Over! If want to play again, please rerun the program.");
		
		

	}

}