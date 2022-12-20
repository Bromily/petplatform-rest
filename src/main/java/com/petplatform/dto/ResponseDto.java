package com.petplatform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDto {


    private long elapsedTime;

    public void setElapsedTime(long startTime){
        long endTime = System.currentTimeMillis();
        this.elapsedTime = endTime - startTime;
    }

    private String status;

    private List errors = new ArrayList();

    private String message;

    private String timestamp;


    private String bodyType;


    private Object body;

    public ResponseDto(Object request, Object e){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = sdf.format(new Date());

        if(request instanceof List<?>){
            if(((List<?>) request).size() < 1){
                throw new IndexOutOfBoundsException();
            }else if(((List<?>) request).get(0) == null){
                throw new NullPointerException();
            }else {
                this.setBodyType("ARRAY");
                this.setBody(request);
            }
        }else {
            this.setBodyType("OBJECT");
            this.setBody(request);
        }

        if(e instanceof Exception){
            this.setStatus("Not OK");
            if(e instanceof IndexOutOfBoundsException){
                this.errors.add("No data found");
            }else if(e instanceof NullPointerException){
                this.errors.add("No data found");
            }else {
                this.errors.add("Errors");
            }
            StringWriter sw = new StringWriter();
            ((Exception) e).printStackTrace(new PrintWriter(sw));
            this.setMessage(sw.toString().substring(0, 50));
        }else {
            this.setStatus("OK");
        }
    }

}