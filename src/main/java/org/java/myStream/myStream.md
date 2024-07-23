#### stream 原理
    1、BaseStream  -->  Stream/LongStream/IntStream, BaseStream 接口继承AutoClose接口
       这样设计能兼容Long/IntStream之间的差别，比如mapToInt等聚合操作，可以屏蔽差异。

