import java.util.Scanner;
import java.sql.*;
public class college {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/school";

   static final String USER = "root";
   static final String PASS = "********";
   public static void main(String[] args)throws SQLException{
   Scanner scanner;
   Connection con=null;
   PreparedStatement stmt=null;
   Statement nstmt=null;

   try{
    scanner =new Scanner(System.in);
    con=DriverManager.getConnection(DB_URL,USER,PASS);
    String createTable="CREATE TABLE IF NOT EXISTS College (BranchCode varchar(10) NOT NULL ,branch varchar(20) NOT NULL, HOD varchar(30))";
    nstmt=con.createStatement();
    int flag=nstmt.executeUpdate(createTable);
    if(flag == 0)System.out.println("Successfully Created table ");
    else System.out.println("not created table ");
    String data1="insert into college values(?,?,?)";
    stmt=con.prepareStatement(data1);
    con.setAutoCommit(false);
    while(true){
        System.out.println("enter the branchCode ");
        String branchCode=scanner.nextLine();
        System.out.println("enter the branch Name ");
        String branchName=scanner.nextLine();
        System.out.println("enter the hod Name");
        String hodName=scanner.nextLine();
        stmt.setString(1,branchCode);
        stmt.setString(2,branchName);
        stmt.setString(3,hodName);
        stmt.addBatch();
        System.out.println("exit y/n");
        char ch=scanner.next().charAt(0);
        if(ch == 'y' || ch == 'Y')break;
        scanner.nextLine();
    }
      stmt.executeBatch();
      con.commit();
      nstmt.close();
      stmt.close();
      con.close();
   }
   catch(SQLException se){
         se.printStackTrace();
   }
   finally{
    if(nstmt != null)nstmt.close();
    if(stmt != null)stmt.close();
    if(con != null)con.close();
   }
   }
}
