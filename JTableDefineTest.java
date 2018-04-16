package com.serial.swing;
import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Frame;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Vector;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.ListSelectionModel;  
import javax.swing.table.DefaultTableCellRenderer;  
import javax.swing.table.DefaultTableModel;  
import javax.swing.table.JTableHeader;  
import javax.swing.table.TableCellRenderer;  
import javax.swing.JPanel;  
import javax.swing.JButton;  
  
public class JTableDefineTest extends JFrame{     
  
     private int currentPage=1;  
     private  int pageSize=2;  
     private int lastPage;  
     JTable table=null;  
     DefaultTableModel dtm=null;  
     JScrollPane jsp=null;  
     JTableDefineTest jTableDefineTest=null;  
     List list,list1;   
     JButton button1 =null;  
         
    public int getLastPage() {  
        return lastPage;  
    }  
  
    public void setLastPage(int lastPage) {  
        this.lastPage = lastPage;  
    }  
  
    public int getCurrentPage() {  
        return currentPage;  
    }  
  
    public void setCurrentPage(int currentPage) {  
        this.currentPage = currentPage;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public JTableDefineTest(){    
          
        list=FenYeTest.list;  
          
        if(list.size()%pageSize==0){  
            setLastPage(list.size()/getPageSize());  
        }else{  
            setLastPage(list.size()/getPageSize()+1);  
        }  
          
          
        String[] columnNames = {"�û���","����"};   
        dtm=new DefaultTableModel(columnNames, 0);  
          
         table=new JTable(dtm);  
         jsp = new JScrollPane();  
         jsp.setViewportView(table);  
         getContentPane().add(jsp);  
      
        setTitle("���");         
        setBounds(100,100,500,500);         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
      
        showTable(currentPage);  
          
        JPanel panel = new JPanel();  
        getContentPane().add(panel, BorderLayout.NORTH);  
          
        JButton button = new JButton("��ҳ");  
        button.addActionListener(new MyTable());  
        button.setActionCommand("��ҳ");  
        panel.add(button);  
         button1 = new JButton("��һҳ");  
        button1.addActionListener(new MyTable());  
        panel.add(button1);  
        JButton button2 = new JButton("��һҳ");  
        button2.addActionListener(new MyTable());  
        panel.add(button2);  
        JButton button3 = new JButton("ĩҳ");  
        button3.addActionListener(new MyTable());  
        panel.add(button3);  
        setVisible(true);      
          
        }     
      
    public void showTable(int currentPage){  
        dtm.setRowCount(0);// ���ԭ����  
        FenYeTest f=new FenYeTest();  
        setCurrentPage(currentPage);  
       list1=f.findUsers(currentPage, pageSize);  
        for(int row = 0;row<list1.size();row++)    //�������       
            {   
                Vector rowV = new Vector();                
                User user= (User) list1.get(row);            
                rowV.add(user.getAaa());  //����     
                rowV.add(user.getBbb());   
            dtm.addRow(rowV);      
            }     
           
          
    //  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //�رձ���е��Զ��������ܡ�         
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //��ѡ        
        table.setSelectionBackground(Color.YELLOW);         
        table.setSelectionForeground(Color.RED);   
        table.setRowHeight(30);    
    }  
      
    public  void init(){  
          
    }  
      
    public static void main(String[] args) {          
         new JTableDefineTest();                          
    }  
      
      
    class MyTable  implements ActionListener    
    {        
        public void actionPerformed(ActionEvent e) {  
                if(e.getActionCommand().equals("��ҳ")){  
                    showTable(1);  
                }  
                  
                if(e.getActionCommand().equals("��һҳ")){  
                    if(getCurrentPage()<=1){  
                        setCurrentPage(2);  
                    }  
                    showTable(getCurrentPage()-1);  
                }  
                  
                if(e.getActionCommand().equals("��һҳ")){  
                    if(getCurrentPage()<getLastPage()){  
                        showTable(getCurrentPage()+1);  
                    }else{  
                        showTable(getLastPage());  
                    }  
                }  
                  
                if(e.getActionCommand().equals("ĩҳ")){  
                    showTable(getLastPage());  
                }  
            }     
        }  
    }  