package Excel;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {

    private String description; // �ļ���������
    private String[] ends = null; // �ļ���׺

    public MyFileFilter(String ends, String description) { // ���캯��
        this.ends = new String[1];
        this.ends[0] = ends; // �����ļ���׺
        this.description = description; // �����ļ���������
    }

    public MyFileFilter(String[] ends, String description) { // ���캯��
        this.ends = ends; // �����ļ���׺
        this.description = description; // �����ļ���������
    }

    @Override
    public boolean accept(File file) { // ����FileFilter�е�accept����
        if (file.isDirectory()) // �����Ŀ¼,�򷵻�true
        {
            return true;
        }
        String fileName = file.getName(); // �õ��ļ�����
        for (int i = 0; i < ends.length; i++) {
            String string = ends[i];
            if (fileName.toUpperCase().endsWith(string.toUpperCase())) // ���ļ���׺��ɽ��ܺ�׺ת�ɴ�д��Ƚ�
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() { // �����ļ���������
        return description;
    }
}
