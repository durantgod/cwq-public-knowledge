## dataflow 数据流动，有向无环图，也称为逻辑图
    flink 的dataflow大概为：source --->> transformate ->> sink

    具体表现为：
    
    source ---> map/flatMap ---> keyBy/window()/apply() ----> sink

    source 可以自定义，需要实现SourceFunction, apply也可以自定义，需要实现AllWindowFunction
