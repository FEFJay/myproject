package com.cell.util.excel;

import java.util.List;

public class RowReader implements IRowReader {
	
	private String taskSign;

	/*
	 * 业务逻辑实现方法
	 * 
	 * @see com.eprosun.util.excel.IRowReader#getRows(int, int, java.util.List)
	 */
	public void getRows(int sheetIndex, int curRow,List<Integer> collist, List<String> rowlist) {
		// TODO Auto-generated method stub
		System.out.print(curRow + " ");
		for (int i = 0; i < rowlist.size(); i++) {
			System.out.print(collist.get(i) + ":" + rowlist.get(i) + ",");
		}
		System.out.println();
	}

	public String getTaskSign() {
		return taskSign;
	}

	public void setTaskSign(String taskSign) {
		this.taskSign = taskSign;
	}


}
