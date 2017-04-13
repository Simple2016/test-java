package lqw.test.test_ticket;

/** 
 *
 * @ClassName   类名：TestTicket
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

public class TicketTest {

    public static void main(String[] args) {
        Ticket ticket = new Ticket(30);

        TicketThread t1 = new TicketThread("T1", ticket);
        TicketThread t2 = new TicketThread("T2", ticket);
        TicketThread t3 = new TicketThread("T3", ticket);

        t1.start();
        t2.start();
        t3.start();
    }
}
