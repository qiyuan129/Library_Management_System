import javax.swing.*;

public class WorkFrame extends JFrame {

    WorkFrame(){
        if(Transmit.isManager) {//是管理员
            JFrame WorkFrame = new JFrame("图书馆信息管理系统");
            WorkFrame.setSize(1600, 1600);
            WorkFrame.setVisible(true);
            JTabbedPane TabPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT );
            WorkFrame.add(TabPanel);

            Books BooksManagePanel = new Books("藏书管理");
            ManagePerson PersonalDataPanel = new ManagePerson("读者信息管理");
            Borrow BorrowPanel = new Borrow("图书借阅管理");
            BookSearch Search = new BookSearch("图书检索");
            PersonalData PersonalPanel = new PersonalData("个人信息");
            BookBorrow BorrowRecord = new BookBorrow("个人借阅记录");

            TabPanel.add(BooksManagePanel);
            TabPanel.add(PersonalDataPanel);
            TabPanel.add(BorrowPanel);
            TabPanel.add(PersonalPanel);
            TabPanel.add(Search);
            TabPanel.add(BorrowRecord);

        }

        else {//是读者
            JFrame WorkFrame = new JFrame("图书馆信息管理系统");
            WorkFrame.setSize(1600, 1600);
            WorkFrame.setVisible(true);
            JTabbedPane TabPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT );
            WorkFrame.add(TabPanel);

            BookSearch Search = new BookSearch("图书检索");
            PersonalData PersonalPanel = new PersonalData("个人信息");
            BookBorrow BorrowRecord = new BookBorrow("个人借阅记录");

            TabPanel.add(PersonalPanel);
            TabPanel.add(Search);
            TabPanel.add(BorrowRecord);

        }
    }
}
