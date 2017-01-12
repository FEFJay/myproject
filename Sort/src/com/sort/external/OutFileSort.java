package com.sort.external;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class OutFileSort {
    static class Memory {
 
        public static long getMaxHeapMemory() {
            MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
            return mmb.getHeapMemoryUsage().getMax();
        }
 
        public static long getInitHeapMemory() {
            MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
            return mmb.getHeapMemoryUsage().getInit();
        }
 
        public static long getUsedHeapMemory() {
            MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
            return mmb.getHeapMemoryUsage().getUsed();
        }
    }
 
    /*
     * 内部排序算法：折半插入排序
     * @param 数组
     * @param b待插入元素
     * */
    public static int binaryInsertSort(List<Integer> a, int b) {
        int low = 0, high = a.size() - 1;
        while (low <= high) {
            int m = (low + high) / 2;
 
            if (b == a.get(m)) {
                while (m < a.size() && b == a.get(m)) {
                    m++;
                }
                high = m - 1;//因为在上一层循环中最后m++多了1，而这里想用high表示新插入元素的最终下标，所以需要减一
                break;
            } else if (b > a.get(m)) {
                high = m - 1;
            } else {
                low = m + 1;
            }
        }
        a.add(high + 1, b);
        return high + 1;
    }
 
 
    public static void main(String[] args) {
        int memorySize = (int) Memory.getMaxHeapMemory() / 8;
        long maxLen = memorySize;
        List<Integer> list = new ArrayList<Integer>();
        int filenum = 0;
        BufferedReader reader = null;
        FileChannel mergesortFile = null;
        try {
        	//读取待排序文件
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("./sort.txt")));
            String numstr = null;
            while ((numstr = reader.readLine()) != null) {
                list.add(Integer.parseInt(numstr)); //往内存中的list表读数据
                maxLen -= numstr.length();//可使用的内存空间缩小
                
                if (maxLen <= 0) {//当可以存储的内存空间使用完，说明这一次数据读取结束。已经读到的数据就是一次切割子文件。
                    maxLen = memorySize;//重新赋值给下次切割可用的内存空间
                    
                    Collections.sort(list);//调用系统的排序
                    
                    //输出排序结果到子文件
                    filenum++;
                    FileOutputStream fout = null;
                    try {
                        fout = new FileOutputStream("sort" + filenum + ".txt");
                        FileChannel mbb = fout.getChannel();
 
                        for (int i = 0; i < list.size(); i++) {
                            mbb.write(ByteBuffer.wrap(String.valueOf(list.get(i)).getBytes()));
                            mbb.write(ByteBuffer.wrap("\n".getBytes()));
                        }
                        mbb.close();
                    } finally {
                        list.clear();
                        System.out.println(list.size());
                        fout.close();
                    }
 
                }
 
                
            }//while 大文件已经被切割成多个有序的子文件
            
            //处理循环中最后一次切割结果
            if (maxLen != (memorySize)) {
                Collections.sort(list);
                filenum++;
                FileOutputStream fout = null;
                try {
                    fout = new FileOutputStream("sort" + filenum + ".txt");
                    FileChannel mbb = fout.getChannel();
 
                    for (int i = 0; i < list.size(); i++) {
                        mbb.write(ByteBuffer.wrap(String.valueOf(list.get(i)).getBytes()));
                        mbb.write(ByteBuffer.wrap("\n".getBytes()));
                    }
                    mbb.close();
                } finally {
                    list.clear();
                    System.out.println(list.size());
                    fout.close();
                }
            }
            
            
            list = null;
            try {
            	//获取每个有序子文件的输入流
                List<BufferedReader> fileChannelList = new ArrayList<BufferedReader>();
                for (int i = 1; i <= filenum; i++) {
                    BufferedReader subFileReader = new BufferedReader(new InputStreamReader(new FileInputStream("sort"+ i + ".txt")));
                    fileChannelList.add(subFileReader);
                }
                
                //进行多路归并
                @SuppressWarnings("resource")
                FileOutputStream fout1 = new FileOutputStream("mergersort.txt");
                mergesortFile = fout1.getChannel();
                List<BufferedReader> filechanlIndexList = new ArrayList<BufferedReader>(); //这是归并目标文件
                List<Integer> numList = new ArrayList<Integer>();
                if (fileChannelList.size() > 0) {
                	
                	//读取所有有序子文件的第一行内容
                    for (int i = 0; i < fileChannelList.size(); i++) {
                        String sunnumstr = fileChannelList.get(i).readLine();//读取第一行，也就是每个子文件的第一个数（在子文件里面是最大或最小）
                        if (sunnumstr != null) {
                            int index = binaryInsertSort(numList,Integer.parseInt(sunnumstr));//在归并目标文件里面所属的下标
                            filechanlIndexList.add(index,fileChannelList.get(i)); //添加到归并目标文件
                        } else {//为空则表示这子文件没有内容，可以删掉
                            fileChannelList.remove(i).close();
                        }
                    }
                    fileChannelList.clear();
                    
                    while (filechanlIndexList.size() > 0) {
                        if (filechanlIndexList.size() != numList.size()) {
                            throw new RuntimeException("list.size["
                                    + numList.size()
                                    + "] != filechanlIndexList.size["
                                    + filechanlIndexList.size() + "] ");
                        }
                        mergesortFile
                                .write(ByteBuffer.wrap(String.valueOf(
                                        numList.remove(numList.size() - 1))
                                        .getBytes()));
                        mergesortFile.write(ByteBuffer.wrap("\n".getBytes()));
                        BufferedReader mixBufferedReader = filechanlIndexList
                                .remove(filechanlIndexList.size() - 1);
                        String sunnumstr = mixBufferedReader.readLine();
                        if (sunnumstr != null) {
                            int index = binaryInsertSort(numList,
                                    Integer.parseInt(sunnumstr));
                            filechanlIndexList.add(index, mixBufferedReader);
                        } else {
                            mixBufferedReader.close();
                        }
                    }
 
                }
                
                
                
                for (int i = 1; i <= filenum; i++) {
                    File file = new File("sort" + i + ".txt");
                    if (file.exists() && file.isFile()) {
                        file.delete();
                    }
                }
            } finally {
                mergesortFile.close();
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
}