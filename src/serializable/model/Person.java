/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializable.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Emilia
 */
public class Person implements Externalizable{
    
    private static final long serialVersionUID = -7625912550791862483L;
    
    private StringProperty name;
    private StringProperty surname;

    private IntegerProperty year;
    private StringProperty degree;
    private StringProperty club;
    
    private BooleanProperty preranked;
    
    /**
     * 
     * @param _name
     * @param _surname
     * @param _year
     * @param _degree eg 1 KYU
     * @param _club 
     */
    public Person(String _name, String _surname, Integer _year, String _degree, String _club){
        name    = new SimpleStringProperty(_name);
        surname = new SimpleStringProperty(_surname);
        year    = new SimpleIntegerProperty(_year);
        degree  = new SimpleStringProperty(_degree);
        club    = new SimpleStringProperty(_club);
        
        preranked = new SimpleBooleanProperty(false);
    }
      
    public Person(){
        name    = new SimpleStringProperty();
        surname = new SimpleStringProperty();
        year    = new SimpleIntegerProperty();
        degree  = new SimpleStringProperty();
        club    = new SimpleStringProperty();
        
        preranked = new SimpleBooleanProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty nameProperty(){
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }
    
    public StringProperty surnameProperty(){
        return surname;
    }

    public Integer getYear() {
        return year.get();
    }

    public void setYear(Integer year) {
        this.year.set(year);
    }
    
    public IntegerProperty yearProperty(){
        return year;
    }

    public String getDegree() {
        return degree.get();
    }

    public void setDegree(String degree) {
        this.degree.set(degree);
    }
    
    public StringProperty degreeProperty(){
        return degree;
    }
    
    public String getClub() {
        return club.get();
    }

    public void setClub(String club) {
        this.club.set(club);
    }
    
    public StringProperty clubProperty(){
        return club;
    }

    public Boolean getPreranked() {
        return preranked.get();
    }

    public void setPreranked(Boolean preranked) {
        this.preranked.set(preranked);
    }
    
    public BooleanProperty prerankedProperty(){
        return preranked;
    }
    
    public void setPreranked() {
        this.preranked.set(true);
    }
    
    
    @Override
    public String toString() {
        return getName()+" "+getSurname()+" ("+getClub()+")";
    }

    public String getFullName() {
        return getName()+" "+getSurname();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
//        private StringProperty name;
//    private StringProperty surname;
//
//    private IntegerProperty year;
//    private StringProperty degree;
//    private StringProperty club;
//    
//    private BooleanProperty preranked;
    
    out.writeObject(getName());
    out.writeObject(getSurname());
         out.writeInt(getYear());
    out.writeObject(getDegree());
    out.writeObject(getClub());
    out.writeBoolean(getPreranked());
        // write other properties...
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//       setId(in.readInt());
//        setFirstName((String)in.readObject());
        setName((String)in.readObject());
        setSurname((String)in.readObject());
        setYear(in.readInt());
        setDegree((String)in.readObject());
        setClub((String)in.readObject());
        setPreranked(in.readBoolean());
    }
    
    
    
    
    
}
