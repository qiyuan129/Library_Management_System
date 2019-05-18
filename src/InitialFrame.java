import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitialFrame extends JFrame {
    JLabel []gifs=new JLabel[8];
    ImageIcon kunkun=new ImageIcon(this.getClass().getResource("sing_jump_basketball.gif"));
    JPanel note=new JPanel();
    JTextArea text1=new JTextArea(" \n\n欢迎使用图书馆管理系统");
    JTextField text2=new JTextField("         --powered by 陈启元，叶尤澎，叶博宁，岳逾先");
    JButton linkDatabase=new JButton("开始进行数据库连接");

    InitialFrame(){
        //Frame大小、布局设置
        setTitle("FZU图书馆管理系统--cxk独家赞助");
        setSize(1471,1518);
        GridLayout layout1=new GridLayout(3,3);
        setLayout(layout1);

        //gif图初始化
        for(int i=0;i<8;i++){
            gifs[i]=new JLabel(kunkun);
        }

        //添加组件
        add(gifs[0]);
        add(gifs[1]);
        add(gifs[2]);
        add(gifs[3]);
        add(note);
        add(gifs[4]);
        add(gifs[5]);
        add(gifs[6]);
        add(gifs[7]);

        /** 对显示主要信息的中间Panel组件进行设置  */
        //panel布局
        BorderLayout layout = new BorderLayout();
        note.setLayout(layout);
        note.setBackground(new Color(168, 197, 215));

        //textfield设置
        text1.setFont(new Font("黑体",Font.BOLD,42));
        text1.setBorder(null);
        text1.setBackground(new Color(168, 197, 215));
        text1.setLocation(30,200);
        text1.setSize(350,140);
        note.add(text1,BorderLayout.NORTH);

        text2.setFont(new Font("黑体",Font.PLAIN,18));
        text2.setBorder(null);
        text2.setBackground(new Color(168, 197, 215));
        note.add(text2,BorderLayout.CENTER);

        linkDatabase.setLocation(0,300);
        linkDatabase.setSize(150,70);
        linkDatabase.setFont(new Font("宋体",Font.PLAIN,22));
        linkDatabase.setBorderPainted(false);
        note.add(linkDatabase,BorderLayout.SOUTH);
        linkDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");   //根据java文档：对forName（“X”）的调用会导致名为X的类被初始化。
                    System.out.printf("连接器注册成功");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"数据库连接器注册失败！");
                    System.out.printf("连接器注册失败");
                    return;                            //注册都失败了就不用执行后续的内容了
                }

                //获取Connection实例DriverManager///////////////////////////////
                Connection conn = null;
                try {
                    conn =
                            DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT%2B8" ,
                                    "root","Userpassword");
                    System.out.printf("数据库连接成功");
                    Transmit.databaseConnector=conn;

                } catch (SQLException ex) {
                    // 处理错误
                    JOptionPane.showMessageDialog(null,"数据库连接失败！\n错误信息：\nSQLException:");
                    System.out.printf("数据库连接失败，错误信息：\n");
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());

                    return;
                }
                JOptionPane.showMessageDialog(null,"连接数据库成功,现在进入管理系统");
                dispose();
                EnterFrame e1=new EnterFrame();
            }
        });


        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


}



