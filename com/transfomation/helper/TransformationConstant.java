package com.transfomation.helper;
/**
 * This class is been created to maintain centralized constant for the entire application, 
 * Doing so we are able to standardize Names and be assured of not declare 
 * them again and again as entire application refers to constants from this location. 
 *
 * @author anandsoni
 *
 */

public class TransformationConstant {
	//Different messages which will be displayed when an exception is caught. 
	public static final String INCORRECTINPUT="Incorrect input provided,Please provide correct Input";
	public static final String NO_WINNER_FOUND_MESSAGE="no winner found Optimus prime or Predaking faced each other in battle";
	public static final String SAME_RANKING_EXCEPTION_MESSAGE = "Found Transformer of same ranking,hence both will be destroyed.";
	
	//This are mapping of the qualities with ArrayIndexes
	public  static final int STRENGTH =0;
	public  static final int INTELLIGENCE=1;
	public  static final int SPEED=2;
	public static final int ENDURANCE=3;
	public static final int RANK=4;
	public static final int COURAGE=5;
	public static final int FIREPOWER=6;
	public static final int SKILL=7;
	
	//These are the type of team exist in system
	public static final String AUTOBOT="A";
	public static final String DECEPTION="D";
	
	//This constants are declared for special condition 
	public static final String OPTIMUS_PRIME="Optimus Prime";
	public static final String PREDAKING="Predaking";

	//This constant will be used in scenario of TIE.
	public static final String NO_WINNER = "No Winner";
	
	//This will be reffered while showing the results.  
	public static final String DECEPTIONS ="Decepticons";
	public static final String AUTOBOTS ="Autobots";
	
	

}
