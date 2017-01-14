/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.model;

import serializable.model.Tournament;
import serializable.model.Competition;
import java.util.ArrayList;

/**
 *
 * @author Emilia
 */
public final class CurrentTournament {
    
    private static Tournament tournament = null;
    private static Integer boardID;
    private static Competition currentCompetition;
    
    private CurrentTournament(){}
    
    public static void setTournament(Tournament t){
        tournament = t;
        currentCompetition = null;
    }
    
    
    public static Tournament getTournament(){
        return tournament;
    }
    public static String getTournamentTitle(){
        return tournament.getTitle();
    }
    public static ArrayList<Competition> getTournamentCompetitions(Integer boardID){
        return tournament.getCompetitionsForBoard(boardID);
    }
    public static ArrayList<Competition> getTournamentCompetitions(){
        return tournament.getCompetitions();
    }
    public static Competition getCompetition(Integer id){
        for(Competition c : tournament.getCompetitions())
            if(c.getID().equals(id))return c;
        return null;
    }
    
    
    public static void setCurrentCompetition(Competition c){
        //todo save previous progress
        
        //check whether it belongs to boardID, set boardID somewhere
        
        currentCompetition = c;
    }
    
    public static boolean isCurrentCompetitionSet(){
        return currentCompetition!=null;
    }
    
    public static Competition getCurrentCompetition(){
        return currentCompetition;
    }
    
    
    public void setBoardID(Integer i){
        boardID = i;
        currentCompetition = null;
    }
    
    public Integer getBoardID(){
        return boardID;
    }


    
    
    
}
