1、查看mysql设置，慢SQL设置
    
### 查看是否开启慢查询 slow_query_log
    show variables like '%slow%'; 
    // 设置开启慢查询
    set global slow_query_log=ON;

### 查看慢查询设置的阈值 long_query_time
    show variables like '%long_query%';
    // 设置慢查询的时间
    set long_query_time = 0.1;

### MySQL中控制内存分配的全局参数
    keyBuffer: 索引缓存池
    innodbbufferpool: 索引池
    querycachesize: 查询缓存池

#### 优化之路（一般优化需要分析，有默认值，但是也可以通过，软件，硬件，第三方检测这三方面综合得出优化方案！）
    通过命令行: show status like '%read%'; 通过分析key_read_request（索引读的请求数） 和 key_reads（索引读响应数）; 来决定keybuffer 设置大小

#### 事务隔离级别
    1、读已提交 处于Read committed （读已提交）级别的事务可以看到其他事务对数据的修改。也就是说，在事务处理期间，如果其他事务修改了相应的表，那么同一个事务的同一sql在其他事务执行前后返回的是不同的结果。一个事务要等另一个事务提交后才能读取数据。
    2、读未提交 提供了事务建最小限度的隔离。顾名思义，就是一个事务可以读取另一个未提交事务的数据。
    3、脏读
    4、幻读
    5、重复读 在开始读取数据（事务开启）时，不再允许修改操作
    由低到高分别为Read uncommitted（读未提交） 、Read committed （读已提交）、Repeatable read （重复读）、Serializable （序列化）。读现象是在多个事务并发执行时，在读取数据方面可能碰到的问题。包括脏读、不可重复读、幻读。
    

### 问题汇总
    1、说说mysql存储引擎innodb和myisam的区别和使⽤场景
    mysql主要有两种常用的存储方式myisam（没有事务，没有外键，只有表锁，优点：主要是性能，读取快，且存有行数）
    和 innodb(默认且支持事务，支持锁粒度到行锁，缺点是：没有存储行数，不支持全文搜索)

    2、InnoDB主要分为两大类索引，聚集索引和非聚集索引，InnoDB有且必须只有一个聚簇索引，因为叶子节点存的是行记录。
       非聚簇索又叫普通索引/二级索引等，叶子节点为主键索引。回表情况：使用了非聚簇索引，但是上面没有需要查询的数据
       从而又要从聚簇索引中查找记录字段。从两个索引中查找数据。

       聚簇索引：主键 > 第一个非空唯一索引 > 隐藏rowID(自动创建)

    3、如何进行优化？
        EXPLAIN select id from t_user where id = 1 limit 1;

       INSERT INTO ``(`id`, `select_type`, `table`, `partitions`, `type`, `possible_keys`, `key`, `key_len`, `ref`, `rows`, `filtered`, `Extra`) VALUES 
                      (1, 'SIMPLE', 't_user', NULL, 'const', 'PRIMARY', 'PRIMARY', '4', 'const', 1, 100.00, 'Using index');
        每个字段的解释：
        id：每个查询组，组id越大越先执行，组id一样，则从上到下执行。
        
        select_type: 查询中每个select子句的类型
            simple:不包含子句或者union  
            PRIMARY： 如果包含复杂子查询，最外层标识
            SUBQUERY：如果select,where 中包含子查询
            DERIVED：from 子查询

        type：在表中找到所需行的方式，称为“访问类型”
              ALL（全表）, index（索引树）,  range（范围）, ref（使用非唯一索引扫描或者唯一索引的前缀扫描）, eq_ref, const, system（当MySQL对查询某部分进行优化，并转换为一个常量时，使用这些类型访问。）, NULL（MySQL在优化过程中分解语句，执行时甚至不用访问表或索引）
            
        possible_keys：不一定使用到的索引

        key：实际使用的索引

        ref：标识上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值

        rows：估算查找到咧所需要读取的记录数

        Extra： 包含不适合在其他列中显示但十分重要的额外信息
            Using filesort: order by 影响性能
            Using index：该值表示相应的select操作中使用了覆盖索引（Covering Index）
            Using where 单独出现 表示当前查询的字段不能被索引覆盖，所以可能会产生回表，效率比前者低
            null 表示查询的列未被索引覆盖，且where筛选条件是索引的前导列，这意味着用到了索引，但是部分字段未被索引覆盖，必须通过“回表查询”来实现，因而性能也比前两者差。 下面例子b
            Using index condition 表示查询条件中虽然出现了索引列，但是有部分条件无法使用索引，会根据能用索引的条件先搜索一遍再匹配无法使用索引的条件。 下面例子c

        具体例子分析：
         CREATE TABLE `t_user2` (
            `id` INT ( 10 ) NOT NULL AUTO_INCREMENT,
            `name` VARCHAR ( 255 ) DEFAULT NULL,
            `age` INT ( 10 ) DEFAULT NULL,
            PRIMARY KEY ( `id` ),
            KEY `nameIndex` ( `name` ) USING BTREE
        ) ENGINE = INNODB AUTO_INCREMENT = 12 DEFAULT CHARSET = utf8;


        a：   EXPLAIN select id, name, age from t_user2 where id = 9;
        b:    EXPLAIN select id, name, age from t_user2 where name = '3324';
        

        c:    EXPLAIN select id, name from t_user2 where name = '3324';
        d:    EXPLAIN select id, name, age from t_user2 where name = '3324';
        
        
        1	SIMPLE	t_user2		const	PRIMARY	PRIMARY	4	const	1	100.00
        1	SIMPLE	t_user2		ref	nameIndex	nameIndex	768	const	1	100.00
        1	SIMPLE	t_user2		ref	nameIndex	nameIndex	768	const	1	100.00	Using index
        1	SIMPLE	t_user2		ref	nameIndex	nameIndex	768	const	1	100.00	
        
        总结：先看key，row搜索的过程和性能，extra看

    4、 
    
    
