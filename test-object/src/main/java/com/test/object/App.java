package com.test.object;

/**
 * 
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("lqwtest");
        byte[] objBytes = ObjectHelper.getBytesFromObject(user);
        User user2 = (User) ObjectHelper.getObjectFromBytes(objBytes);

        System.err.println(user2.getId());
        System.err.println(user2.getName());
    }
}
