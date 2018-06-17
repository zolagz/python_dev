# mongodb使用

### 创建数据库

语法： use 数据库名

**注意**：如果该数据库不存在，则创建，如果该数据存在，则是切换，如果创建了数据库，没有做任何操作，则会自动删除该数据库。

### 查看数据库

语法：show dbs


### 创建集合

注意：mongodb里面的集合是隐式创建的，就是无需创建，直接使用

语法：db.集合名.insert({}) 该语法意思是，向集合里面，添加文档

![](http://p2ehgqigv.bkt.clouddn.com/18-6-17/64531078.jpg)

![](http://p2ehgqigv.bkt.clouddn.com/18-6-17/98154562.jpg)

### 查看集合

语法1 ： show collections

语法2： show tables;


### 查看集合里的文档

语法：
 
db.集合名.find()  查询所有

db.集合名.findOnd() 查询第一个文档


### 删除集合

语法：db.集合名.drop()

### 删除数据库,删除当前所有的数据库

语法:db.dropDatabase()


### 帮助命令

全局帮助命令

语法：help

数据库相关的帮助命令

语法:db.help()

集合相关的帮助命令：
语法：db.集合名.help()



###增删改查

#### 插入数据
插入数据，随着数据的插入，数据库创建成果了，集合也创建成果了
文档就是键值对，数据类型是bson格式，支持的格式更加丰富，BSON就是json的扩展，他先增加了诸如日期，浮点等json不支持的数据类型

```
db.表名.insert({"name":"zhangsan","age":18})
```
####查找数据

1 查找所有记录

```
db.userInfo.find()
```
相当于select * from userInfo;

查询集合中的文档 ，使用条件表达式(<, <=, >, >=,!=)

```
//大于：field > valuedb.collection.find({field:{$gt:value}});//小于：field < valuedb.collection.find({field:{$lt:value}});//大于等于：field >= valuedb.collection.find({field:{$gte:value}});//小于等于：field <= valuedb.collection.find({field:{$lte:value}});//不等于：  field!= valuedb.collection.find({field:{$ne:value}});
```


###查询集合中的文档 ,$all主要用来查询数组中的包含关系，查询条件中只要有一个不包含就不返回



###查询集合中的文档 ,$in，类似于关系型数据库中的IN
```
> db.hello.find()
{ "_id" : ObjectId("5b2604d3f448672e65fccd88"), "name" : "zhang san", "age" : 19 }
{ "_id" : ObjectId("5b260badf448672e65fccd89"), "name" : "lisi", "age" : 9 }
{ "_id" : ObjectId("5b260bbaf448672e65fccd8a"), "name" : "wangwu", "age" : 10 }
{ "_id" : ObjectId("5b260bc6f448672e65fccd8b"), "name" : "heihei", "age" : 20 }
{ "_id" : ObjectId("5b260bd2f448672e65fccd8c"), "name" : "xiaohua", "age" : 8 }

> db.hello.find({age:{$in:[8,9,10]}})
{ "_id" : ObjectId("5b260badf448672e65fccd89"), "name" : "lisi", "age" : 9 }
{ "_id" : ObjectId("5b260bbaf448672e65fccd8a"), "name" : "wangwu", "age" : 10 }
{ "_id" : ObjectId("5b260bd2f448672e65fccd8c"), "name" : "xiaohua", "age" : 8 }
> 

```

 查询集合中的文档 ,$nin，与$in相反
 
```
> db.hello.find({age:{$nin:[8,9,10]}})
{ "_id" : ObjectId("5b2604d3f448672e65fccd88"), "name" : "zhang san", "age" : 19 }
{ "_id" : ObjectId("5b260bc6f448672e65fccd8b"), "name" : "heihei", "age" : 20 }
> 
``` 查询集合中的文档 ,$or，相当于关系型数据库中的OR，表示或者的关系，例如查询name为user2或者age为3的文档，命令为：
 db.hello.find({$or:[{name:”user2”},{age:3}]})

![](http://p2ehgqigv.bkt.clouddn.com/18-6-17/49867081.jpg)

查询集合中的文档 ,$nor，表示根据条件过滤掉某些数据，例如查询name不是user2，age不是3的文档，命令为：db.hello.find({$nor:[{name:”user2”},{age:3}]})


 查询集合中的文档 ,$exists，用于查询集合中存在某个键的文档或不存在某个键的文档，例如查询hello集合中存在name键的所有文档，可以使用
 
```
 db.hello.find({name:{$exists:1}})，
```$exists:1表示真，指存在$exists:0表示假，指不存在 查询集合中的文档 ，统计(count)、排序(sort)、分页(skip、limit)
 
```db.customer.count();db.customer.find().count();db.customer.find({age:{$lt:5}}).count();db.customer.find().sort({age:1});db.customer.find().skip(2).limit(3);db.customer.find().sort({age:-1}).skip(2).limit(3);db.customer.find().sort({age:-1}).skip(2).limit(3).count();db.customer.find().sort({age:-1}).skip(2).limit(3).count(0);db.customer.find().sort({age:-1}).skip(2).limit(3).count(1);```
 
2 查询后去掉当前聚集集合中的某列的重复数据

```
db.userInfo.distinct("name")
```
相当于： select distinct name from userInfo;

3、查询age = 22的记录```db.userInfo.find({"age": 22});```相当于： select * from userInfo where age = 22;4、查询age > 22的记录```db.userInfo.find({age: {$gt: 22}});```相当于：select * from userInfo where age >22;5、查询age < 22的记录```db.userInfo.find({age: {$lt: 22}});```相当于：select * from userInfo where age <22;6、查询age >= 25的记录```db.userInfo.find({age: {$gte: 25}});相当于：select * from userInfo where age >= 25;```7、查询age <= 25的记录```db.userInfo.find({age: {$lte: 25}});``` 8、查询age >= 23 并且 age <= 26      注意书写格式```db.userInfo.find({age: {$gte: 23, $lte: 26}});```9、查询name中包含 mongo的数据       模糊查询用于搜索```db.userInfo.find({name: /mongo/});//相当于%%select * from userInfo where name like ‘%mongo%’;``` 10、查询name中以mongo开头的```db.userInfo.find({name: /^mongo/});select * from userInfo where name like ‘mongo%’;``` 11、查询指定列name、age数据      ```db.userInfo.find({}, {name: 1, age: 1});相当于：select name, age from userInfo;```当然name也可以用true或false,当用ture的情况下河name:1效果一样，如果用false就是排除name，显示name以外的列信息。 12、查询指定列name、age数据, age > 25```db.userInfo.find({age: {$gt: 25}}, {name: 1, age: 1});相当于：select name, age from userInfo where age >25;```13、按照年龄排序    1升序    -1降序```升序：db.userInfo.find().sort({age: 1});降序：db.userInfo.find().sort({age: -1});``` 14、查询name = zhangsan, age = 22的数据```db.userInfo.find({name: 'zhangsan', age: 22});相当于：select * from userInfo where name = ‘zhangsan’ and age = ‘22’;``` 15、查询前5条数据```db.userInfo.find().limit(5);相当于：selecttop 5 * from userInfo;``` 16、查询10条以后的数据```db.userInfo.find().skip(10);相当于：select * from userInfo where id not in (selecttop 10 * from userInfo);``` 17、查询在5-10之间的数据```db.userInfo.find().limit(10).skip(5);可用于分页，limit是pageSize，skip是第几页*pageSize```18、or与 查询```db.userInfo.find({$or: [{age: 22}, {age: 25}]});相当于：select * from userInfo where age = 22 or age = 25;``` 19、查询第一条数据```db.userInfo.findOne();相当于：selecttop 1 * from userInfo;```db.userInfo.find().limit(1); 20、查询某个结果集的记录条数   统计数量```db.userInfo.find({age: {$gte: 25}}).count();相当于：select count(*) from userInfo where age >= 20;```如果要返回限制之后的记录数量，要使用count(true)或者count(非0) ```
db.users.find().skip(10).limit(5).count(true);```21、按照某列进行排序```db.userInfo.find({sex: {$exists: true}}).count();相当于：select count(sex) from userInfo;```4.3 修改数据修改里面还有查询条件。你要该谁，要告诉mongo。查找名字叫做小明的，把年龄更改为16岁：```
db.student.update({"name":"小明"},{$set:{"age":16}});```查找数学成绩是70，把年龄更改为33岁：```
db.student.update({"score.shuxue":70},{$set:{"age":33}});```更改所有匹配项目：
By default, the update() method updates a single document. To update multiple documents, use the multi option in the update() method. ```db.student.update({"sex":"男"},{$set:{"age":33}},{multi: true});完整替换，不出现$set关键字了：  注意db.student.update({"name":"小明"},{"name":"大明","age":16});db.users.update({name: 'Lisi'}, {$inc: {age: 50}}, false, true);相当于：update users set age = age + 50 where name = ‘Lisi’; db.users.update({name: 'Lisi'}, {$inc: {age: 50}, $set: {name: 'hoho'}}, false, true);相当于：update users set age = age + 50, name = ‘hoho’ where name = ‘Lisi’;

```



<!--
create time: 2018-06-17 16:15:14
Author: Alfred

This file is created by Marboo<http://marboo.io> template file $MARBOO_HOME/.media/starts/default.md
本文件由 Marboo<http://marboo.io> 模板文件 $MARBOO_HOME/.media/starts/default.md 创建
-->

