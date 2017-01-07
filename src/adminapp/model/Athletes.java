/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.model;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Emilia
 */
public class Athletes {
    
    private ArrayList<Person> competitors;
    boolean twoThirdPlaces;
    
    public Athletes(){
        //get lists from somewhere TODO
        
        competitors = new ArrayList<Person>();
        
        competitors.add(new Person("Adam","Kowalski",'m',2000,7,"KS"));
        competitors.add(new Person("Edward","Rogal",'m',2001,6, "ŁKKT"));
        competitors.add(new Person("Marian","Nowak",'m',2000,7,"KK"));
        competitors.add(new Person("Marek","Koper",'m',2002,7,"KS"));
        competitors.add(new Person("Wojciech","Maj",'m',2000,6,"AKK"));
        competitors.add(new Person("Adam","Wodny",'m',2001,6,"AKK"));
        competitors.add(new Person("Tomasz","Kowalski",'m',2001,7,"Kumade"));
        competitors.add(new Person("Tomasz","Wilk",'m',2000,7,"Kumade"));
        
        competitors.add(new Person("Marian","Rokita",'m',2002,7,"Kumade"));
        competitors.add(new Person("Łukasz","Buc",'m',2002,6,"KK44"));
        competitors.add(new Person("Tomasz","Kowalski",'m',2001,7,"Kumade"));
        competitors.add(new Person("Tomasz","Wilk",'m',2000,7,"Kumade"));
        competitors.add(new Person("Marek","Koper",'m',2002,7,"KS"));
        competitors.add(new Person("Wojciech","Maj",'m',2000,6,"AKK"));
        
        twoThirdPlaces = true;
    }
    
    public Athletes(ArrayList<Person> athletes, boolean is){
        competitors = athletes;
        setTwoThirdPlaces(is);
    }
    
    public ArrayList<Person> getCompetitors(){
        return competitors;
    }
    
    public boolean isTwoThirdPlaces(){
        return twoThirdPlaces;
    }
    
    public void setTwoThirdPlaces(boolean is){
        twoThirdPlaces = is;
    }
    
}
