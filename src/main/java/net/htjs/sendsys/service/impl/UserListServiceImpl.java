package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.Msg;
import net.htjs.sendsys.mongo.UserInfo;
import net.htjs.sendsys.mongo.UserTimes;
import net.htjs.sendsys.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

/**
 * Description:
 * author  dyenigma
 * date 2016/9/18 16:30
 */
@Service("userlistService")
public class UserListServiceImpl implements UserListService {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    private static final int PAGE_SIZE=10;
    /**
     * 根据时间区间选择在线用户，如果结束时间不选择，则默认为当前时间
     *
     * @param project
     * @param starttime
     * @param endtime
     * @return
     */
    @Override
    public Map getUserListByTime(String project, String starttime, String endtime,int currentpage) {
        Aggregation agg = newAggregation(match(Criteria.where("project").is(project).and("createTime").gte(starttime)
                        .lte(endtime)), group("userId", "project").count().as("times"), sort(Sort.Direction.DESC, "times"));
        AggregationResults<UserTimes> groupResults
                = mongoEventsTemplate.aggregate(agg, UserInfo.class, UserTimes.class);
        List<UserTimes> result = groupResults.getMappedResults();
        List<UserInfo> userlist = new ArrayList<UserInfo>();
        for (UserTimes u : result) {
            UserInfo userinfo = mongoEventsTemplate.findOne(new Query(Criteria.where("project").is(u.getProject())
                    .and("userId").is(u.getUserId()))
                    .with(new Sort(Sort.Direction.DESC, "createTime")), UserInfo.class);
            userlist.add(userinfo);
        }
        Collections.sort(userlist, new Comparator<UserInfo>() {
            public int compare(UserInfo arg0, UserInfo arg1) {
                return arg1.getCreateTime().compareTo(arg0.getCreateTime());
            }
        });
        if(userlist.size()<=currentpage*PAGE_SIZE){
            List<UserInfo> ulist = new ArrayList<UserInfo>();
            for(int j=(currentpage-1)*PAGE_SIZE;j<userlist.size();j++){
                    ulist.add(userlist.get(j));
            }
            Map map = new HashMap<>();
            map.put("list",ulist);
            map.put("totalpages",userlist.size());
            return map;
        }else{
            Map map = new HashMap<>();
            map.put("list",userlist.subList((currentpage-1)*PAGE_SIZE,currentpage*PAGE_SIZE));
            map.put("totalpages",userlist.size());
            return map;
        }
    }

    /**
     * 查询一段时间内发送过的消息记录
     *
     * @param project
     * @param starttime
     * @param endtime
     * @return
     */
    @Override
    public Map getUserListByMsg(String project, String starttime, String endtime, int currentpage) {
        //总条数
        long total=mongoEventsTemplate.count(new Query(Criteria.where("project").is(project).and("flag").is("1")
                .and("send_Timestamp").gt(starttime).lt(endtime)), Msg.class);
        //总页数
        int totalpages=(int)Math.ceil(total/PAGE_SIZE);
        if(totalpages<1)
            totalpages=1;
        int skip = (currentpage-1)*PAGE_SIZE;
        List<Msg> msgs = mongoEventsTemplate.find(new Query(Criteria.where("project").is(project).and("flag").is("1")
                .and("send_Timestamp").gt(starttime).lt(endtime)).skip(skip).limit(PAGE_SIZE), Msg.class);
        Map map = new HashMap<>();
        map.put("list",msgs);
        map.put("totalpages",totalpages);
        return map;
    }


}
