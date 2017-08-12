package fr.gwenzy.discord.ariana.Methods;

import javax.xml.crypto.Data;
import java.sql.*;

public class PollsMethods {

    public static Long createPoll(String question, String authorID){
        try (Connection con = DatabaseMethods.connect();
             PreparedStatement state = con.prepareStatement("INSERT INTO polls (authorID, question, answers, votes, voted, active) VALUES (?, ?, '', '', '', 0)", Statement.RETURN_GENERATED_KEYS);
        ){
            state.setString(1, authorID);
            state.setString(2, question);

            state.executeUpdate();

            try(ResultSet result = state.getGeneratedKeys()){
                if(result.next()){
                    return result.getLong(1);
                }
                else{
                    return -1L;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static boolean addAnswer(int pollID, String answer){
        try (Connection con = DatabaseMethods.connect();
             PreparedStatement state = con.prepareStatement("SELECT * FROM polls WHERE id=?");
        ){
            state.setInt(1, pollID);


            String answers = "";
            try(ResultSet result = state.executeQuery()){
                if(result.next()){
                    answers+=result.getString("answers");

                }
                else{
                    return false;
                }

            }
            answers+=answer+";";

            try(PreparedStatement stateupdate = con.prepareStatement("UPDATE polls SET answers=? WHERE id=?")){
                stateupdate.setString(1, answers);
                stateupdate.setInt(2, pollID);
                stateupdate.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }


}