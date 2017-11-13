package test.com.transformation.main;
import java.io.ByteArrayInputStream;

import com.transfomation.helper.TransformationConstant;
import com.transfomation.main.TransformationCompany;

import junit.framework.TestCase;

/**
	 * Common scenario across all the test cases.
	 *    Case 1 : Some common cases to be verified across all the nodes,team sorting is done appropiatly.
	 *    Case 2 : Team With maximum Elimination Will win the match 
	 *    
	 *   
	 *   Particular scenario.
 * Case 1 : TestCases AutoBot and Deception contains equal members and they have a tie.
 * Case 2 : If any fighter is down by 4 points on courage and 3 points on strength looses the fight.This case should also test the vice-versa 
 * Case 3:  If Case 2 is draw and any fighter is 3 points or more on skills he will automatically win the battle. 
 * Case 4 : If Case 2 and Case 3 does not Qualify declare the result on Ranking.
 * Case 5 : If Ranking is a Tie then It will be counted In battle but No winner will be declared. 
 * Case 6 : When team are unequal then members with no opposing pair will be skipped. and fight will be over. 
 * 
 * ************************************************SPECIAL SCENARIO************************************************************************************** 
 * Case 7 : Special Scenarios : If any team member has name Optimus prime or predaking , the player would win the battle. 
 * Case 8 : If player names Optimus Prime or Predaking fight against player name the same or either then entire battle would stop Immediatly 
 *          all players will be destroyed and No winners and no loosers. 
 * *************************************************************************************************************************************************         
 *  
 * 
 * 
 * 
 */

public class TransformationCompanyTest extends TestCase{
	
	protected TransformationCompany transformationCompany;
	public void setUp(){
		transformationCompany = new TransformationCompany();
		
		
	}
	
	
/****************************************************SPECIAL SCENARIO************************************************************************************** 
* Case 7 : Special Scenarios : If any team member has name Optimus prime or predaking , the player would win the battle. 
* Case 8 : If player names Optimus Prime or Predaking fight against player name the same or either then entire battle would stop Immediatly 
*          all players will be destroyed and No winners and no loosers. 
* *************************************************************************************************************************************************
* */	
	
