package com.serial.swing;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class SwingJTable extends JFrame{
	public static void main(String[] args) {
		SwingJTable j = new SwingJTable();
		j.setVisible(true);
		}
	
	public SwingJTable() {
		super();
		setTitle("不可滚动表格");
		setBounds(100,400,540,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Vector<String> columnNameV = new Vector<>();
		columnNameV.add("A");
		columnNameV.add("B");
		Vector<Vector<String>> tableValueV = new Vector<>();
		for (int i = 0; i < 6; i++) {
			Vector<String> rowV = new Vector<>();
			rowV.add("A"+i);
			rowV.add("B"+i);
			tableValueV.add(rowV);
		}
		JTable table = new JTable(tableValueV,columnNameV);
		getContentPane().add(table,BorderLayout.CENTER);
		JTableHeader tableHeader = table.getTableHeader();
		getContentPane().add(tableHeader,BorderLayout.NORTH);
	}
}
