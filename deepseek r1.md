# 数据定义

> 数据定义语言（DDL）：数据库对象的创建、修改、删除——————数据库模式、表、视图、索引



## 数据库模式定义：

是数据库的逻辑结构，描述了数据库中对象的组织方式。

SQL 提供 `CREATE SCHEMA` 语句来定义数据库模式，它通常包含表、视图、索引等对象的集合。

```mysql
CREATE SCHEMA University;
```

该命令创建一个名为 `University` 的模式，可以**在该模式下定义表、视图等对象**。模式的作用在于**组织数据库对象**，使其更加清晰和可管理。





## 表的定义：创建表

SQL 通过 `CREATE TABLE` 语句定义表的结构，包括列名、数据类型、约束等。

```
CREATE TABLE Student (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Age INT, 
    Major VARCHAR(50),
    EnrollmentDate DATE DEFAULT (CURRENT_DATE)
);
```



## 修改表

```
# 添加列
alter table student add column sname varchar(30);

# 删除列
alter table student drop column sname;

# 修改列
# （1）修改列属性的数据范围
alter table student modify column sname char(30); 
# （2）修改列名称
alter table student rename column sname to snames;
# （3）修改表名称
alter table student rename to students;

# 约束的修改
# （1）添加约束
alter table student add constriant pk_student primary key(sno);

# （2）删除约束
alter table student drop constraint pk_student;

```

