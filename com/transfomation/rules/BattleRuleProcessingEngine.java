package com.transfomation.rules;

import com.transfomation.helper.TransformationConstant;
import com.transfomation.helper.WinnerDetails;
import com.transfomation.main.TransformerDetails;
import com.transformation.exception.AllCompetitorsDestroyedException;
import com.transformation.exception.SameRankingTransFormerEnounteredException;

/**
 * This class contains all the rules for identifying winner or looser among Autobots or Deceptions.
 * This class is designed to be single Instance as this is not managing internal state of the application. 
 * Following is the flow Validation steps in this class : 
 * a. Try to apply rule for special events which are defined in method<checkForSpecialExceptionScenario>. 
 * b. If Step a fails then it will check for checkCourageAndStrengthRule.
 * c. If step b fails it will then check for skill rule.
 * d. If step c fails it will check for Rank of the transformers to identify the winner. 
 * 
 *  ==> At any given step if a winner is identified the rule engine stops further processing and return back.  
 * 
 * @author anandsoni
 *
 */


public class BattleRuleProcessingEngine {
	
	private BattleRuleProcessingEngine(){
		
	}

    private static BattleRuleProcessingEngine battleRuleEngine;

    /**
     * This is a static method created to get the handle of the class object. 
     * This method ensures that only one instance is created for this class,as this is an state less and does not carry states across multiple method calls.  
     * @return
     */
public static BattleRuleProcessingEngine getBattleRuleEngineInstance() {
        if (battleRuleEngine == null)
            synchronized(BattleRuleProcessingEngine.class) {
                if (battleRuleEngine == null) {
                    battleRuleEngine = new BattleRuleProcessingEngine();
                }

            }
        return battleRuleEngine;
    }

/**
 * Once handle of this engine is obtained then below method is called to start validating the rules.
 * This method instantiates WinnerDetails and is passed across the method calls to make sure all methods updates
 * same data structure to effectively manage flow.  
 * @param autoBot
 * @param deception
 * @return
 */
 public String FindWinner(TransformerDetails autoBot, TransformerDetails deception) {
        String winner = null;
        WinnerDetails winnerDetails = new WinnerDetails();
        
            checkForSpecialExceptionScenario(autoBot, deception, winnerDetails);
            if (!winnerDetails.isFoundWinner()) {
                checkForbasicRules(autoBot, deception, winnerDetails);
            }
       
        return winner=winnerDetails.getWinner();
    }

 /**
  * This method is start of the basic rules to validated in deciding the winner of a battle.
  * @param autoBot
  * @param deception
  * @param winnerDetails
  */
private void checkForbasicRules(TransformerDetails autoBot, TransformerDetails deception,
        WinnerDetails winnerDetails) {
        checkCourageAndStrengthRule(autoBot, deception, winnerDetails);
        if (!(winnerDetails.isFoundWinner())) {
            checkForSkillStrength(autoBot, deception, winnerDetails);
        }
        if (!(winnerDetails.isFoundWinner())) {
            checkWinnerByRanking(autoBot, deception, winnerDetails);
        }

    }


    /**
     * 
     * @param autoBot
     * @param deception
     * @param winnerDetails
     * This method simple checks the Ranking of the transformer if difference in Ranking is found then Transformer with higher rank is
     * declared as winner. 
     * If Ranking of both Transformer is same then SameRankingTransFormerEnounteredException is thrown and No Winner are setup. 
     */


