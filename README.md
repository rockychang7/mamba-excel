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
- [读取和生成操作](./src/test/java/ExcelDemo.java)
 
###  核心类

- MambaExcelFactory：用来操作excel和csv的核心类
- ContentDataBuilder:构建输出内容
- HeaderDataBuilder：构建header字段
