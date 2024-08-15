import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequestDto {
    private Long id;
    private String task;
    private String managePerson;
    private String pw;
}