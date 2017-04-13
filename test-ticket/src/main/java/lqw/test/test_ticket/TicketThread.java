package lqw.test.test_ticket;

/** 
 *
 * @ClassName   类名：TicketThread
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年11月14日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年11月14日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class TicketThread extends Thread {

    private final String name;

    private final Ticket ticket;

    private final int count;

    public TicketThread(String name, Ticket ticket) {
        this.name = name;
        this.ticket = ticket;
        count = ticket.number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            try {
                synchronized (ticket) {
                    if (ticket.number > 0) {
                        ticket.number--;
                        Thread.sleep(100);
                        System.out.println(name + " 卖了 " + i + " 张票" + ", 余票：" + ticket.number);
                    } else {
                        System.out.println(name + " 没有票了");
                        break;
                    }

                }
                Thread.sleep(500);// 增加卖票输出到下一次卖票过程的间隔时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
