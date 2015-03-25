/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBaseTables;
import java.sql.*;





public class UserTable {
    
    int id;
    String login;
    
    
   static final String USER = "Batman";
   static final String PASS = "batman";
   static final String DB_URL = "jdbc:mysql://localhost/bomberman";
    
    public UserTable(){
    login = "gosc";
    }
    public void addUser (String login, String md5hash) throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        PreparedStatement stmt = null;
        
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String sql = "INSERT INTO users"
				+ "(login, password) VALUES"
				+ "(?,?)";
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.prepareStatement(sql);
    stmt.setString(1, login);
    stmt.setString(2, md5hash);

// execute insert SQL stetement
stmt.executeUpdate();
System.out.println("User added");
      

      stmt.close();
      conn.close();
}
    
    
    public void login(String login, String md5hash) throws ClassNotFoundException, SQLException{
    
    Class.forName("com.mysql.jdbc.Driver");
        Connection conn;
        PreparedStatement stmt;
        
        System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String sql = "SELECT id, login FROM users WHERE login = ? and password = ?";
				
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.prepareStatement(sql);
    stmt.setString(1, login);
    stmt.setString(2, md5hash);
        try (// execute insert SQL stetement
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                this.id=rs.getInt("id");
                this.login=rs.getString("login");
                System.out.println(rs.getInt("id"));
            }
        }
      stmt.close();
      conn.close();
    
    }
    
    public String getLogin(){
        return this.login;
    }
    
    
    public int getId(){
        return this.id;
    }
}