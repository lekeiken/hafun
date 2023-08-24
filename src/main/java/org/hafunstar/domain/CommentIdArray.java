package org.hafunstar.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */

public class CommentIdArray {
    private List<String> ids;
    private List<String> s_ids;

    public Integer[] getIds() {
        Integer [] a = new Integer[s_ids.size()];
        int index = 0;
        for(String id:ids){

            a[index]= Integer.parseInt(id);
            index++;
        }
        return a;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Integer[] getS_ids() {
        Integer [] a = new Integer[s_ids.size()];
        int index = 0;
        for(String id:ids){

            a[index]= Integer.parseInt(id);
            index++;
        }
        return a;
    }

    public void setS_ids(List<String> s_ids) {
        this.s_ids = s_ids;
    }
}
