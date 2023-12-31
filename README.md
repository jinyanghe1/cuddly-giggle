# all2csv
- **一个基于控制台的将excel文件和.csv文件进行转换的工具**
## contents
- 基本框架、核心功能
- 附加功能和特色
- 使用示例
- 开发日志
## 基本框架和核心功能
- 从控制台读取输入文件，通过`-etoc`, `-ctoe` 表示转换的方式，后面跟文件名称，默认在`cwd`下寻找，随后进行格式转换
- 一个`getter`类处理输入，之后在文件夹下进行查询，将文件进行转换
## 业务逻辑和使用方法
- 支持`bash`语法：
  - 支持一次处理多个文件，如`al2csv -e 1.csv 2.csv`
  - 支持pattern matching：
    - `{1..10}.csv`->`1.csv 2.csv ... 10.csv`
    - `*.csv`->所有`.csv`文件
    - `-rg`正则表达式支持
- 默认搜索深度为`1`，可以通过`-depth <depth>`进行指定，使用`-r`表示recursive search。输出文件可以选择`-tree`（保留层级关系）或`-flat`（全部输出到一个文件夹中）
- 语法糖：`al2csv -c x.csv`或`al2csv -c x`都可以匹配同一个文件
- 实时转换：`|` 选项支持将读入文件结果直接重定向至shell
- `-h` 帮助菜单
- 提供可定制的`shell`脚本，一键式执行重复工作，简化工作流
## 开发日志
12.12：
- 完成提示功能，完成输入行解析功能
12.14 
- 完成了`xlsx`->`csv`，经过研究，发现将`csv`转为`xlsx`意义似乎不太大，遂取消。
