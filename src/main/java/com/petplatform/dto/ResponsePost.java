package com.petplatform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponsePost {

    private String returnCd;
    private String returnMsg;

    public ResponsePost(Object obj){
        if(obj == null) {
            this.returnCd = "0001";
            this.returnMsg = "성공";
        }else if(obj instanceof Exception){
            this.returnCd = "9999";
            this.returnMsg = ((Exception) obj).getMessage();
        }else {
            this.returnCd = "0000";
            this.returnMsg = (String) obj;
        }
    }

}
