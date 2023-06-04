package inserted;

import java.util.Arrays;
import java.util.Vector;

import reflect.Reflect;
import reflect.converter.ConverterObject;


public class Table {

    String[] headerName;
    Inserted[] ins;
    String html;
    String width = "150";

    public Table(String[] headerName, Inserted[] ins) {
        this.headerName = headerName;
        this.ins = ins;
    }

    public Table(String[] headerName, Vector vec ){
        this.headerName = headerName;
        setIns(vec);
    }

    public void setIns (Vector vec){
        Object[] obj = vec.toArray();
        ins = Arrays.copyOf(obj,obj.length,Inserted[].class);
    }

    protected String header() {
        String head = "<tr> \n";
        for (String tmp : headerName) {
            String toAdd = "\t<th width='%s'>%s</th>\n";
            toAdd = String.format(toAdd, width, tmp);
            head += toAdd;
        }
        head += "</tr>\n";
        return head;
    }

    protected String body() {
        String body = "";
        for (Inserted tmp : ins) {
            Reflect ref = new Reflect(tmp);
            body += "<tr>\n";
            for (int i = 0; i < headerName.length; i++) {
                String str = new ConverterObject(ref.getValueOf(headerName[i])).toString();
                body += "\t<td>" + str + "</td>\n";
            }
            body += "</tr>\n";
        }
        return body;
    }

    protected void prepareHtml() {
        html = "<table>\n" + header() + body() + "</table>";
    }

    public String getHtml() {
        prepareHtml();
        return html;
    }
}

