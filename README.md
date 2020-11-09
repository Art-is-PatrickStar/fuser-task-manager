# fuser-task-manager

协助开发，提高开发效率

分布式微服务系统架构

本项目参考个人学习仓库: https://github.com/WswSummer15/myspringcloud2020
## Java版本
```text
Java1.8
```

## 框架版本
```text
<spring.boot.version>2.2.2.RELEASE</spring.boot.version>
<spring.cloud.version>Hoxton.SR1</spring.cloud.version>
<spring.cloud.alibaba.version>2.1.0.RELEASE</spring.cloud.alibaba.version>
```

## SpringCloud组件选择
```text
1. 注册中心 Nacos
2. 配置中心 Nacos
3. 服务调用 OpenFeign
4. 负载均衡 Ribbon
5. 服务限流降级 Sentinel
6. 网关 Gateway
7. 服务总线 SpringCloud Bus
```

## 程序运行
### 本地环境
```text
最新版本即可
1. Nacos
cd bin -> .\startup.cmd

2. Sentinel(jar包)
自定义端口启动 -> java -jar .\sentinel-dashboard-1.8.0.jar --server.port=9090

3. RabbitMQ
省略
```

### 使用Nacos作为配置中心管理配置文件
配置文件格式: {微服务名称}-{环境}.{文件格式}
例如: manager-task-service-local.yaml
```yaml
spring:
    rabbitmq:
        host: 127.0.0.1
        port: 5672
        username: guest
        password: guest
        # 开启消息确认机制
        publisher-confirms: true
        # 开启发送失败退回
        publisher-returns:  true
        # 虚拟主机(一个RabbitMQ服务可以配置多个虚拟主机，每一个虚拟机主机之间是相互隔离，相互独立的，授权用户到指定的virtual-host就可以发送消息到指定队列
        virtual-host: /
        # 保证监听有效
        template:
            mandatory: true
        listener:  
            simple:
                # 消费者的ack方式为手动 auto自动 none不会发送ACK（与channelTransacted=true不兼容）
                acknowledge-mode: manual
                # 最小消费者数量
                concurrency: 1
                # 最大消费者数量
                max-concurrency: 10
                # 支持重试/重发
                retry:
                    enabled: true
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/rabbitmq?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
        username: root
        password: root

mybatis:
    mapper-locations: classpath:mapper/*.xml

management:
    endpoints:
        web:
            exposure:
                include: '*'
```