###### 1、Flink 分为四个组件，其中三个manager和一个dispatcher
        
    Dispatcher: 提供webUI 和 REST 接口，让我们提交任务和了解作业的信息。
    JobManager: 接收到作业后，包含了JobGraph, 转化为ExecutionGraph，然后去sourceManager 申请
                任务槽slot
    sourceManager: 任务槽管理员，所有的taskManager生成的slot都要向它注册，然后统一管理，提供给
                    jobManager使用。
    taskManager: 生产任务槽，实际任务执行器。

###### 2、taskManager 任务处理槽和各个算子间的关系。
