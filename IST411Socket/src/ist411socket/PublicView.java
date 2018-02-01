/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author ajl5735
 */
public class PublicView {

    private StringBuilder html = new StringBuilder();

    public PublicView() {

//        html.append("<b>Hello World!</b><BR>");
//        html.append("</html>");
    }

    public String getHtml(String filePath) {
//        html.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\n" +
//"   \"http://www.w3.org/TR/html4/strict.dtd\">");
        html.append("<html><body>");

        html.append("<img src=\"" + "fw.jpg" + "\" alt=\"Flowers in Chania\">");
        System.out.println("FilePath from View test: <img src=\"" + "fw.jpg" + "\" alt=\"Flowers in Chania\">");

        html.append("</body></html>");
        return html.toString();
    }
}
