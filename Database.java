package base;

import java.sql.*;

public class Database {
    public Connection con;
    public ResultSet resultSet(String requete) {
        try
		{
			String forName = "",portDB="",user="",mdp="";
            forName = "oracle.jdbc.driver.OracleDriver";
            portDB = "jdbc:oracle:thin:@localhost:1521:orcl";
            user="energie";
            mdp = "mahefa";
			Class.forName(forName);
			this.con = DriverManager.getConnection(portDB,user,mdp);
			Statement stmt = con.createStatement();
			ResultSet  res = stmt.executeQuery(requete);
            return res;
		}
		catch(Exception e){ 
			System.out.println("erreur de connection");
            return null;
		}
    }

    public String[] select(String column,String table,String condition) {
        String req = "";
        String[] result = null;
        if(condition==null) condition="";
        String sql = "select "+ column + " from " + table  + " " + condition;
        //System.out.println(sql);
        ResultSet res = this.resultSet(sql);
        try {
            while(res.next()) {
                req+= res.getString(1)+",";
            }
            result= req.split(",");
            con.close();
        } catch (Exception e) {
            System.out.println("erreur de requete");
        }
       return result;
    }

    public void insert(String table,String[] values) throws SQLException{
        String sql = "insert into " + table + " values (";
        for(int i=0;i<values.length;i++) {
            if(i!=values.length-1) sql+= values[i] + ",";
            else sql+=values[i];
        }
        sql+=")";
        //System.out.println(sql);
        this.resultSet(sql);
        con.close(); 
    }

    public void update(String table,String set,String cond) throws SQLException {
        if(cond!=null || cond !="") cond = " where " + cond;
        String sql= "update "+ table+ " set " + set + cond;
        System.out.println(sql);
        this.resultSet(sql);
        con.close();
    }

}
