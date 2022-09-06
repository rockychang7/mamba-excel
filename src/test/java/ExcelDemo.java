import com.alibaba.fastjson.JSON;
import com.joebig7.MambaExcelFactory;
import com.joebig7.core.data.ContentDataBuilder;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.data.HeaderDataBuilder;
import com.joebig7.core.listener.DataReadListener;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.joebig7.enums.FieldTypeEnum.*;

/**
 * @Author JoeBig7
 * @date 2021/7/20 13:57:34
 */

public class ExcelDemo {
    /**
     * 读取excel内容
     */
    @Test
    public void testExcelReader() throws Exception {
        //指定excel标题头，并且指定每个字段的类型
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name")
                .fill("age", INTEGER)
                .fill("address")
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();

//        MambaExcelFactory
//                .build()  //创建MambaExcelFactory实例
//                .readInstance("./user.xlsx", User.class, headerDataList)//初始化reader实例，指定文件，返回类型和header字段
//                .read(new DataReadListener<>((users -> {
//                    //可以对查询到的结果进行特定逻辑的处理
//                    System.out.println(JSON.toJSONString(users));
//                })));

        InputStream inputStream = new FileInputStream("./user.xlsx");

        MambaExcelFactory
                .build()  //创建MambaExcelFactory实例
                .readInstance(inputStream,"xlsx", User.class, headerDataList)//初始化reader实例，指定文件，返回类型和header字段
                .read(new DataReadListener<>((users -> {
                    //可以对查询到的结果进行特定逻辑的处理
                    System.out.println(JSON.toJSONString(users));
                })));
    }


    /**
     * 生成excel内容 内容通过指定对象创建
     */
    @Test
    public void testExcelWriter() {
        //创建header(生成excel header不是必须的，但是如果不指定header元素会被当做String类型处理)
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .fill("age", INTEGER)
                .fill("address", STRING)
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();

        //创建内容
        List<List<Object>> contentList = ContentDataBuilder.instance(headerDataList)
                .fillObject(new User("jack", 29, "shanghai", 1111.1, true))
                .fillObject(new User("rose", 24, "beijing", 12000d, true))
                .fillObject(new User("jack", 29, "shanghai", 11111.1, true))
                .build();
        //生成excel
        MambaExcelFactory
                .build()
                .writeInstance("./user2.xlsx", headerDataList, contentList)
                .write();

        //生成excel无header
        MambaExcelFactory
                .build()
                .writeInstance("./user2-1.xlsx", contentList)
                .write();

    }


    /**
     * 生成excel内容 通过具体List<User>指定
     */
    @Test
    public void testExcelWriter2() {
        List<User> users = new ArrayList<>();
        User user1 = new User("jack", 29, "shanghai", 12111d, true);
        User user2 = new User("rose", 24, "beijing", 12000d, true);
        User user3 = new User("jack", 29, "shanghai", 11111.1, true);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        List<List<Object>> contentList = ContentDataBuilder
                .instance()
                .fill(users).build();

        MambaExcelFactory.build().writeInstance("./user3.xlsx", contentList).write();

    }

    /**
     * 生成excel内容 通过具体List<Object>指定
     */
    @Test
    public void testExcelWriter3() {
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .fill("age", INTEGER)
                .fill("address", STRING)
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();


        List<Object> users = new ArrayList<>();
        User user1 = new User("jack", 29, "shanghai", 12111d, true);
        User user2 = new User("rose", 24, "beijing", 12000d, true);
        User user3 = new User("jack", 29, "shanghai", 11111.1, true);
        users.add(user1);
        users.add(user2);
        users.add(user3);


        //指定content, 默认users会被当做多行，并且元素不会转成具体对象处理
        List<List<Object>> contentList = ContentDataBuilder
                .instance(headerDataList)
                .fill(users)
                .build();

        //指定content, 并且指定转换
        List<List<Object>> contentList2 = ContentDataBuilder
                .instance(headerDataList)
                .fillWithTransform(users)
                .build();


        //生成excel
        MambaExcelFactory
                .build()
                .writeInstance("./user4-1.xlsx", headerDataList, contentList)
                .write();

        //生成excel
        MambaExcelFactory
                .build()
                .writeInstance("./user4-2.xlsx", headerDataList, contentList2)
                .write();


    }

    /**
     * 生成excel内容 将List当做单行处理
     */
    @Test
    public void testExcelWriter5() {
        List<Object> users = new ArrayList<>();
        Object name1 = "joe";
        Object name2 = "rose";
        Object name3 = "jack";
        users.add(name1);
        users.add(name2);
        users.add(name3);


        //指定content, 并且指定转化对象，同时List对象当做一行处理
        List<List<Object>> contentList3 = ContentDataBuilder
                .instance()
                .fillWithOneLine(users)
                .build();

        //生成excel
        MambaExcelFactory
                .build()
                .writeInstance("./user5.xlsx", contentList3)
                .write();
    }
}
