package com.doysoft.q_radio.logcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.doysoft.app.radio.R;

import android.util.Log;
import android.widget.Toast;

public class Mylog {

	
        public static  void getLog()
        {
            System.out.println("--------func start--------"); // 方法启动
            try
            {
                ArrayList<String> cmdLine=new ArrayList<String>();   //设置命令   logcat -d 读取日志
                cmdLine.add("logcat");
                cmdLine.add("-d");
                
                ArrayList<String> clearLog=new ArrayList<String>();  //设置命令  logcat -c 清除日志
                clearLog.add("logcat");
                clearLog.add("-c");
                
                Process process=Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));   //捕获日志
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));    //将捕获内容转换为BufferedReader
                
         
//                Runtime.runFinalizersOnExit(true);
                String str=null;
                while((str=bufferedReader.readLine())!=null)    //开始读取日志，每次读取一行
                {
                    Runtime.getRuntime().exec(clearLog.toArray(new String[clearLog.size()]));  //清理日志....这里至关重要，不清理的话，任何操作都将产生新的日志，代码进入死循环，直到bufferreader满
                    System.out.println(str);    //输出，在logcat中查看效果，也可以是其他操作，比如发送给服务器..
                 //   Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
                }
                if(str==null)
                {
                    System.out.println("--   is null   --");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("--------func end--------");
        }
    
	
        
        public void getlog() throws InterruptedException, IOException{
        	Runtime.getRuntime().exec("logcat -c").waitFor();

        	Process process = Runtime.getRuntime().exec("logcat -v long *:*");
        	BufferedReader reader = 
        	    new BufferedReader(new InputStreamReader(process.getInputStream()));
        	while (true) {
        	    String nextLine = reader.readLine();
        	    if (!nextLine.contains("LogWatcher-D")) {
        	//        Log.w("LogWatcher-D", "See: " + nextLine);
        	    }
        }
        }
        
        
        public void get() throws IOException{
        	  Process process = Runtime.getRuntime().exec("logcat -e");
        	   
        	               BufferedReader bufferedReader = new BufferedReader(
        	   
        	               new InputStreamReader(process.getInputStream()));
        	   
        	               StringBuilder log = new StringBuilder();
        	   
        	               String line;
        	   
        	               while ((line = bufferedReader.readLine()) != null) {
        	   
        	                   log.append(line);
        	   
        	                   
        	               }
        }
        
        
        
}
