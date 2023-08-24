package org.hafunstar.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */
@Component
public class LoginVo implements Serializable {

    private String username;

    private String password;

    private List<Captcha> Points;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Captcha> getPoints() {
        return Points;
    }

    public void setPoints(List<Captcha> points) {
        Points = points;
    }

}
