package lqw.test.cache.eh_cache.domain;

import lombok.*;

import java.io.Serializable;

/**
 * Created by liqw on 2017/10/19.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -1318068408295471682L;
    private String id;
    private String name;
}
