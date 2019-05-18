import java.awt.*;
import java.sql.*;
public class SQLtest{

    public static void main(String[] args) {
        //注册MySQL Connector/J/////////////////////////////////////////
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.printf("连接器注册成功");
        } catch (Exception ex) {
            System.out.printf("连接器注册失败");
        }

        //获取Connection实例DriverManager///////////////////////////////
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT%2B8" ,
                            "root","Userpassword");
            System.out.printf("数据库连接成功");

        } catch (SQLException ex) {
            // 处理错误
            System.out.printf("数据库连接失败，错误信息：\n");
            System.out.println("SQLEsxception: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        //执行查询语句/////////////////////////////////////////////
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            stmt.execute("SELECT administrator_account FROM administrator");
            System.out.printf("查询成功，查询结果为");
            rs=stmt.getResultSet();
            while(rs.next()){
                String str=rs.getString(1);
                System.out.println(str);
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }
}
