package lqw.test.test_dubbo;

public interface DemoService {

    public void sayHello();

    public String returnHello();

    public MsgInfo returnMsgInfo(MsgInfo info);

}