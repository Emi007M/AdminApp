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

    private CurrentTournament() {
    }

    public static void setTournament(Tournament t) {
        tournament = t;
        currentCompetition = null;
    }

    public static Tournament getTournament() {
        return tournament;
    }

    public static String getTournamentTitle() {
        return tournament.getTitle();
    }

    public static ArrayList<Competition> getTournamentCompetitions(Integer boardID) {
        return tournament.getCompetitionsForBoard(boardID);
    }

    public static ArrayList<Competition> getTournamentCompetitions() {
        return tournament.getCompetitions();
    }

    public static Competition getCompetition(Integer id) {
        for (Competition c : tournament.getCompetitions()) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public static void setCurrentCompetition(Competition c) {
        //todo save previous progress

        //check whether it belongs to boardID, set boardID somewhere
        currentCompetition = c;
    }

    public static boolean isCurrentCompetitionSet() {
        return currentCompetition != null;
    }

    public static Competition getCurrentCompetition() {
        return currentCompetition;
    }

    public void setBoardID(Integer i) {
        boardID = i;
        currentCompetition = null;
    }

    public Integer getBoardID() {
        return boardID;
    }

    /**
     * method for Socket operations probably receives locked or locked and
     * finished competition from Client and not locked from Server
     *
     * @param ID
     * @param c
     */
    public static void updateCompetition(Competition c) {
        System.out.println("competition to be updated...");
        if (c == null) {
            return;
        }
        Integer ID = c.getID();
        Competition tmp = getCompetition(ID);

        if (tmp.getTitle().equals(c.getTitle())) {

            tournament.setCompetition(c);
            return;
        }
        System.out.println("competition couldn't be updated");
    }

}
