package lqw.test.subject;

import lqw.test.Observer;
import lqw.test.Subject;

import java.util.ArrayList;
import java.util.List;

public class Angelababy implements Subject {
  
    // 观察者集合  
    private List<Observer> mList = new ArrayList<>();// 注意这里集合的泛型用的是接口类型
  
    public void addObservers(Observer observers) {
        mList.add(observers);  
    }  
  
    @Override  
    public void deleteObservers(Observer observers) {
        mList.remove(observers);  
    }  
  
    @Override  
    public void notifyAllObservers(String msg) {  
        for (Observer list : mList) {
            list.updateMsg(msg);
        }  
    }  
  
}  