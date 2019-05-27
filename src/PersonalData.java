import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.*;


class  ManagePerson extends JTabbedPane{//人员信息管理类   Manager
	/*
	 * 这个页面实现管理员  查询读者信息（包括管理员自己的信息也要罗列出来）
	 * 增加读者，删除读者 ，修改读者信息 等功能的实现
	 */
	
	JPanel Search;
	JPanel Insert;
	JPanel Delete;
	JButton JBSearch;
	
	public ManagePerson(String name) {
		super(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setName(name);
		Search = new JPanel();
		Insert = new JPanel();
		Delete = new JPanel();
		JBSearch = new JButton("查询");
		Search.setName("查询");
		Insert.setName("增加读者");
		Delete.setName("删除读者");
		add(Search);
		add(Insert);
		add(Delete);
		Search.add(JBSearch);
		
		
		
		JBSearch.addActionListener(new SearchJBListener());
		
		
	}
 
	
	
	class SearchJBListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			System.out.println("this is Manager/SearchPanel");
		}
		
	}
	
}






















class Books extends JTabbedPane implements ActionListener, ItemListener {//藏书类   Manager
	/*
	 * 这个页面实现图书馆馆藏书目的管理，包括   图书入库，图书注销
	 * 5.18实现了基本布局，还差delete页面的查询打印功能，这个功能在
	 * 实现，
	 * 还差insert 页面的录入数据库功能 这个功能在actionPerformed 213 行
	 */


	JPanel insert;
	JPanel delete;

	//insertPanel:
	Vector<JLabel> labels;//标签组
	Vector<JTextField> textFields;//输入框组
	JButton enterButton;
	JButton cancelButton;
	JPanel tempPanel;

	//deletePanel:
	JPanel inputPanel;//条件输入区
	JPanel tablePanel;//数据表打印区
	JPanel checkPanel;//复选框区
	Vector<JCheckBox> checkBox;
	Vector<JPanel> checkInput;
	JButton deleteButton ;
	JButton searchButton;



	public Books(String name) {
		super(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setName(name);
		insert = new JPanel();
		delete = new JPanel();

		//.....................insertPanel...................//
		//labels管理数组
		labels = new Vector<JLabel>();
		labels.add(new JLabel("书名"));
		labels.add(new JLabel("作者"));
		labels.add(new JLabel("ISBN"));
		labels.add(new JLabel("出版社"));
		labels.add(new JLabel("类别"));

		//文本域管理数组
		textFields = new Vector<JTextField>();
		textFields.add(new JTextField("请输入书名"));//
		textFields.add(new JTextField("请输入作者"));
		textFields.add(new JTextField("请输入ISBN码"));
		textFields.add(new JTextField("请输入出版社"));
		textFields.add(new JTextField("请输入类别（可空）"));


		insert.setName("图书入库");
		delete.setName("图书注销");
		this.add(insert);
		this.add(delete);

		insert.setLayout(new GridLayout(6, 1));//设置6行一列网格布局


		for (int i = 0; i < labels.size(); i++) {
			tempPanel = new JPanel();
			labels.get(i).setFont(Transmit.font);
			textFields.get(i).setFont(Transmit.font);
			tempPanel.add(labels.get(i));
			tempPanel.add(textFields.get(i));
			insert.add(tempPanel);
		}

		tempPanel = new JPanel();
		insert.add(tempPanel);
		enterButton = new JButton("确定");
		cancelButton = new JButton("取消");
		enterButton.addActionListener(this);
		cancelButton.addActionListener(this);
		tempPanel.add(enterButton);
		tempPanel.add(cancelButton);



		//.....................deletePanel...................//
		delete.setLayout(new BorderLayout(10,10));

		// 创建复选框
		checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(7,1));
		checkBox = new Vector<JCheckBox>();//管理复选框的数组
		JPanel tmpPanel;

		checkBox.add(new JCheckBox("书名"));
		checkBox.add(new JCheckBox("作者"));
		checkBox.add(new JCheckBox("ISBN"));
		checkBox.add(new JCheckBox("出版社"));
		checkBox.add(new JCheckBox("类别"));
		for(JCheckBox check:checkBox){
			check.addItemListener(this);
			checkPanel.add(check);
		}








		//条件输入区
		inputPanel = new JPanel();
		checkInput = new Vector<JPanel>();
		inputPanel.setLayout(new GridLayout(3, 2));
		deleteButton = new JButton("删除");
		searchButton = new JButton("查询");
		deleteButton.addActionListener(this);
		searchButton.addActionListener(this);
		for(int i=0;i<checkBox.size();i++){//复选框有几个，这边的输入框就有几个，当复选框被选中，就将这个输入框加进去
			tmpPanel = new JPanel();
			tmpPanel.add(new JLabel(checkBox.get(i).getText()));
			tmpPanel.add(new JTextField("输入" + checkBox.get(i).getText()));
			checkInput.add(tmpPanel);

		}




		//打印查询结果
		tablePanel = new JPanel();
		JLabel tmpLabel = new JLabel("这里只数据表");
		tablePanel.add(tmpLabel);


		delete.add(inputPanel, BorderLayout.NORTH);
		delete.add(tablePanel, BorderLayout.SOUTH);
		delete.add(checkPanel, BorderLayout.WEST);



	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton) {
			//确定 链接数据库处理,，从checkBox中查看哪些被选中，对应到checkInput中寻找相关的文本域获取文本内容作为查询的条件




		} else if(e.getSource() == cancelButton) {//取消
			for (JTextField jtf : textFields) {
				jtf.setText("请输入");
			}
		}