    private void checkWinnerByRanking(TransformerDetails autoBot, TransformerDetails deception,
        WinnerDetails winnerDetails) {

        if (autoBot.getRanking() > deception.getRanking()) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.AUTOBOT);
        } else if (deception.getRanking() > autoBot.getRanking()) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.DECEPTION);
        } else if (deception.getRanking() == autoBot.getRanking()) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.NO_WINNER);
            throw new SameRankingTransFormerEnounteredException(TransformationConstant.SAME_RANKING_EXCEPTION_MESSAGE);
        }
    }
    /**
     * 
     * @param autoBot
     * @param deception
     * @param winnerDetails
     * Otherwise, if one of the fighters is 3 or more points of skill above their opponent, they win
    	the fight regardless of overall rating
     */


    private void checkForSkillStrength(TransformerDetails autoBot, TransformerDetails deception,
        WinnerDetails winnerDetails) {

        if ((autoBot.getMatrix()[TransformationConstant.SKILL] - deception.getMatrix()[TransformationConstant.SKILL]) >= 3) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.AUTOBOT);
        } else if ((deception.getMatrix()[TransformationConstant.SKILL] - autoBot.getMatrix()[TransformationConstant.SKILL]) >= 3) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.DECEPTION);
        }
    }
 /**
     * 
     * @param autoBot
     * @param deception
     * @param winnerDetails
     * If any fighter is down 4 or more points of courage and 3 or more points of strength compared to their opponent, 
     * the opponent automatically wins the face-off regardless of
    	overall rating (opponent has ran away)
     */




    private void checkCourageAndStrengthRule(TransformerDetails autoBot, TransformerDetails deception,
        WinnerDetails winnerDetails) {
        if (((autoBot.getMatrix()[TransformationConstant.COURAGE] - deception.getMatrix()[TransformationConstant.COURAGE]) >= 4) &&
            ((autoBot.getMatrix()[TransformationConstant.STRENGTH] - deception.getMatrix()[TransformationConstant.STRENGTH]) >= 3)) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.AUTOBOT);
        } else if (((deception.getMatrix()[TransformationConstant.COURAGE] - autoBot.getMatrix()[TransformationConstant.COURAGE]) >= 4) &&
            ((deception.getMatrix()[TransformationConstant.STRENGTH] - autoBot.getMatrix()[TransformationConstant.STRENGTH]) >= 3)) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.DECEPTION);
        }

    }
    /**
     * ************************************************************************************||***********************************************
     * 									ASSUMPTION 	
     * *************************************************************************************||***********************************************
     * Here we are assuming that be it be it Autobot or Deception both of them can be OPTIMUS PRIME or PREDAKING as name of their transformer
     * ***************************************************************************************************************************************
     * In this method we have designed conditions considering above assumption in deciding the winner or all competitors destroyed condition. 
     * If OPTIMUS PRIME comes face off with PREDAKING, then code will throw AllCompetitorsDestroyedException to signal all competitors should be 
     * destroyed. 
     * 
    	 
    */


    private void checkForSpecialExceptionScenario(TransformerDetails autoBot, TransformerDetails deception, WinnerDetails winnerDetails) {
        /**
         * This is scenario when Autobot is Optimus Prime or PREDAKING and opponent is neither Optimus Prime nor Predaking.
         */
        if ((autoBot.getName().equals(TransformationConstant.OPTIMUS_PRIME) || (autoBot.getName().equals(TransformationConstant.PREDAKING))) &&
            (!(deception.getName().equals(TransformationConstant.OPTIMUS_PRIME)) && !(deception.getName().equals(TransformationConstant.PREDAKING)))) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.AUTOBOT);
        }

        /**
         * This is scenario when Deception is Optimus Prime or PREDAKING and AutoBot is neither Optimus Prime nor Predaking.
         */
        else if (deception.getName().equals(TransformationConstant.OPTIMUS_PRIME) &&
            (!(autoBot.getName().equals(TransformationConstant.OPTIMUS_PRIME)) && !(autoBot.getName().equals(TransformationConstant.PREDAKING)))) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.DECEPTION);
        }
        /**
         * This is scenario when Either of opponent is  OPTIMUS PRIME or PREDAKING.
         * In this scenario code will throw AllCompetiorsDestroyedException as no one will be competitors will be destroyed and 
         * there will be no winner in this scenario. 
         */
        else if ((autoBot.getName().equals(TransformationConstant.OPTIMUS_PRIME) || autoBot.getName().equals(TransformationConstant.PREDAKING)) &&
            ((deception.getName().equals(TransformationConstant.OPTIMUS_PRIME)) || (deception.getName().equals(TransformationConstant.PREDAKING)))) {
            winnerDetails.setFoundWinner(true);
            winnerDetails.setWinner(TransformationConstant.NO_WINNER);
            throw new AllCompetitorsDestroyedException(TransformationConstant.NO_WINNER_FOUND_MESSAGE);
        }

    }

}