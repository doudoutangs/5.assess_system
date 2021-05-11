# 绩效考核系统

## 一、系统介绍
本系统为绩效考核系统，系统分为三大模块：考核设置，绩效考核，系统管理。
可满足小企业对员工进行考核。本系统最大特色是有强大和灵活的权限控制功能，所有菜单，按钮功能均可由管理通过配置来控制。

系统默认有三个角色：管理员，领导，普通用户
- 管理员（admin/admin）：可以操作所有功能
- 领导(lsk/lsk)：添加考核项，设置考核等内容
- 普通用户（wdc/wdc）：可进行考核申报等
## 二、角色运行图
### 管理员
![管理员](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/r-1-%E7%AE%A1%E7%90%86%E5%91%98.png)
### 领导
![领导](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/r-2-%E9%A2%86%E5%AF%BC.png)
### 普通用户
![普通用户](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/r-3%E6%99%AE%E9%80%9A%E5%91%98%E5%B7%A5.png)

## 三、所有功能介绍
### 0.登录
- 登录地址：http://localhost:8885/
- 账号密码：admin/admin

![登录](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/0-1-%E7%99%BB%E5%BD%95.png)
![首页](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/0-2-%E9%A6%96%E9%A1%B5.png)
![修改密码](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/0-3-%E4%BF%AE%E6%94%B9%E5%AF%86%E7%A0%81.png)

