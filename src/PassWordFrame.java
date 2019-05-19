import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class PassWordFrame extends JFrame {
    JPanel Panel1;
    JPanel Panel2;
    JPanel Panel3;
    JLabel Label1;
    JLabel Label2;
    JTextField TextField;
    JPasswordField PasswordField;
    JButton Enter;
    JButton Exit;
    String UserNumber;
    String PassWord;
    Connection connector;

    public PassWordFrame() {
        UserNumber = null;
        PassWord = null;
        Panel1 = new JPanel();
        Panel2 = new JPanel();
        Panel3 = new JPanel();
        this.setLayout(new GridLayout(3, 1));
        //设置显示大概在屏幕中央
        this.setLocation(this.getToolkit().getScreenSize().width / 3, this.getToolkit().getScreenSize().height / 6);
        Label1 = new JLabel("账户");
        Label2 = new JLabel("密码");
        PasswordField = new JPasswordField("请输入密码", 20);
        TextField = new JTextField("请输入账户", 20);
        Enter = new JButton("确定");
        Exit = new JButton("退出");
        this.setSize(800, 800);
        this.setVisible(true);
        Label1.setSize(400, 50);
        Label2.setSize(400, 50);
        //Enter.setBounds(0,0,100,60);//x,y,w,h   设置按钮大小
        //Enter.setPreferredSize(new Dimension(100,60));
        Panel1.add(Label1);
        Panel1.add(TextField);
        Panel2.add(Label2);
        Panel2.add(PasswordField);
        Panel3.add(Enter);
        Panel3.add(Exit);
        this.add(Panel1);
        this.add(Panel2);
        this.add(Panel3);

        Exit.addActionListener(new ButtonListener());
        Enter.addActionListener(new ButtonListener());
        TextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transmit.UserNumber = TextField.getText();
            }
        });
        TextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Transmit.UserNumber = TextField.getText();
            }
        });
        PasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transmit.PassWord = PasswordField.getText();
            }
        });
        PasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Transmit.PassWord = PasswordField.getText();
            }
        });
    }


    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            if (e.getSource() == Exit) {
                dispose();//先关闭自己
                System.exit(0);
            } else if (e.getSource() == Enter) {

                //..........添加检查用户账户，密码的代码....................................//
                Statement sql = null;
                ResultSet result1;
                ResultSet result2;

                /** 根据全局变量判断登录的是学生还是管理员后，在相应的表中查询 */
                //是管理员
                if (Transmit.isManager == true) {
                    try {
                        String tem = "SELECT administrator_account FROM administrator " +
                                "WHERE administrator_account = '" + Transmit.UserNumber + "'";
                        sql = Transmit.databaseConnector.createStatement();
                        sql.execute("SELECT administrator_account FROM administrator " +
                                "WHERE administrator_account = '" + Transmit.UserNumber + "'");

                        //检查用户名
                        result1 = sql.getResultSet();
                        System.out.println("传入的用户名是" + Transmit.UserNumber);
                        System.out.println("传入的sql语句是" + tem);
                        if (result1.next() == false) {
                            throw new LoginException(0);
                        }

                        //比对库中密码与输入的密码
                        sql.execute("SELECT administrator_password FROM administrator " +
                                "WHERE administrator_account ='" + Transmit.UserNumber + "'");
                        result2 = sql.getResultSet();
                        result2.next();
                        if (Transmit.PassWord.equals(result2.getString("administrator_password")) == false) {
                            throw new LoginException(1);
                        }

                        //能执行到这里 说明账号密码验证成功 进入管理界面
                        dispose();
                        WorkFrame frame = new WorkFrame();

                    } catch (SQLException ex) {       //SQLException和自定义的两种错误的处理
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "执行SQL语句时出现错误！");
                        return;
                    } catch (LoginException ex) {
                        JOptionPane.showMessageDialog(null, ex.errorInformation + ",请尝试重新输入");
                        return;
                    }
                }
                //是学生
                else {
                    try {
                        sql = Transmit.databaseConnector.createStatement();
                        sql.execute("SELECT student_ID FROM student " +
                                "WHERE student_ID =" + Transmit.UserNumber);

                        result1 = sql.getResultSet();          //检查用户名
                        System.out.println("传入的学号是" + Transmit.UserNumber);
                        if (result1.next() == false) {
                            throw new LoginException(0);
                        }

                        //比对库中密码与输入的密码
                        sql.execute("SELECT student_password FROM student " +
                                "WHERE student_ID =" + Transmit.UserNumber);
                        result2 = sql.getResultSet();
                        result2.next();
                        if (Transmit.PassWord.equals(result2.getString("student_password")) == false) {
                            throw new LoginException(1);
                        }

                        //能执行到这里 说明账号密码验证成功 进入管理界面
                        dispose();
                        WorkFrame frame = new WorkFrame();


                    } catch (SQLException ex) {       //SQLException和自定义的两种错误的处理
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "执行SQL语句时出现错误！");
                        return;
                    } catch (LoginException ex) {
                        JOptionPane.showMessageDialog(null, ex.errorInformation + ",请尝试重新输入");
                        return;
                    }

                }

            }

        }
    }
}

/**
 * 自定义异常类，错误码errorcode为0是用户不存在错误，为1是用户与密码不匹配错误
 */
class LoginException extends Exception {
    int errorCode;
    String errorInformation;

    LoginException(int code) {
        errorCode = code;
        if (errorCode == 0) {
            errorInformation = "用户不存在！";
        }
        if (errorCode == 1) {
            errorInformation = "密码输入错误！";
        }
    }

    public void showInfo() {
        System.out.println("登录时出错，" + errorInformation);
    }
}
