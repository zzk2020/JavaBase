package cn.qiaqiatech.learn.pattern.factory.abstractfactory;

import cn.qiaqiatech.learn.pattern.factory.ICourse;
import cn.qiaqiatech.learn.pattern.factory.INote;
import cn.qiaqiatech.learn.pattern.factory.ISource;
import cn.qiaqiatech.learn.pattern.factory.IVideo;

// 要求所有的子工厂都实现改工厂
// （一个品牌的抽象）
// 不符合开闭原则，新加产品时需要改很多代码
public interface ICourseFactory {

    ICourse createCourse();

    INote createNode();

    IVideo createVideo();

    ISource createSource();
}
