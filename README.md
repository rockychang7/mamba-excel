## 1 mamba-excel
一个简单的excel/csv工具类

## 2 supported feature
-  自定义生成excel内容
-  自定义读取excel的格式
-  自定义生成csv内容
-  自定义读取csv内容

## 3 如何使用
1.  `git clone https://github.com/Joebig7/mamba-excel.git`
2.  `git clone https://github.com/Joebig7/mamba-tool.git` 依赖工具类


## 4 user guide

### excel的读取和生成
- 读取
```
@Test
public void testExcelReader() {
    //指定excel标题头，并且指定每个字段的类型
    List<HeaderData> headerDataList = HeaderDataBuilder.instance()
            .fill("name", STRING)
            .fill("age", INTEGER)
            .fill("address", STRING)
            .fill("salary", DOUBLE)
            .fill("married", BOOLEAN)
            .build();

    MambaExcelFactory.build().readInstance("E:/user.xlsx", User.class, headerDataList)
            .read(new DataReadListener<>((users -> {
                //可以对查询到的结果进行特定逻辑的处理
                System.out.println(JSON.toJSONString(users));
            })));
}
```

- 生成
```
@Test
public void testExcelWriter() {

    List<HeaderData> headerDataList = HeaderDataBuilder.instance()
            .fill("name", STRING)
            .fill("age", INTEGER)
            .fill("address", STRING)
            .fill("salary", DOUBLE)
            .fill("married", BOOLEAN)
            .build();

    List<List<Object>> contentList = ContentDataBuilder.instance(headerDataList)
            .fill(new User("jack", 29, "shanghai", 1111.1, true)).build();


    MambaExcelFactory.build().writeInstance("./user2.xlsx", headerDataList, contentList).write();


}
```

### csv的读取和生成
- 读取
```
@Test
public void testCsvReader() {
    //指定csv标题头，并且指定每个字段的类型
    List<HeaderData> headerDataList = HeaderDataBuilder.instance()
            .fill("name", STRING)
            .fill("age", INTEGER)
            .fill("address", STRING)
            .fill("salary", DOUBLE)
            .fill("married", BOOLEAN)
            .build();

    MambaExcelFactory.build().readInstance("./user.csv", User.class, headerDataList)
            .read(new DataReadListener<>((users -> {
                //可以对查询到的结果进行特定逻辑的处理
                System.out.println(JSON.toJSONString(users));
            })));
}
```

- 生成
```
@Test
public void testCsvWriter() {

    List<HeaderData> headerDataList = HeaderDataBuilder.instance()
            .fill("name", STRING)
            .fill("age", INTEGER)
            .fill("address", STRING)
            .fill("salary", DOUBLE)
            .fill("married", BOOLEAN)
            .build();

    List<List<Object>> contentList = ContentDataBuilder.instance(headerDataList)
            .fill(new User("jack", 29, "shanghai", 1111.1, true)).build();


    MambaExcelFactory.build().writeInstance("./user2.csv", headerDataList, contentList).write();
}
```


###  核心类

- MambaExcelFactory：用来操作excel和csv的核心类