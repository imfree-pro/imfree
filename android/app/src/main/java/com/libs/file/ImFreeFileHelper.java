package com.libs.file;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by 종열 on 2015-06-23.
 */
public abstract class ImFreeFileHelper {

    private Context _context;
    public ImFreeFileHelper(Context context)
    {
        _context = context;
    }

    protected  Context getContext()
    {
        return _context;
    }

    public abstract String FileName();


    public File getTempFile()
    {
        File file = null;
        try{
            file = File.createTempFile( FileName(), ".tmp", _context.getCacheDir());
        }catch( IOException e){ }

        return file;
    }

    public boolean writeFile(String file_content)
    {
        boolean result;
        File file = getTempFile();
        FileOutputStream fos;
        if(file != null && file.exists() && file_content != null){
            try {
                fos = new FileOutputStream(file);
                try {
                    fos.write(file_content.getBytes(Charset.forName("UTF-8")));
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    public String readFile()
    {
        int readcount=0;
        String result = "";
        File file = getTempFile();
        if( file!=null && file.exists() ){
            try {
                FileInputStream fis = new FileInputStream(file);
                readcount = (int)file.length();
                byte[] buffer = new byte[readcount];
                fis.read(buffer);
                fis.close();
                result = new String(buffer, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void removeFile()
    {
        File file = getTempFile();
        file.delete();
    }
}
