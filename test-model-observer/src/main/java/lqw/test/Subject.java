package lqw.test;

public interface Subject {
    public void addObservers(Observer observers);//添加观察者
  
    public void deleteObservers(Observer observers);//删除观察者
  
    public void notifyAllObservers(String msg);//通知所有的观察者  
}  