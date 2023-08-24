package org.hafunstar.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */

public class RandomUtil {
    public static String sixNum() {

        String num = "";
        for(int i=0;i<6;i++){
            Integer radomInt = new Random().nextInt(10);

            num = num+radomInt.toString();
        }
        return num;
    }
}
