/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ajl5735
 */
public class AddressListModel {
//    AddressModel addresses = new AddressModel();

    ArrayList<AddressModel> addressArrayList;

    AddressListModel() {
        addressArrayList = new ArrayList();

    }

    public ArrayList<AddressModel> getAddressArrayList() {
        return addressArrayList;
    }

    public void setAddressArrayList(ArrayList<AddressModel> addressArrayList) {
        this.addressArrayList = addressArrayList;
    }

    public void saveToFile(String fileName) {
        System.out.println(this.addressArrayList.size());

        FileWriter fout;
        try {
            fout = new FileWriter("fileName.csv", false);

            for (int i = 0; i < this.addressArrayList.size(); i++) {

                fout.write(this.addressArrayList.get(i).serializeToString() + "\n");
//                fout.write(this.addressArrayList.get(i).getName() + "," + this.addressArrayList.get(i).getStreet() + "," + this.addressArrayList.get(i).getState() + "," + this.addressArrayList.get(i).getZip() + "\n");

                System.out.println("save to file : " + this.addressArrayList.get(i).getName() + "," + this.addressArrayList.get(i).getStreet() + "," + this.addressArrayList.get(i).getState() + "," + this.addressArrayList.get(i).getZip() + "\n");
            }
            fout.close();
        } catch (IOException ex) {
            System.out.println("Unable To Create File");
        }
    }

    static public AddressListModel makeAddressListFromFile(String fileName) {

        File tmpDir = new File("fileName.csv");
        boolean exists = tmpDir.exists();
        if (exists == true) {

            AddressListModel alm = new AddressListModel();
            String tempLine;
            try {
                FileReader fin;
                fin = new FileReader("fileName.csv");
                Scanner scan = new Scanner(fin);
                while (scan.hasNext()) {

                    tempLine = scan.next();

                    alm.getAddressArrayList().add(AddressModel.deSerializeFromString(tempLine));

                }
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found for DB");
            }

            System.out.println("alm size is: " + alm.getAddressArrayList().size());
            return alm;

        } else {
            System.out.println("No File Found");
            return new AddressListModel();
        }

    }
}
