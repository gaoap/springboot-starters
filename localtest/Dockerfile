FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

################################

FROM adoptopenjdk:11-jre-hotspot
MAINTAINER gaoyd <gaoyd@gaoap.com>
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

# JVM_XMS and JVM_XMX configs deprecated for removal in halov1.4.4
ENV JVM_XMS="256m" \
    JVM_XMX="256m" \
    JVM_OPTS="-Xmx256m -Xms256m" \
    TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

ENTRYPOINT java -Xms${JVM_XMS} -Xmx${JVM_XMX} ${JVM_OPTS} -Djava.security.egd=file:/dev/./urandom org.springframework.boot.loader.JarLauncher


#项目 pom.xml 中引入 spring-boot-maven-plugin 打包插件，并且开启分层功能：似乎新版本不用配置开启了。例如：2.5.5
#
#<build>
#    <plugins>
#        <plugin>
#            <groupId>org.springframework.boot</groupId>
#            <artifactId>spring-boot-maven-plugin</artifactId>
#            <configuration>
#                <!--开启分层编译支持-->
#                <layers>
#                    <enabled>true</enabled>
#                </layers>
#            </configuration>
#        </plugin>
#    </plugins>
#</build>
#根据官方介绍，在构建 Docker 镜像前需要从 Jar 中提起出对应的分层文件到 Jar 外面，可用使用下面命令列出可以从分层 Jar 中提取出的文件夹信息：
#
# 查看目录$ java -Djarmode=layertools -jar target/springboot-layer-0.0.1.jar list
# 解压.jar$ java -Djarmode=layertools -jar target/springboot-layer-0.0.1.jar extract
#可用该看到以下输出，下面的内容就是接下来使用分层构建后，生成的 Jar 提取出对应资源后的结构：
#
#dependencies
#spring-boot-loader
#snapshot-dependencies
#application
#上面即是使用分层工具提取 Jar 的内容后生成的文件夹，其中各个文件夹作用是：
#
#dependencies： 存储项目正常依赖 Jar 的文件夹。
#
#snapshot-dependencies： 存储项目快照依赖 Jar 的文件夹。
#
#resources： 用于存储静态资源的文件夹。
#
#application： 用于存储应用程序类相关文件的文件夹。