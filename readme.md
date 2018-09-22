## 说明
目前已经完成了表结构的调整，代码重构的任务。redis使用，shiro这个ssm版本不做实现。
后面的功能开发等都转移到springboot版本的项目开发上。
后面这个项目应该就不继续更新开发了。

#### 关于用户登录及权限问题，可以选用权限框架（各种操作的权限设置）；现在都不做实现
#### aop的功能，后面补充进去

1. 自定义管理员administrator账号
2. 管理员administrator账户登陆后，添加几个服务员waiter账号
3. 几个用户user进行注册

3.5. 用户user登录后，查看“招领”信息大厅 -> lost_release_record（dto->复合对象：lost_release_record与lost_cache_record表的复合对象，同时也是关联查询）
4. 几个用户user提交“招领”信息 -> lost_cache_record : status=0(未处理)
5. 几个用户user查看自己提交的“招领”信息及状态 -> lost_cache_record：不论status

6. 服务员waiter查看lost_cache_record记录，并进行审核、留存记录 -> lost_cache_record更改status，lost_cache_check留存记录,驳回则stop，通过则lost_release_record留存记录
7. 步骤5重复，这时状态发生变化（查看自己提交的“招领”信息，状态发生了变化）
8. 步骤3.5重复，这时数据发生变化（查看“招领大厅，应该发生了变化”）
9. 线下物主上门确认，失物归还 -> lost_release_record删除记录（软删），lost_cache_record修改status，lost_cache_record修改状态，lost_release_update留存记录

9.5. 用户被驳回的“招领”信息不可再编辑，成为历史记录，仅可查看
10. 用户申请主动下架（仅可申请下架正式发布的招领信息），服务员在正式记录的下架申请中统一操作。lost_cache_record修改，lost_release_record软删除，lost_release_update留存记录



> 可以思考的功能
1. 全文搜索，分类，
2. redis搜索排行榜、最新上架

> 工程总结
1. 所有的javabean都实现Serializable接口，添加显式id
2. 所有的javabean的日期都使用注解@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  注意date和timestamp的pattern不同
2.5.对于不想返回给客户端显示的属性值，使用@JsonIgnore来忽略
3.对于值为null的属性值，可以使用@JsonInclude(Include.NON_NULL)来让其不返回客户端
3. mapper不加exception，service要加
4. controller、service、mapper之间的分层关系
5. service中的事务处理
6. mybatis中，mapper返回对象时->没有数据返回的是null；没有数组时，返回的是size=0的数组

> redis
1. @Cacheable和@CachePut注解的方法，是把方法的返回值作为value来缓存，尤其要注意返回值
2. @CacheEvict则不关心返回值，它只是用来移除key对应的数据