	public void testIfPlayerNameIsOptimusPrimethenPlayerWillWinTheBattle(){
		ByteArrayInputStream in = new ByteArrayInputStream((TransformationConstant.OPTIMUS_PRIME+", D, 8,9,2,6,7,5,6,10\r\n"+
				"Bluestreak, A, 6,6,7,9,5,2,9,7\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
		assertEquals(1, transformationCompany.getNoOfBattle());
		//battles won by Deception team 
		assertTrue(transformationCompany.getDeceptionWinningCounter()>transformationCompany.getAutoBotWinningCounter());
		//No Survivor loosing team
		assertEquals(0,transformationCompany.getAutoBotWinnerList().size() );
		//Total Member In winning team
		assertEquals(1,transformationCompany.getDeceptionWinnerList().size() );
		//MemeberName of the Winning Team
		assertEquals(TransformationConstant.OPTIMUS_PRIME, transformationCompany.getDeceptionWinnerList().get(0));
		
		
		//transformationCompany.printWinner();
	}
	
	public void testIfPlayerNameIsOptimusPrimeAndOponentNameisPREDAKINGthenAllPlayersAreDestroyed(){
		ByteArrayInputStream in = new ByteArrayInputStream((TransformationConstant.OPTIMUS_PRIME+", D, 8,9,2,6,7,5,6,10\r\n"+
				TransformationConstant.PREDAKING+", A, 6,6,7,9,5,2,9,7\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
		assertEquals(1, transformationCompany.getNoOfBattle());
		//No one has win the battle
		assertEquals(transformationCompany.getAutoBotWinningCounter(), transformationCompany.getDeceptionWinningCounter());
		//No Survivor Autobot team
		assertEquals(0,transformationCompany.getAutoBotWinnerList().size() );
		//No Member in Deception team
		assertEquals(0,transformationCompany.getDeceptionWinnerList().size() );
		
	
	}
	
	public void testIfPlayerNameIsPREDAKINGInOneOfTheTeam(){
		ByteArrayInputStream in = new ByteArrayInputStream((TransformationConstant.PREDAKING+", A, 8,9,2,6,7,5,6,10\r\n"+
				"Bluestreak, D, 6,6,7,9,5,2,9,7\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
		assertEquals(1, transformationCompany.getNoOfBattle());
		//battles won by Autobot team 
		assertTrue(transformationCompany.getAutoBotWinningCounter()>transformationCompany.getDeceptionWinningCounter());
		//No Survivor loosing team
		assertEquals(0,transformationCompany.getDeceptionWinnerList().size() );
		//Total Member In winning team
		assertEquals(1,transformationCompany.getAutoBotWinnerList().size() );
		//MemeberName of the Winning Team
		assertEquals(TransformationConstant.PREDAKING, transformationCompany.getAutoBotWinnerList().get(0));
	 //To print output in required format
		transformationCompany.printWinner();	
	}
	/**
	 * Case  : TestCases AutoBot and Deception contains equal members and they have a tie.
	 *   	   In the below Testcase no AutoBot will win the battle.hence there will be tie.
	 */
	
	public void testIfBothTeamContainsEqualNumberOfMembersAndWeHaveATie(){
		ByteArrayInputStream in = new ByteArrayInputStream(("Hubcap, A, 8,9,2,6,7,5,6,10\r\n"+
				"Bluestreak, D, 8,9,2,6,7,5,6,10\r\n"+
				"Soundwave, D, 6,6,7,9,5,2,9,7\r\n"+
				"XXY, A, 6,6,7,9,5,2,9,7\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
		assertEquals(2, transformationCompany.getNoOfBattle());
		//No battle won by each team 
		assertTrue(transformationCompany.getAutoBotWinningCounter()==transformationCompany.getDeceptionWinningCounter());
		//No Survivor Deception team
		assertEquals(0,transformationCompany.getDeceptionWinnerList().size() );
		//Total Member Autobot team
		assertEquals(0,transformationCompany.getAutoBotWinnerList().size() );
		transformationCompany.printWinner();
		
	}
	/**
	 * Case  : If any fighter is down by 4 points on courage and 3 points on strength looses the fight.This case should also test the vice-versa
	 *   	   
	 */
	public void test4pointDownOnCourageAnd3pointOnStrengthRuleForTransformer(){
		ByteArrayInputStream in = new ByteArrayInputStream(("Soundwave, D, 8,9,2,6,7,5,6,10\r\n"+
				"Bluestreak, A, 6,6,7,9,5,2,9,7\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
		assertEquals(1, transformationCompany.getNoOfBattle());
		//Battles 
		assertTrue(transformationCompany.getAutoBotWinningCounter() <transformationCompany.getDeceptionWinningCounter());
		//No Survivor Deception team
		assertEquals(1,transformationCompany.getDeceptionWinnerList().size() );
		//Total Member Autobot team
		assertEquals(0,transformationCompany.getAutoBotWinnerList().size() );
		
		transformationCompany.printWinner();
		
	}
	
	/**
	 * Case  : If Case 2 is draw and any fighter is 3 points or more on skills he will automatically win the battle
	 *   	   
	 */
	public void test3pointMoreOnSkillsMakesTransFormerTheWinner(){
		ByteArrayInputStream in = new ByteArrayInputStream(("Soundwave, A, 8,9,2,6,7,5,6,10\r\n"+
				"Bluestreak, D, 6,6,7,9,5,7,9,7\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
		assertEquals(1, transformationCompany.getNoOfBattle());
		//Battles 
		assertTrue(transformationCompany.getDeceptionWinningCounter() <transformationCompany.getAutoBotWinningCounter());
		//No Survivor Deception team
		assertEquals(1,transformationCompany.getAutoBotWinnerList().size() );
		//Total Member Autobot team
		assertEquals(0,transformationCompany.getDeceptionWinnerList().size() );
		
		transformationCompany.printWinner();
		
	}
	
	/**
	 * Case 4 : If Case 2 and Case 3 does not Qualify declare the result on Ranking.
	 * Rank =STRENGTH+ INTELLIGENCE +SPEED+ENDURANCE+FIREPOWER;
	 *   	   
	 */
	public void testTransFormerWithHigherRankingWillBeDeclaredAsWinner(){
		ByteArrayInputStream in = new ByteArrayInputStream(("Soundwave, A, 8,9,2,6,7,5,6,10\r\n"+
				"Bluestreak, D, 6,6,7,9,5,7,9,10\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
				assertEquals(1, transformationCompany.getNoOfBattle());
				//Battles 
				assertTrue(transformationCompany.getDeceptionWinningCounter() >transformationCompany.getAutoBotWinningCounter());
				
				assertEquals(0,transformationCompany.getAutoBotWinnerList().size() );
				
				assertEquals(1,transformationCompany.getDeceptionWinnerList().size() );
				
				transformationCompany.printWinner();
		
	}
	
	/**
	 * Case 5 : If Ranking is a Tie then It will be counted In battle but No winner will be declared.
	 * Rank =STRENGTH+ INTELLIGENCE +SPEED+ENDURANCE+FIREPOWER;
	 *   	   
	 */
	public void testTransFormerFightWhenRankingIsSame(){
		ByteArrayInputStream in = new ByteArrayInputStream(("Soundwave, A, 8,9,6,6,7,5,8,10\r\n"+
				"Bluestreak, D, 6,6,7,9,5,7,9,10\r\n"+
				"\r\n").getBytes());
		System.setIn(in);
		transformationCompany.receiveAndprocessInput();
		transformationCompany.startBattle();
		//Total Number of Battles 
				assertEquals(1, transformationCompany.getNoOfBattle());
				//Battles 
				assertTrue(transformationCompany.getDeceptionWinningCounter() ==transformationCompany.getAutoBotWinningCounter());
				
				assertEquals(0,transformationCompany.getAutoBotWinnerList().size() );
				
				assertEquals(0,transformationCompany.getDeceptionWinnerList().size() );
				
				transformationCompany.printWinner();
		
	}
	/**
	 * Case 6 : When team are unequal then members with no opposing pair will be skipped. and fight will be over.
	 */
	
	public void testTeamMemberAreSkippedFromFightWhenTeamContainUnequalPairs(){
		ByteArrayInputStream in = new ByteArrayInputStream(("Soundwave, D, 8,9,2,6,7,5,6,10\r\n"+
															"Bluestreak, A, 6,6,7,9,5,2,9,7\r\n"
															+"Hubcap, A, 4,4,4,4,4,4,4,4\r\n"+
															"\r\n").getBytes());
		System.setIn(in);

		transformationCompany.receiveAndprocessInput();
		
		transformationCompany.startBattle();
		//Total Number of Battles 
		assertEquals(1, transformationCompany.getNoOfBattle());
		//battles won by Deception team 
		assertTrue(transformationCompany.getDeceptionWinningCounter()>transformationCompany.getAutoBotWinningCounter());
		//Total Member in loosing team
		assertEquals(1,transformationCompany.getAutoBotWinnerList().size() );
		//Total Member in wining team
		assertEquals(1, transformationCompany.getDeceptionWinnerList().size());
		//MemeberName of the Winning Team
		assertEquals("Soundwave", transformationCompany.getDeceptionWinnerList().get(0));
		//Survivour of Loosing team
		assertEquals("Hubcap", transformationCompany.getAutoBotWinnerList().get(0));
		
	}
	
	
 	
	

}