		else if(e.getSource() == deleteButton){//连接数据库删除查询出来的结果

		}

		else if(e.getSource() == searchButton){//连接数据库打印查询结果

		}
	}






	public void itemStateChanged(ItemEvent e){
		// 获取事件源（即复选框本身）

		//System.out.println(checkBox.getText() + " 是否选中: " + checkBox.isSelected());

		int index =0 ;
		JCheckBox box = (JCheckBox) e.getSource();
		for(index = 0;index<checkBox.size();index++){
			if(checkBox.get(index) == box)
				break;
		}
		if(box.isSelected()){
			inputPanel.add(checkInput.get(index));//勾选，则加上
		}
		else if (!box.isSelected()){
			inputPanel.remove(checkInput.get(index));//去选，则除去
		}
		repaint();

	}


}












public class PersonalData extends JTabbedPane{//个人信息类：显示个人信息 Manager & Students
	/*
	 * 这个页面显示管理员/学生自己的个人信息并实现相关的  修改功能
	 */
	//view
	JPanel view;
	JPanel update;
	JPanel namePanel;
	JPanel SNumberPanel;//xuehao
	JPanel sexPanel;
	JTextField nameField;
	JTextField SNumberField;
	JTextField sexField;

	//update
	JPanel upNamePanel;
	JPanel upSNumberPanel;//xuehao
	JPanel upSexPanel;
	JTextField upNameField;
	JTextField upSNumberField;
	JTextField upSexField;


	//数据库方面
	Connection connector;
	ResultSet result;
	Statement sqlStament;
	public PersonalData(String theName) {
		super(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setName(theName);
		view = new JPanel();
		update = new JPanel();
		view.setName("个人信息");
		update.setName("更改");
		this.add(view);
		this.add(update);

		//数据库
		//
		// 方面
		connector = Transmit.databaseConnector;

		String sql = "SELECT* FROME administrator "+"WHERE administrator_account = '" + Transmit.UserNumber + "'";
		try {
			sqlStament = connector.createStatement();
			sqlStament.execute(sql);
			result = sqlStament.getResultSet();
///...........................  5.27  获取结果集，准备将结果的数据赋值给文本框..............
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"出错了","SQL语句执行出错! ",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}






		//.....................View.........
		//name
		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		nameField = new JTextField();
		namePanel.add(new JLabel("姓名"));
		namePanel.add(nameField);


		// SNumber
		SNumberPanel = new JPanel();
		SNumberPanel.setLayout(new FlowLayout());
		SNumberField = new JTextField();
		SNumberPanel.add(new JLabel("学号"));
		SNumberPanel.add(SNumberField);



		//sex
		sexPanel = new JPanel();
		sexPanel.setLayout(new FlowLayout());
		sexField = new JTextField();
		sexPanel.add(new JLabel("性别"));
		sexPanel.add(sexField);



		view.setLayout(new GridLayout(3, 1));
		view.add(namePanel);
		view.add(SNumberPanel);
		view.add(sexPanel);





		//...................updatePanel
		//upName
		upNamePanel = new JPanel();
		upNameField = new JTextField("请填入姓名");
		upNamePanel.setLayout(new FlowLayout());
		upNamePanel.add(new JLabel("姓名"));
		upNamePanel.add(upNameField);

		//upSNumber
		upSNumberPanel = new JPanel();//
		upSNumberPanel.setLayout(new FlowLayout());
		upSNumberPanel.add(new JLabel("学号"));
		upSNumberField = new JTextField("请填入学号");
		upSNumberPanel.add(upSNumberField);

		//upsex
		upSexPanel = new JPanel();
		upSexPanel.setLayout(new FlowLayout());
		upSexField = new JTextField("请填入性别");
		upSexPanel.add(new JLabel("性别"));
		upSexPanel.add(upSexField);


		update.setLayout(new GridLayout(3,1));
		update.add(upNamePanel);
		update.add(upSNumberPanel);
		update.add(upSexPanel);


	}
	
	
	
}





class Borrow extends JTabbedPane{// 借阅登记  Manager
	/*
	 * 这个页面实现图书借   阅登记功能   ,查询学生的借阅信息
	 */
	
	JPanel Borrow;
	JPanel Returning;
	JPanel Search;
	public Borrow(String name) {
		super(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setName(name);
		Borrow = new  JPanel();
		Returning = new JPanel();
		Search = new JPanel();
		Borrow.setName("借阅");
		Returning.setName("还书");
		Search.setName("借阅查询");
		this.add(Borrow);
		this.add(Returning);
		this.add(Search);
		
	}
	
	
}








class BookBorrow extends JTabbedPane{//借书历史   Student & Manager
	/*
	 * 这个页面实现学生图书借阅信息的显示
	 */
	JPanel Borrow;
	
	public BookBorrow(String name) {
		super(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setName(name);
		Borrow = new JPanel();
		Borrow.setName("借阅记录");
		this.add(Borrow);
		
	}
	
}













class BookSearch extends JTabbedPane{// 馆藏书籍 显示 Manager & Students
	/*
	 * 查询馆藏书籍
	 */
	JPanel Search;
	
	public BookSearch(String name) {
		super(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setName(name);
		Search = new JPanel();
		Search.setName("图书查询");
		this.add(Search);
	}
	
	
}



class Transmit{//值传递
	static public String UserNumber;
	static public String PassWord;
	static public boolean isManager;
	static public Font font;
	static public Connection databaseConnector=null;
}


