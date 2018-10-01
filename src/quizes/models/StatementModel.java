/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizes.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author christopher.eckles
 */
public class StatementModel {
    String statement = "";
    Map<String, String> questionMap = new HashMap<>();

public StatementModel(String statement){
    this.statement = statement;
}    

    public String getStatement() {
        return statement;
    }

    public Map<String, String> getQuestionMap() {
        return questionMap;
    }
}
