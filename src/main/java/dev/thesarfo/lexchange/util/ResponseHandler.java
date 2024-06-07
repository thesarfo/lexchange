package dev.thesarfo.lexchange.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHandler {
    private HttpStatus status;
    private Object data;

    public static ResponseEntity<Object> successResponse(String message, Object data,HttpStatus status) {
        return response("success",status,data);
    }

    public static ResponseEntity<Object> errorResponse(Object data, HttpStatus status){
        return response("error", status, data);
    }

    public static ResponseEntity<Object> response(String statusMessage,HttpStatus httpStatus,Object data) {
        Map<String,Object> response = new HashMap<>();
        response.put("status",statusMessage);
        response.put("content",data);
        return new ResponseEntity<>(response,httpStatus);
    }
}
