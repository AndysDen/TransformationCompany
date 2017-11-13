package com.transfomation.main;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.transfomation.helper.TransformationConstant;
import com.transfomation.rules.BattleRuleProcessingEngine;
import com.transformation.exception.AllCompetitorsDestroyedException;
import com.transformation.exception.IncorrectInputException;
import com.transformation.exception.SameRankingTransFormerEnounteredException;

/**
 * *******************************************************************************************
 * This is a starting point in application;This class is responsible for following activities 
 * 
 * a. Receive and process Input from System.in. 
 * b. Start the battle between Autobot and Deception. 
 * c. Print out the Winners and Looser of the battle.
 * 
 *  This class uses PriorityQueue to store element to be sorted based on Ranking. Each Element stored in
 *  this queue implements Comparator to achieve this. 
 * 
 *  *******************************************
 * @author anandsoni
 * 
 * 
 *
 */
public class TransformationCompany {
	
	//Below defined dataStructure has been used to store transformer for the teams. 
	private PriorityQueue<TransformerDetails> autoBotTeam;
	private PriorityQueue<TransformerDetails> deceptionTeam;
	
	private int autoBotWinningCounter=0;
	private int deceptionWinningCounter=0;
	private int noOfBattle=0;
	
	
	private List<String> autoBotWinnerList; 
	private List<String> deceptionWinnerList;
	
	private boolean isAllCompetitorsDestroyedExceptionThrown=false;
	
	
	BattleRuleProcessingEngine battleRuleEngine ;
	
	public TransformationCompany(){
		initalizeEnvironment();
	}
	
	private void initalizeEnvironment() {
		 autoBotTeam= new PriorityQueue<TransformerDetails>();
	     deceptionTeam= new PriorityQueue<TransformerDetails>();
	     autoBotWinnerList= new ArrayList<String>();
	     deceptionWinnerList= new ArrayList<String>();
	     battleRuleEngine  = BattleRuleProcessingEngine.getBattleRuleEngineInstance();
		}

	/**
	 * This method is responsible accepting input from the user command prompt[System.in].
	 * This method is coded with following logic :: 
	 * a. This accepts inputs in which each line contains 10 elements separated by delimiter[,],an example of valid Input is [Soundwave, D, 8,9,2,6,7,5,6,10]. 
	 * b. If Incorrect input is provided System will throw IncorrectInputException.
	 * c. To declare end of Input , provide an empty String ended with \n.
	 */
	public void receiveAndprocessInput(){
		
		Scanner sc = new Scanner(System.in);
		String nextLine=null;
		
		while((nextLine=sc.nextLine()) !=null){
		
		nextLine.trim();//Here we are trimming it to remove unnesscary shite space.
		if(nextLine.length()==0){
		//System.out.println("No more input to system hence moving to further processing");
		break;
		}
		String arr[] = nextLine.split(",");
		if(arr.length !=10){
			throw new IncorrectInputException(TransformationConstant.INCORRECTINPUT);
		}
		
		String name=arr[0];
		String team=arr[1].trim();
		int [] matrix= new int[arr.length-2];
		for(int i=2,j=0;i<arr.length;i++,j++ ){
			
			matrix[j]=Integer.parseInt(arr[i].trim());
		}
		
		TransformerDetails transformer = new TransformerDetails(matrix,name,team);
		decideTeamForTransformer(transformer);
		}
		
		
		}

	private void decideTeamForTransformer(TransformerDetails transformer) {
		if(transformer.getTeam().equalsIgnoreCase(TransformationConstant.AUTOBOT)){
			autoBotTeam.add(transformer);
			}else {
				deceptionTeam.add(transformer);
			}
		}
	
	/**
	 * This method is responsible for starting the battle.Following logic is coded in this method: 
	 * a.Extract Autobot from deception from priorityQueue and submit them to RuleEngine to Identify the Winner of the battle.
	 * b. There can be 3 results of the battle :
	 *    AUTOBOT is a winner.
	 *    DECEPTION is a winner. 
	 *    NO WINNER from the battle : This is a TIE UP scenario.
	 *  c. When unequal member are present then transformer with no match are skipped.
	 *  d. Following information will be captured as well : This information will be used to print results.  
	 *     1. Number of battles. 
	 *     2. AutoBotWinner List. 
	 *     3. DeceptionWinner List.  
	 *     
	 */

