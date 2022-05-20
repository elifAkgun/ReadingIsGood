package code.elif.readingIsGood.customer.ui.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ApiError {

    private long timeStamp = new Date().getTime();

    private int statusCode;

    private String message;

    private String url;

    private Map<String,String> validationErrors;

    public ApiError(int statusCode, String message, String url) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.url = url;
    }



}
