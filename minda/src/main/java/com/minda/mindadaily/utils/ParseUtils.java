package com.minda.mindadaily.utils;

import com.minda.mindadaily.model.EverydayCourse;
import com.minda.mindadaily.model.MyTable;
import com.minda.mindadaily.model.SingleCourseInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kun on 2017/1/19.
 */

public class ParseUtils {
    private static final int BUFFER_SIZE = 1024;

    private List<EverydayCourse> parse(String result) {
        List<EverydayCourse> weekCourse = new ArrayList<EverydayCourse>();
        Document doc = Jsoup.parse(result);
        Element e0 = doc.select("table.CourseFormTable").first();

        MyTable myTable = TableParser.parser(e0);
        for (int week = 2; week < myTable.getColLength(); week++) {
            //每天的课程
            EverydayCourse everydayCourse = new EverydayCourse();
            for (int courseIndex = 1; courseIndex < myTable.getRowLength(); courseIndex++) {
                // 每节课信息
                SingleCourseInfo singleCourse = CourseParser.parser(myTable.getCell(courseIndex, week));
                // 每日第几节
                singleCourse.setNumOfDay(courseIndex + "");
                //周几的课
                singleCourse.setNumOfWeek(week - 1);
                everydayCourse.getDayOfWeek().add(singleCourse);
            }
            weekCourse.add(everydayCourse);
        }

        return weekCourse.isEmpty() ? null : weekCourse;
    }

    public static String parse_login_result(String result) {
        Document doc = Jsoup.parse(result);
        Elements AlrtErrTxts = doc.getElementsByClass("AlrtErrTxt");
        String str = "";
        if (AlrtErrTxts.hasText()) {
            str = AlrtErrTxts.get(0).text();
        }
        return str;
    }

    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static String InputStreamTOString(InputStream in) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), "ISO-8859-1");
    }
}
