package inserted;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import database.sql.QueryPreparator;
import reflect.Reflect;
import reflect.converter.ConverterString;

public class Inserted {

    protected String pk;
    protected String tableName;

    protected Inserted(String pk, String tableName) {
        this.pk = pk;
        this.tableName = tableName;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public static void main(String[] args) throws Exception {
 
    }

    /**
     * Use to update the table using
     * 
     * @param con            the instance connection
     * @param primaryKeyName name of the PK of the table
     */
    public void update(Connection con, Inserted newUpdate) throws Exception {
        Statement st = null;
        try {
            st = con.createStatement();
            String sql = new QueryPreparator(newUpdate, tableName).getUpdateQuery(pk);
            System.out.println(sql);
            st.execute(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if(st != null)
                st.close();
        }
    }

    /**
     * Use to insert a value in the table the value is getted from the attribute
     * value of the object-caller
     * 
     * @param con the instance connection
     */
    public void insert(Connection con) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement();
            String sql = new QueryPreparator(this, tableName).getInsertQuery(pk);
            System.out.println(sql);
            st.execute(sql);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(st != null)
                st.close();
        }
    }

    /**
     * use to get the result of a select query as an Inserted[] the caller can be
     * used as a predicat
     * 
     * @return An inserted array that represents the values of all rows from the
     *         query
     * @throws Exception
     * @throws ClassNotFoundException
     */
    public Inserted[] select(Connection con) throws Exception {
        Statement st = null;
        ResultSet rs = null;
        Inserted[] ins = null;
        try {
            st = con.createStatement();
            String sql = new QueryPreparator(this, tableName).getSelectQuery();
            rs = st.executeQuery(sql);
            ins = extractAll(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            if(st != null)
                st.close();
            if(rs != null)
                rs.close();
        }
        return ins;
    }

    /**
     * Use to extract the values of a row that rs point
     * 
     * @param rs  the pointer of the row
     * @param ins the inserted that we will set the attribute value from the value
     *            of the row that rs points
     * @throws Exception From transformInto() of the class ConverterStrings
     */
    private void extractIn(ResultSet rs, Inserted ins) throws Exception {
        Reflect ref = new Reflect(ins);
        Field[] attributes = ref.getAllFields();

        for (int i = 0; i < attributes.length; i++) {
            ref.setValueOf(attributes[i].getName(),
                    new ConverterString(rs.getString(i + 1)).transformInto(attributes[i].getType()));
        }
    }

    /**
     * Use to extract all values os the rows that the resultset will point
     * 
     * @param rs the resultSet that point to the row
     * @return an Inserted array that represents all the values of the row that rs
     *         will point
     * @throws Exception From the method createAnInstance of the class Reflect
     */
    private Inserted[] extractAll(ResultSet rs) throws Exception {
        Vector<Inserted> vectResult = new Vector<>(15, 15);
        Reflect ref = new Reflect(this);

        while (rs.next()) {
            Inserted tmp = (Inserted) ref.createAnInstance();
            extractIn(rs, tmp);
            vectResult.add(tmp);
        }
        return vectResult.toArray(new Inserted[vectResult.size()]);
    }
}
