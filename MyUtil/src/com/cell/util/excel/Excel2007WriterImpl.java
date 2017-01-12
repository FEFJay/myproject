package com.cell.util.excel;


public class Excel2007WriterImpl extends AbstractExcel2007Writer {

	/*
	 * 可根据需求重写此方法，对于单元格的小数或者日期格式，会出现精度问题或者日期格式转化问题，建议使用字符串插入方法
	 * 
	 * @see com.excel.ver2.AbstractExcel2007Writer#generate()
	 */
	@Override
	public void generate() throws Exception {
		
		// 电子表格开始
		beginSheet();

		for (int i = 0; i < 100 ; i++) {
			System.out.println(i);
			// 插入新行
			insertRow(i);

			createCell(0,"ok");
			createCell(1,"yes");

			// 结束行
			endRow();
		}

		// 电子表格结束
		endSheet();
		
	}


}