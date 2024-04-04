package com.lina.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;
    public static Result success(){// insert delete alter response;
        return new Result(1,"success",null);
    }
    public static Result success(Object data){// search response;
        return new Result(1,"success",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
