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
8. 缓存中间件 Redis
9. 消息中间件 RabbitMQ
```
## 项目结构
```text
manager-gateway-service -> 1998
manager-auth-service -> 2000
manager-task-service -> 2001
manager-mq-service -> 2002
manager-recepienter-service -> 2003
manager-tester-service -> 2004
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
4. Redis
省略
```

### 使用Nacos作为配置中心管理配置文件
配置文件格式: {微服务名称}-{环境}.{文件格式}
例如: manager-task-service-local.yaml

#### 1. 存储公共配置的配置文件manager-dev.yaml

```yaml
jwt:
    secretKey: 1234567890-1234567890-1234567890
```

#### 2. 配置文件manager-task-service-dev.yaml

```yaml
server:
    port: 2001

spring:
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource
        url: jdbc:mysql://39.107.80.231:3306/task-system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
        username: HengTian
        password: ***

    redis:
        host: 39.107.80.231
        port: 6379

        lettuce:
            pool:
                # 连接池最大连接数(使用负值表示没有限制) 默认为8
                max-active: 8
                # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1
                max-wait: -1ms
                # 连接池中的最大空闲连接 默认为8
                max-idle: 8
                # 连接池中的最小空闲连接 默认为 0
                min-idle: 0
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

mybatis:
    mapper-locations: classpath:mapper/*.xml
    type-aliases-package: com.wsw.fusertaskmanager.domain

management:
    endpoints:
        web:
            exposure:
                include: '*' 
```

#### 3. 配置文件manager-auth-service-dev.yaml

```yaml
server:
    port: 2000

spring:
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource
        url: jdbc:mysql://39.107.80.231:3306/task-system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
        username: HengTian
        password: ***

mybatis:
    mapper-locations: classpath:mapper/*.xml
    type-aliases-package: com.wsw.fusertaskmanager.domain

management:
    endpoints:
        web:
            exposure:
                include: '*'
```

#### 4. 配置文件manager-tester-service-dev.yaml

```yaml
server:
    port: 2004

spring:
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource
        url: jdbc:mysql://39.107.80.231:3306/task-system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
        username: HengTian
        password: ***
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

mybatis:
    mapper-locations: classpath:mapper/*.xml
    type-aliases-package: com.wsw.fusertaskmanager.domain

management:
    endpoints:
        web:
            exposure:
                include: '*'
```

#### 5. 配置文件manager-recepienter-service-dev.yaml

```yaml
server:
    port: 2003

spring:
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource
        url: jdbc:mysql://39.107.80.231:3306/task-system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
        username: HengTian
        password: ***
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

mybatis:
    mapper-locations: classpath:mapper/*.xml
    type-aliases-package: com.wsw.fusertaskmanager.domain

management:
    endpoints:
        web:
            exposure:
                include: '*'
```

配置文件在Nacos配置中心配置好后即可依次启动服务。

### 访问服务
通过gateway网关暴露服务,通过网关访问具体服务即可,比如访问manager-task-service微服务:
```text
http://localhost:1998/manager-task-service/task/***
```