package com.transfomation.main;

import com.transfomation.helper.TransformationConstant;
/**
 * This class contains the details of Transformer.Following details of transformer are captured 
 * 
 * a. Ranking of transformer : this is calculated by following formula, STRENGTH+SPEED+ENDURANCE+FIREPOWER[This is defined as per problem].
 * b. Matrix : This will contain rank of transformer under various parameters. 
 * c. Name : Name of the transformer. 
 * d. Team : Is it a Autobot or is it a Deception team member. 
 **************************************************** 
 *HOW DOES IT MANAGE ORDERING OF TRANSFORMER :
 ********************************************************************************************************************
 * This class implements Comparable interface to help data structure to store them in descending order.
 * The descending order is done to replication the requirement of transformer should be stored based on their ranking. 
 * *******************************************************************************************************************   
 * @author anandsoni
 *
 */


public class TransformerDetails implements Comparable<TransformerDetails> {
    private int ranking;
    private int matrix[] = new int[8];
    private String name;
    private String team;
	public TransformerDetails(int arr[],String name,String team) {
	  matrix=arr;
	  ranking = calculateRank(arr);
	  this.name=name;
	  this.team = team;
	}
	
	public int getRanking() {
		return ranking;
	}



	public void setRanking(int ranking) {
		this.ranking = ranking;
	}



	public int[] getMatrix() {
		return matrix;
	}



	public void setMatrix(int[] matrix) {
		this.matrix = matrix;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTeam() {
		return team;
	}



	public void setTeam(String team) {
		this.team = team;
	}



	/**
	 * This method calculates the ranking of the transformer my adding skills defined as per requirement.  
	 * This takes the single dimension array of skills as input and returns the ranking by 
	 * adding[Skills,Intelligence,Speed,Endurance,firepower]
	 * @param arr
	 * @return
	 */
	
	private int calculateRank(int[] arr) {
		//(Strength + Intelligence + Speed + Endurance + Firepower)
		return arr[TransformationConstant.STRENGTH]+ arr[TransformationConstant.INTELLIGENCE]+ arr[TransformationConstant.SPEED]+ arr[TransformationConstant.ENDURANCE]+ arr[TransformationConstant.FIREPOWER];
	}

	/**
	 *This class implements comparable method to make sure that it could arrange elements in descending order. 
	 */
	@Override
	public int compareTo(TransformerDetails o) {
		if(this.ranking >o.ranking){
			return -1;
		}else if(this.ranking < o.ranking){
			return 1;
		} 
		return 0;
	}
	
}
