package com.zjw.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private String code;
    private String msg;
    private Object data;

    public static R ok(){
        R r = new R();
        r.setCode("00");
        r.setMsg("success");
        r.setData(null);
        return r;
    }
    public static R faild(){
        R r = new R();
        r.setCode("01");
        r.setMsg("faild");
        r.setData(null);
        return r;
    }
}
