package org.hafunstar.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */
public class JwtUtil {

    //自定义密钥(HMAC256加密)
    private static final String SING = "@W3#e4rCa%Tb!YoQ*4Nki@6dw";
    public static final int calendarField = Calendar.DATE;
    private int time = 10;//签名有效期

    public JwtUtil() {

    }

    public JwtUtil(int time) {
        this.time = time;
    }


    /**
     * 获取JWT
     * @param name
     * @return
     */
    public String createJWT(String name,Integer userId) throws Exception {
        if (name != null){
            Date iatDate = new Date();
            // expire time
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(calendarField, time);
            Date expiresDate = nowTime.getTime();
            Map<String, Object> map = new HashMap<>();
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            //拿到HS256签名算法实例
            String token = JWT.create().withHeader(map) // header
                    .withIssuer("single_K")
                    .withClaim("username",name)
                    .withClaim("user_id", userId)
                    .withIssuedAt(iatDate) // sign time
                    .withExpiresAt(expiresDate) // expire time
                    .sign(Algorithm.HMAC256(SING)); // signature

            return token;
        }else {
            throw new Exception("用户名为空");
        }


    }
    public String createJWT(String name) throws Exception {
        if (name != null){
            Date iatDate = new Date();
            // expire time
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(calendarField, time);
            Date expiresDate = nowTime.getTime();
            Map<String, Object> map = new HashMap<>();
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            //拿到HS256签名算法实例
            String token = JWT.create().withHeader(map) // header
                    .withIssuer("single_K")
                    .withClaim("username",name)
                    .withIssuedAt(iatDate) // sign time
                    .withExpiresAt(expiresDate)
                    .sign(Algorithm.HMAC256(SING)); // signature

            return token;
        }else {
            throw new Exception("用户名为空");
        }


    }

    /**
     * 验证JWT
     * @param token
     * @return
     */
    public boolean verifyToken(String token){
        if (token == null){
            return false;
        }
        DecodedJWT jwt = null;
        try {
            jwt = verify(token);
        }catch (Exception e){
            //无效的签名/声明
            e.printStackTrace();
            return false;
        }
            return timeCompare(jwt);

    }

    /**
     * 解码后获得指定插入的数据
     * @param token
     * @param claim
     * @return
     */
    public DecodedJWT verify(String token){

         JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SING)).build();

        return verifier.verify(token); //解码JWT ，verifier 可复用

    }

    /**
     * 解析cookie获得token
     * @param cookies
     * @return
     */
    public String tokenInCookie(Cookie[] cookies){
        String token = "";
        if(cookies != null && cookies.length>0 ) {
            for(Cookie cookie : cookies) {
                if("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    /**
     *token的过期时间与现在时间相比是否在之前，是返回ture
     * @param token
     * @param i
     * @return
     */
    public boolean timeCompare(DecodedJWT jwt){
        Date date = new Date();
        return date.before(jwt.getExpiresAt());
    }
}
