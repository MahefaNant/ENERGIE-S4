package database.sql;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;

import inserted.Inserted;
import reflect.Reflect;
import reflect.converter.ConverterObject;

/**
 * A class that manages the preapration of a sql query (select,update,insert)
 * used by the methods of Inserted (select,update,insert)
 */
public class QueryPreparator {
    /**
     * @param ref class that will manage all reflect needed
     */
    Reflect ref;

    /**
     * All declared fields of the Inserted
     */
    Field[] fields;

    String tableName;

    public QueryPreparator() {
    }

    public QueryPreparator(Inserted ins, String tableName) {
        this.ref = new Reflect(ins);
        this.fields = ref.getAllFields();
        this.tableName = tableName;
    }

    /**
     * get the update query from the attribute of the inserted
     * 
     * @param namePrimaryKey name of the PK
     * @return the update query
     */
    public String getUpdateQuery(String namePrimaryKey) {
        String sets = "";
        String condition = "";

        for (int i = 0; i < fields.length; i++) {
            String nameAttribute = fields[i].getName();
            String toSet = "%s %s=%s ";
            toSet = String.format(toSet, (sets == "") ? "" : ", ", nameAttribute, valueToQuery(fields[i]));
            if (nameAttribute.equals(namePrimaryKey)) {
                condition = toSet;
            }
            sets = sets + toSet;
        }
        sets = sets + " where " + condition;
        return "update " + tableName + " set " + sets;
    }

    private String fieldToAdd(String pk) {
        String tmp = "";
        for (Field field : fields) {
            String add = field.getName();
            if (add.equals(pk))
                continue;
            tmp += (tmp == "") ? add : "," + add;
        }
        return " (" + tmp + ") ";
    }

    /**
     * get the insertion query from the attibute of the inserted
     * 
     * @return the query of insertion
     */
    public String getInsertQuery(String pk) {
        String values = "";
        String fieldName = (pk == null) ? "" : fieldToAdd(pk);
        for (int i = 0; i < fields.length; i++) {
            String toAdd = "%s %s";
            if (fields[i].getName().equals(pk))
                continue;
            String tmp = (fields[i].getType() == String.class) ? "'" + valueToQuery(fields[i]) + "'"
                    : valueToQuery(fields[i]);
            toAdd = String.format(toAdd, (values == "") ? "" : ", ", tmp);
            values = values + toAdd;
        }
        values = " ( " + values + " ) ";
        return "insert into " + tableName + fieldName + " values " + values;
    }

    /**
     * use to get the query select with the condition gets from the attribute values
     * of the inserted
     * 
     * @return a String of the query
     */
    public String getSelectQuery() {
        String str = " where ";

        for (int i = 0; i < fields.length; i++) {
            String tmp = valueToQuery(fields[i]);

            if (tmp == "") {
                continue;
            }
            tmp = (fields[i].getType() != LocalDate.class) ? " like '%" + tmp + "%'" : "=" + tmp;
            String toAdd = "%s " + fields[i].getName() + "%s"; // the predicat
            toAdd = String.format(toAdd, (str.trim().equals("where")) ? "" : "and ", tmp); // add and to the predicat
            str = str + toAdd;
        }
        return "select * from " + tableName + ((str.trim().equals("where")) ? "" : str);
    }

    /**
     * Convert the string YYYY-MM-DD into a query that the database will understand
     * 
     * @param value The string get in format YYYY-MM-DD
     * @return TO_DATE('value','YYYY-MM-DD')
     */
    public String getQueryDate(String value) {
        String tmp = " TO_DATE('%s','%s') ";
        return String.format(tmp, value, "YYYY-MM-DD");
    }

    /**
     * Get the value of the this field and transform into a query that the database
     * will understand
     * 
     * @param field the field that we will get the value
     * @return a query that we can use in a selectQuery,insertQuery,updateQuery
     */
    private String valueToQuery(Field field) {
        String value = new ConverterObject(ref.getValueOf(field.getName())).toString();
        if (value == "") {
            return "";
        }
        if (value.contains("nextVal")) {
            return value;
        }
        if(field.getType() == LocalTime.class){
            return "'"+value+"'";
        }
        return (field.getType() == LocalDate.class) ? getQueryDate(value) : value;// soit double soit string na date:
    }

}
