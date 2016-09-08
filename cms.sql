--管理员信息
drop table admin;
create table admin(
	aid int primary key,  --管理员编号
	aname varchar2(20) unique not null,--管理员姓名
	apwd varchar2(20) default 'aaaaaa' --管理员密码
);
create sequence aid_seq increment by 1 start with 1001;
insert into admin values(aid_seq.nextval,'a','A909B54DB2198F11');
insert into admin values(aid_seq.nextval,'awf1','A909B54DB2198F11');

insert into admin values(aid_seq.nextval,'aw1','A909B54DB2198F11');

insert into admin values(aid_seq.nextval,'aahfh1','A909B54DB2198F11');

update admin set apwd='A909B54DB2198F11' where aname='a';
commit;
select * from admin;
--创建用户表
drop table users;
create table users(
       usid int primary key,  --用户编号
       uname varchar2(20 char) unique,  --用户名
       upwd varchar(20 char),  --用户密码
	   email varchar2(20 char) not null unique --邮箱
);

insert into users values(1,'lydia','aaaaaa','qing.yt@163.com')
 

--主题表fbu 
create table topic(
       tid int primary key,  --主题编号
       tname varchar2(20 char) unique    --主题名称  
);

--新闻表
create table news(
       nid int primary key,  --新闻编号
       ntid int
           constraint fk_news_topic references topic(tid),  --主题类型
       ntitle varchar2(200 char) not null,  --新闻标题
       nauthor varchar2(50 char) not null,   --作者
       ncreatedate date,  --发布日期
       npicpath varchar2(1000 char),  --图片地址
       ncontent varchar2(4000 char) not null,  --新闻内容
       nmodifydate date,   --修改日期
       nsummary varchar2(500 char)  --概要
);


--主外键
--alter table news add constraint fk_news_topic  foreign key(ntid) references topic(tid);

--评论表
create table comments(
       cid int primary key,  --评论编号
       cnid int not null
			constraint fk_comments_news references news(nid), --新闻编号
       ccontent varchar2(4000 char),  --评论内容
       cdate date,  --评论日期
       cip varchar2(100 char),  --评论者ip
       cauthor varchar2(100 char)  --评论者
);
select * from commonts;
--创建序列
create sequence cid_seq increment by 1 start with 1;
create sequence nid_seq increment by 1 start with 1;
create sequence usid_seq increment by 1 start with 1;
create sequence tid_seq increment by 1 start with 1;
--create sequence aid_seq increment by 1 start with 1001;



--comments _ news 的主外键
--alter table comments add constraint fk_comments_news  foreign key(cnid) references news(nid);
      
--创建索引

--用户名唯一
create unique index uname_index  on news_users(uname);
       
--类型唯一
create unique index tname_index ON topic(tname);

--新闻标题建立普通索引
create unique index ntitle_index on news(ntitle);

 select * from users;
 select * from topic;
 select * from news;
 select * from commonts;
insert into topic values ('1','国内');
insert into topic values ('2','国际');
insert into topic values ('3','娱乐');
insert into topic values ('4','新闻');
insert into topic values ('5','军事');
insert into topic values ('6','教育');
insert into topic values ('7','体育');
insert into topic values ('8','社会');
insert into topic values ('9','财经');
insert into topic values ('10','科技');
insert into topic values ('11','健康');
insert into topic values ('12','汽车');
insert into topic values ('13','教育');
insert into topic values ('14','房产');
insert into topic values ('15','家居');
insert into topic values ('16','旅游');
insert into topic values ('17','文化');
insert into topic values ('18','其他');
commit;     

select * from topic where tid=(select max(tid) from topic)

select n.*,tname from news n,topic t where tid=ntid and nid=1

select count(*) from news where ntid=1

select * from (select n.*,tname,rownum rn from news n,topic t where tid=ntid and rownum<10 order by nid asc) where rn>5

select * from news;

update news set npicpath='../dataInfo/pic/20140726/1406338899637768.jpg' where nid=3

update news set ntitle='中国骑兵' where nid=12
--创建视图
 create or replace view news_view as select n.nid,n.ntitle,n.nauthor,to_char(n.ncreatedate,'yyyy-MM-dd HH24:mi:ss') ncreatedate,
	n.npicpath,n.ncontent,to_char(n.nmodifydate,'yyyy-MM-dd HH24:mi:ss') nmodifydate,
	n.nsummary,t.tid,t.tname
from news n inner join topic t on n.ntid =t.tid;
select * from news_view order by nmodifydate desc
select * from news_view;
--分页
select * from (select a.*,rownum rn from 
(select * from news_view order by nmodifydate desc,ncreatedate desc)a where rownum<=10) b where rn>5



