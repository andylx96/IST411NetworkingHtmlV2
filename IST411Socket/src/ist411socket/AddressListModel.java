/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.util.ArrayList;

/**
 *
 * @author ajl5735
 */
public class AddressListModel {
//    AddressModel addresses = new AddressModel();
    ArrayList<AddressModel> addressArrayList; 
    
    AddressListModel(){
     addressArrayList = new ArrayList();
    
    }

    public ArrayList<AddressModel> getAddressArrayList() {
        return addressArrayList;
    }

    public void setAddressArrayList(ArrayList<AddressModel> addressArrayList) {
        this.addressArrayList = addressArrayList;
    }

    
}
