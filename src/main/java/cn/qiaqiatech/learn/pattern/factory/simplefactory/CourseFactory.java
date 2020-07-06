package cn.qiaqiatech.learn.pattern.factory.simplefactory;

import cn.qiaqiatech.learn.pattern.factory.ICourse;
import cn.qiaqiatech.learn.pattern.factory.JavaCourse;
import cn.qiaqiatech.learn.pattern.factory.PythonCourse;

public class CourseFactory {

//    public ICourse create(String name) {
//        if("java".equals(name)) {
//            return new JavaCourse();
//        } else if ("python".equals(name)) {
//            return  new PythonCourse();
//        }
//        return null;
//    }


    public ICourse create(String className) {
        try {
            if(!(null == className || "".equals(className = className.trim()))) {
                return (ICourse) Class.forName(className).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public ICourse create(Class clazz) {
        try {
            if(null != clazz) {
                return (ICourse) clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
