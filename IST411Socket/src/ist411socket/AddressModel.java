/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author ajl5735
 */
public class AddressModel {

    String name;
    String street;
    int zip;
    String state;

    AddressModel(String name, String street, int zip, String state) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.state = state;
    }

    AddressModel() {
        this.name = "";
        this.street = "";
        this.zip = 0;
        this.state = "";
    }

    public boolean isValidate() {
        boolean valididty = false;

        if (!this.name.equalsIgnoreCase("")) {
            if (!this.street.equalsIgnoreCase("")) {

                if (!this.state.equalsIgnoreCase("")) {

                    if (this.zip != 0) {

                        if (this.zip > 0 && this.zip < 99999) {
                            valididty = true;
                        }

                    }
                }

            }

        }

        return valididty;
    }

    public void printInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Street: " + this.street);
        System.out.println("Zip: " + this.zip);
        System.out.println("State: " + this.state);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String serializeToString() {
        String a = this.getName().replaceAll("\\ ", "_") +","+ this.street.replaceAll("\\ ", "_")+"," + this.state+"," + this.zip;
        return a;
    }

    static AddressModel deSerializeFromString(String s) {
        AddressModel a = new AddressModel();
       String[] tempSplit;
        
        tempSplit = s.split(",");
        
        a.setName(tempSplit[0].replaceAll("_", "\\ "));
        a.setStreet(tempSplit[1].replaceAll("_","\\ "));
        a.setState(tempSplit[2]);
        a.setZip(Integer.parseInt(tempSplit[3]));
        return a;
    }

}
