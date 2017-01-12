package com.sort.external;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class WriterNum {
 
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<Integer>();
        try {
            FileOutputStream fout = new FileOutputStream("./sort.txt");
            FileChannel mbb = fout.getChannel();
            for (int i = 1; i <= (2 << 21); i++) {
                arrayList.add(i);
 
                if (i % (2 << 10) == 0) {
                    Collections.shuffle(arrayList);
                    for (int j = 0; j < arrayList.size(); j++) {
                        mbb.write(ByteBuffer.wrap(String.valueOf(arrayList.get(j)).getBytes()));
                        mbb.write(ByteBuffer.wrap("\n".getBytes()));
                    }
                    arrayList.clear();
                }
            }
            mbb.close();
            fout.close();
            System.out.println("success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}













