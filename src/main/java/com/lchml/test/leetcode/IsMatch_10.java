package com.lchml.test.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 大致的思路是把包含*和.字符的先切分，然后分段匹配
 *
 * Created by lc on 2019/01/22.
 */
public class IsMatch_10 {

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        // 先对p分段
        if (p.contains("*") && p.contains(".")) {
            int index1 = p.indexOf('*');
            int index2 = p.indexOf('.');
            // 最复杂情况
            return false;
        } else if (p.contains("*")) {
            List<String> patterns = new ArrayList<>();
            for (String sp : p.split("\\*")) {
                patterns.add(sp.substring(0, sp.length() - 1));
                patterns.add(sp.charAt(sp.length() - 1) + "*");
            }
            List<String> sSplit = new ArrayList<>();
            // 对s进行分段
            for (int i=0; i<patterns.size(); i++) {
                if (!patterns.get(i).contains("*")) {
                    sSplit.add(s.substring(s.indexOf(patterns.get(i)), patterns.get(i).length()));
                }
            }



        } else if (p.contains(".")) {
            int index = 0;
            List<Integer> indexs = new ArrayList<>();
            while ((index = p.indexOf('.', index)) != -1) {
                indexs.add(index);
            }
            return false;
        } else {
            return s.equals(p);
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