### 1.考核设置
考核设置由考核管理，考核项管理，考核名单三个子模块组成，主要对考核信息进行动态管理
#### （1）考核管理
根据公司的需求可每月或每季度进行周期性考核，也可根据公司实际情况自行添加非周期性考核。
![考核管理-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/1-%E8%80%83%E6%A0%B8%E9%85%8D%E7%BD%AE-%E8%80%83%E6%A0%B8%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![考核管理-增加](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/1-%E8%80%83%E6%A0%B8%E9%85%8D%E7%BD%AE-%E8%80%83%E6%A0%B8%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

#### （2）考核项管理
将考核的常用考核项动态管理起来，方便每次添加考核
![考核项管理-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/2-%E8%80%83%E6%A0%B8%E9%85%8D%E7%BD%AE-%E8%80%83%E6%A0%B8%E9%A1%B9%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![考核项管理-增加](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/2-%E8%80%83%E6%A0%B8%E9%85%8D%E7%BD%AE-%E8%80%83%E6%A0%B8%E9%A1%B9%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

#### （3）考核名单
添加考核是通常以部门为单位进行考核，再次确定当前考核哪些部门参加
![考核名单-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/3-%E8%80%83%E6%A0%B8%E9%85%8D%E7%BD%AE-%E8%80%83%E6%A0%B8%E5%90%8D%E5%8D%95-%E5%88%97%E8%A1%A8.png)
![考核名单-增加](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/3-%E8%80%83%E6%A0%B8%E9%85%8D%E7%BD%AE-%E8%80%83%E6%A0%B8%E5%90%8D%E5%8D%95-%E5%A2%9E%E5%8A%A0.png)

### 3.绩效考核
绩效考核由员工考核，考核审批，考核统计三个模块组成，主要作用是员工申报考核，领导审批考核
#### （1）员工考核
员工自行申报考核信息
![员工考核-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/4-%E7%BB%A9%E6%95%88%E8%80%83%E6%A0%B8-%E5%91%98%E5%B7%A5%E8%80%83%E6%A0%B8-%E5%88%97%E8%A1%A8.png)
![员工考核-申报](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/4-%E7%BB%A9%E6%95%88%E8%80%83%E6%A0%B8-%E5%91%98%E5%B7%A5%E8%80%83%E6%A0%B8-%E7%94%B3%E6%8A%A5.png)

#### （2）考核审批
领导对员工申报的考核进行审批
![考核审批-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/5-%E7%BB%A9%E6%95%88%E8%80%83%E6%A0%B8-%E8%80%83%E6%A0%B8%E5%AE%A1%E6%89%B9-%E5%88%97%E8%A1%A8.png)
![考核审批-审批](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/5-%E7%BB%A9%E6%95%88%E8%80%83%E6%A0%B8-%E8%80%83%E6%A0%B8%E5%AE%A1%E6%89%B9-%E5%AE%A1%E6%89%B9.png)
#### （3）考核统计
根据部门和考核主题统计每个部门的考核平均分
![考核统计](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/6-%E7%BB%A9%E6%95%88%E8%80%83%E6%A0%B8-%E8%80%83%E6%A0%B8%E7%BB%9F%E8%AE%A1.png)


### 3.系统管理
系统管理子模块:员工管理，部门管理，角色管理，菜单管理。
#### （1）账号管理
管理员对系统登录账号进行管理，可以为新员工申请登录账号，为离职员工删除账号
![员工管理-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E5%91%98%E5%B7%A5%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![员工管理-增加](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E5%91%98%E5%B7%A5%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

#### （2）部门管理
可增加新部门，通常只有管理员和高级管理领导可用
![部门管理-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E9%83%A8%E9%97%A8%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![部门管理-增加](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E9%83%A8%E9%97%A8%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

#### （3）角色管理
可新增角色，并为角色赋予相应权限，通常只有管理员和高级管理领导可用
![角色管理-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![角色管理-增加](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

#### （4）菜单管理
管理系统左侧的菜单树，只有管理员可用
![菜单管理-列表](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E8%8F%9C%E5%8D%95%E7%AE%A1%E7%90%86-%E5%88%97%E8%A1%A8.png)
![菜单管理-增加](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/7-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86-%E8%8F%9C%E5%8D%95%E7%AE%A1%E7%90%86-%E5%A2%9E%E5%8A%A0.png)

## 四、软件架构

基础环境：
1. JDK:1.8
2. MySQL:5.7
3. Maven3.0

使用框架：

1. 核心框架：Spring Boot 2.1.8.RELEASE
2. 视图框架：Spring MVC 5.0
3. ORM框架：MyBatisPlus 3.1.2
4. 数据库连接池：Druid 1.1
5. 安全框架：Apache Shiro 1.4
6. 日志：SLF4J 1.7、Log4j
7. 前端框架：Layui,ztree,jquery,bootstrap



## 五、安装教程
1. 导入mysql脚本,数据库名称：assess_system
2. 修改数据库配置：

![修改数据](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/0-99-%E9%85%8D%E7%BD%AE.png)
3. 启动java工程（执行assess_system工程com.assess.Application.class中main方法）

![启动项目](https://gitee.com/doudoutang/system-diagram/raw/master/%E7%BB%A9%E6%95%88%E7%B3%BB%E7%BB%9F/0-99-%E5%90%AF%E5%8A%A8.png)
4. 访问：http://localhost:8885（账号admin/admin）

## 六、特别说明
本项目可做公司内网使用，也可做自学练习亦可作毕业设计。完整系统源代码以及指导有偿提供，也可付费咨询其他项目，如不愿意付费的勿扰。
如意愿付费请[加QQ详谈，QQ:553039957](http://)

## 七、提醒
最近有同学反映有人在淘宝，B站等渠道贩卖我的源代码，本人在此郑重声明，目前只有唯一的购买咨询方式就是加我QQ:553039957.
其他渠道都是非法的，在非法渠道您可能花了钱买到的不是完整系统，请各位真心喜欢本项目的朋友不要上当受骗，请走唯一正规渠道，我只对这唯一渠道的服务负责。
## 八、其他项目
1. [人事管理系统](https://gitee.com/doudoutang/person_system)
2. [薪资管理系统](https://gitee.com/doudoutang/salary_system)
3. [OA系统](https://gitee.com/doudoutang/bankOA)
4. [招投标管理系统](https://gitee.com/doudoutang/bid-system)
5. [绩效考核系统](https://gitee.com/doudoutang/assess_system)

