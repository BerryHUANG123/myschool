import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;

/**
 * @author :huangjinyang
 * @Date :2018/7/19
 * @Time :19:20
 * @Description :
 */
public class Tess4JTest {
    /*public static void main(String[] args){

        String path = "D://Java//Tess4J";		//我的项目存放路径

        File file = new File(path + "//photo3.jpg");
        ITesseract instance = new Tesseract();

        *//**
         *  获取项目根路径，例如： D:\IDEAWorkSpace\tess4J
         *//*
        File directory = new File(path);
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
            System.out.println(courseFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置训练库的位置
        instance.setDatapath(courseFile + "//tessdata");

        instance.setLanguage("eng");//chi_sim ：简体中文， eng	根据需求选择语言库
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result =  instance.doOCR(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        System.out.println("result: ");
        System.out.println(result);
    }*/

}
