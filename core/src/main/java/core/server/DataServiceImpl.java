package core.server;

import com.google.common.collect.Lists;
import interfaces.DataService;

import java.util.List;

public class DataServiceImpl implements DataService {

    @Override
    public String sendData(String body) {
        System.out.println("己收到的参数长度：" + body.length());
        return "success";
    }

    @Override
    public List<String> getList() {
        return Lists.newArrayList("idea1", "idea2", "idea3");
    }
}