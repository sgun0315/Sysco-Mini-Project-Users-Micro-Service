package sysco.mini.project.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @Getter @Setter
    private int status;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private long timeStamp;

}
