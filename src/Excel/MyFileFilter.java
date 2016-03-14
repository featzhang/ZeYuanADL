package Excel;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {

    private String description; // 文件描述文字
    private String[] ends = null; // 文件后缀

    public MyFileFilter(String ends, String description) { // 构造函数
        this.ends = new String[1];
        this.ends[0] = ends; // 设置文件后缀
        this.description = description; // 设置文件描述文字
    }

    public MyFileFilter(String[] ends, String description) { // 构造函数
        this.ends = ends; // 设置文件后缀
        this.description = description; // 设置文件描述文字
    }

    @Override
    public boolean accept(File file) { // 重载FileFilter中的accept方法
        if (file.isDirectory()) // 如果是目录,则返回true
        {
            return true;
        }
        String fileName = file.getName(); // 得到文件名称
        for (int i = 0; i < ends.length; i++) {
            String string = ends[i];
            if (fileName.toUpperCase().endsWith(string.toUpperCase())) // 把文件后缀与可接受后缀转成大写后比较
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() { // 返回文件描述文字
        return description;
    }
}