insert into news values(nid_seq.nextval,1,'重庆沙黑富豪李强夫妇庭审','张三',sysdate,'upload/1.gif','重庆沙黑富豪李强夫妇庭审。。。。。内容',null,'重庆沙黑富豪李强夫妇庭审。。。概要');
insert into news values(nid_seq.nextval,1,'答辩言辞相互矛盾','张三',sysdate,'upload/1.gif','答辩言辞相互矛盾。。。。。内容',null,'答辩言辞相互矛盾。。。概要');
insert into news values(nid_seq.nextval,1,'发改案：4万亿投资计划','张三',sysdate,'upload/1.gif','发改案：4万亿投资计划。。。。。内容',null,'发改案：4万亿投资计划。。。概要');
insert into news values(nid_seq.nextval,1,'会挤占民间投资空间','张三',sysdate,'upload/1.gif','会挤占民间投资空间。。。。。内容',null,'会挤占民间投资空间。。。概要');
insert into news values(nid_seq.nextval,1,'河南两个乡镇真伪报告内容','张三',sysdate,'upload/1.gif','河南两个乡镇真伪报告内容。。。。。内容',null,'河南两个乡镇真伪报告内容。。。概要');
insert into news values(nid_seq.nextval,1,'完全一致引关注','张三',sysdate,'upload/1.gif','完全一致引关注。。。。。内容',null,'完全一致引关注。。。概要');
insert into news values(nid_seq.nextval,1,'答辩言辞相互矛盾','张三',sysdate,'upload/1.gif','答辩言辞相互矛盾。。。。。内容',null,'答辩言辞相互矛盾。。。概要');
insert into news values(nid_seq.nextval,1,'发改案：4万亿投资计划','张三',sysdate,'upload/1.gif','发改案：4万亿投资计划。。。。。内容',null,'发改案：4万亿投资计划。。。概要');
insert into news values(nid_seq.nextval,1,'会挤占民间投资空间','张三',sysdate,'upload/1.gif','会挤占民间投资空间。。。。。内容',null,'会挤占民间投资空间。。。概要');
insert into news values(nid_seq.nextval,1,'河南两个乡镇真伪报告内容','张三',sysdate,'upload/1.gif','河南两个乡镇真伪报告内容。。。。。内容',null,'河南两个乡镇真伪报告内容。。。概要');
insert into news values(nid_seq.nextval,1,'完全一致引关注','张三',sysdate,'upload/1.gif','完全一致引关注。。。。。内容',null,'完全一致引关注。。。概要');
 
insert into news values(nid_seq.nextval,2,'气候变化导致海平面上升','张三',sysdate,'upload/1.gif','气候变化导致海平面上升。。。。。内容',null,'气候变化导致海平面上升。。。概要');
insert into news values(nid_seq.nextval,2,'商贸联委会在杭州开会 中国美对贸易争端态度低调','张三',sysdate,'upload/1.gif','商贸联委会在杭州开会 中国美对贸易争端态度低调。。。。。内容',null,'商贸联委会在杭州开会 中国美对贸易争端态度低调。。。概要');
insert into news values(nid_seq.nextval,2,'伊朗称放弃美元作为外蓄地位 人民币或上位','张三',sysdate,'upload/1.gif','伊朗称放弃美元作为外蓄地位 人民币或上位。。。。。内容',null,'伊朗称放弃美元作为外蓄地位 人民币或上位。。。概要');
insert into news values(nid_seq.nextval,2,'德媒：武装分子离开大马士革是普京的功劳','张三',sysdate,'upload/1.gif','德媒：武装分子离开大马士革是普京的功劳。。。。。内容',null,'德媒：武装分子离开大马士革是普京的功劳。。。概要');
insert into news values(nid_seq.nextval,2,'印度总理近12年来首访巴基斯坦 中方表态欢迎','张三',sysdate,'upload/1.gif','印度总理近12年来首访巴基斯坦 中方表态欢迎。。。。。内容',null,'印度总理近12年来首访巴基斯坦 中方表态欢迎。。。概要');
insert into news values(nid_seq.nextval,2,'外媒:IS支持活摘人体器官 称不必尊重叛教者生命','张三',sysdate,'upload/1.gif','外媒:IS支持活摘人体器官 称不必尊重叛教者生命。。。。。内容',null,'外媒:IS支持活摘人体器官 称不必尊重叛教者生命。。。概要');
insert into news values(nid_seq.nextval,2,'普京任命格雷兹洛夫为乌克兰问题联络组代表','张三',sysdate,'upload/1.gif','普京任命格雷兹洛夫为乌克兰问题联络组代表。。。。。内容',null,'普京任命格雷兹洛夫为乌克兰问题联络组代表。。。概要');
insert into news values(nid_seq.nextval,2,'日媒：中国“转向欧洲”很可能成功','张三',sysdate,'upload/1.gif','日媒：中国“转向欧洲”很可能成功。。。。。内容',null,'日媒：中国“转向欧洲”很可能成功。。。概要');
insert into news values(nid_seq.nextval,2,'泰媒：MPV或借二孩政策大卖','张三',sysdate,'upload/1.gif','泰媒：MPV或借二孩政策大卖。。。。。内容',null,'泰媒：MPV或借二孩政策大卖。。。概要');
insert into news values(nid_seq.nextval,2,'新媒：什么是中国的头号大敌？','张三',sysdate,'upload/1.gif','新媒：什么是中国的头号大敌？。。。。内容',null,'新媒：什么是中国的头号大敌？。。。概要');
insert into news values(nid_seq.nextval,2,'日媒：安倍政府上台满三年 在野党指责称“日本变','张三',sysdate,'upload/1.gif','日媒：安倍政府上台满三年 在野党指责称“日本变。。。。。内容',null,'日媒：安倍政府上台满三年 在野党指责称“日本变。。。概要');



select * from (select a.*,rownum rn from 
					(select * from news_view  where tid =1 
					order by nmodifydate desc,ncreatedate desc)a 
					where rownum<=16) b where rn>8;


commit;
select * from (select a.*,rownum rn from (select * from news_view where tid=1 order by nmodifydate desc,ncreatedate desc)a where rownum<=4) b where rn>0








