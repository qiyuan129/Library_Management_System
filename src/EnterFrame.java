import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EnterFrame extends JFrame implements ActionListener{
	Icon TeacherIcon;
	Icon StudentIcon;
	Font StartFont;
	Font TextFont;
	Color FontColor;
	JButton Student ;
	JButton Manager;
	JTextField TextField;
	JPanel StartPanel;
	public EnterFrame() {
		super("图书馆信息管理系统");
		

		TeacherIcon = new ImageIcon(getClass().getResource("teacher.png"));
		StudentIcon = new ImageIcon(getClass().getResource("student.png"));
		StartFont = new Font("宋体", 2, 95);// 宋体，加粗，15号
		TextFont = new Font("宋体", 1, 25);// 宋体，加粗，15号
		FontColor = new Color(143, 134, 203);
		Student = new JButton("读者登陆");
		Manager = new JButton("管理员登陆");
		TextField = new JTextField("图书馆信息管理系统登陆");
		StartPanel = new JPanel();
		TextField.setEditable(false);
		TextField.setBackground(FontColor);
		TextField.setFont(StartFont);
		TextField.setHorizontalAlignment(JTextField.CENTER);
		setSize(1600, 1600);
		setVisible(true);
		setLayout(new GridLayout(2,1));
		add(TextField);
		StartPanel.setLayout(new FlowLayout());
		add(StartPanel);
		StartPanel.add(Student);	
		Student.setIcon(StudentIcon);//设置按钮图片
		Manager.setIcon(TeacherIcon);
		StartPanel.add(Manager);
		Manager.addActionListener(this);
		Student.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
        //////////////////////5.13到这里，接下来做账号密码输入框  
		dispose();
		if(e.getSource() == Manager)
			Transmit.isManager = true;
		if(e.getSource()== Student)
			Transmit.isManager = false;
		PassWordFrame Enter= new PassWordFrame();	
		
		
	}
	
	
	
	void hh(){
		System.out.println("cjnsj");
	}
	
}



















