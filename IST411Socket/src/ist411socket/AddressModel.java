/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.util.StringTokenizer;

/**
 *
 * @author ajl5735
 */
public class AddressModel {

    String name;
    String streetName;
    int streetNumber;
    int zip;
    String state;

    AddressModel(String name, int streetNumber, String streetName, int zip, String state) {
        this.name = name;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.zip = zip;
        this.state = state;
    }
    
    
     AddressModel() {
        this.name = "";
        this.streetNumber = 0;
        this.streetName = "";
        this.zip = 0;
        this.state = "";
    }

    public boolean validate() {
        boolean valid = false;
        if(this.zip >0 && this.zip< 99999){
            
            if(this.streetNumber > 0){
            if (this.streetName.length() > 0){
            valid = true;}
            }
        }
        
        return valid;
    }
    
    public void printInfo(){
        System.out.println("Name: " + this.name);
        System.out.println("Street: " + this.streetNumber +" " + this.streetName);
        System.out.println("Zip: "+ this.zip);
        System.out.println("State: "+ this.state);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