	public void startBattle() {
		
		
		
		TransformerDetails autoBotTransFormer=null;
		TransformerDetails deceptionTransFormer = null;
		 try {
		while(!(null==autoBotTeam.peek()) && !(null==deceptionTeam.peek())){
			noOfBattle++;
			autoBotTransFormer=autoBotTeam.poll();
			deceptionTransFormer = deceptionTeam.poll();
		/**
		 * This is a scenario  when we have unequal member in both the team. if any one the list becomes empty we will not be able to process further. 
		 */
			String winner=null;
		try{	
		 winner =battleRuleEngine.FindWinner(autoBotTransFormer,deceptionTransFormer);
			
		if(winner.equals(TransformationConstant.AUTOBOT)){
			autoBotWinningCounter++;
			autoBotWinnerList.add(autoBotTransFormer.getName());
		}else if(winner.equals(TransformationConstant.DECEPTION)){
			deceptionWinningCounter++;
			deceptionWinnerList.add(deceptionTransFormer.getName());
		}
		
		}catch(SameRankingTransFormerEnounteredException srtee){
		System.out.println("Found a tie between Autobot :: "+autoBotTransFormer.getName()+" :: And  "+deceptionTransFormer.getName());	
		}
		}
		if(autoBotTeam.peek() ==null){
			while(deceptionTeam.peek()!=null){
				deceptionWinnerList.add(deceptionTeam.poll().getName());	
			}
			
		}else if(deceptionTeam.peek() ==null ){
			while(autoBotTeam.peek()!=null){
			autoBotWinnerList.add(autoBotTeam.poll().getName());
			}
		}	
		 } catch (AllCompetitorsDestroyedException acde) {
			 isAllCompetitorsDestroyedExceptionThrown=true;
	            
	        }
	}
	
	/**
	 * This method is responsible to printing the output based on the requirement.
	 * Exception scenario managed in method is : 
	 * If All the competitors are destroyed then normal output will not be printed. and will
	 * only print one message. 
	 */
	
	public void printWinner() {
		TransformerDetails transformer = null;
		if(isAllCompetitorsDestroyedExceptionThrown){
			System.out.println("ALL COMPETITORS ARE DESTROYED AND WE HAVE NO WINNERS");
			return;
		}
		System.out.println(noOfBattle + " battle");
		if (autoBotWinningCounter > deceptionWinningCounter) {
			System.out.print("Winning team(" + TransformationConstant.AUTOBOTS + "): ");
			// It will display the leftover member names of winning team with a
			// space
			
			for(String name:autoBotWinnerList) {
				System.out.print(name + " ");
		}
			System.out.println();
			System.out.print("Survivors from the losing team (" + TransformationConstant.DECEPTIONS + "): ");
			for(String name:deceptionWinnerList) {
				System.out.print(name + " ");
		}
		} else if (deceptionWinningCounter > autoBotWinningCounter) {
			System.out.print("Winning team(" + TransformationConstant.DECEPTIONS + "): ");
			// It will display the leftover member names of winning team with a
			// space
			for(String name:deceptionWinnerList) {
				System.out.print(name + " ");
		}
			System.out.println();
			System.out.print("Survivors from the losing team (" + TransformationConstant.AUTOBOTS + "): ");
			for(String name:autoBotWinnerList) {
				System.out.print(name + " ");
		}
		}else {
			System.out.println("Equal winning with both team,hence its a TIE");
		}

	}
	
	public PriorityQueue<TransformerDetails> getAutoBotTeam() {
		return autoBotTeam;
	}

	public void setAutoBotTeam(PriorityQueue<TransformerDetails> autoBotTeam) {
		this.autoBotTeam = autoBotTeam;
	}

	public PriorityQueue<TransformerDetails> getDeceptionTeam() {
		return deceptionTeam;
	}

	public void setDeceptionTeam(PriorityQueue<TransformerDetails> deceptionTeam) {
		this.deceptionTeam = deceptionTeam;
	}

	public int getAutoBotWinningCounter() {
		return autoBotWinningCounter;
	}

	public void setAutoBotWinningCounter(int autoBotWinningCounter) {
		this.autoBotWinningCounter = autoBotWinningCounter;
	}

	public int getDeceptionWinningCounter() {
		return deceptionWinningCounter;
	}

	public void setDeceptionWinningCounter(int deceptionWinningCounter) {
		this.deceptionWinningCounter = deceptionWinningCounter;
	}

	public BattleRuleProcessingEngine getBattleRuleEngine() {
		return battleRuleEngine;
	}

	public void setBattleRuleEngine(BattleRuleProcessingEngine battleRuleEngine) {
		this.battleRuleEngine = battleRuleEngine;
	}

	public int getNoOfBattle() {
		return noOfBattle;
	}

	public void setNoOfBattle(int noOfBattle) {
		this.noOfBattle = noOfBattle;
	}
	public List<String> getAutoBotWinnerList() {
		return autoBotWinnerList;
	}

	public void setAutoBotWinnerList(List<String> autoBotWinnerList) {
		this.autoBotWinnerList = autoBotWinnerList;
	}

	public List<String> getDeceptionWinnerList() {
		return deceptionWinnerList;
	}

	public void setDeceptionWinnerList(List<String> deceptionWinnerList) {
		this.deceptionWinnerList = deceptionWinnerList;
	}

	public boolean isAllCompetitorsDestroyedExceptionThrown() {
		return isAllCompetitorsDestroyedExceptionThrown;
	}

	public void setAllCompetitorsDestroyedExceptionThrown(boolean isAllCompetitorsDestroyedExceptionThrown) {
		this.isAllCompetitorsDestroyedExceptionThrown = isAllCompetitorsDestroyedExceptionThrown;
	}



}
