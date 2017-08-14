package lqw.test.observer;

import lqw.test.Observer;

public class ObserverImpl implements Observer {
    private String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void updateMsg(String msg) {
        if ("我不开心".equals(msg)) {
            System.out.println(name + "说： 多喝热水");
        }
    }

}  