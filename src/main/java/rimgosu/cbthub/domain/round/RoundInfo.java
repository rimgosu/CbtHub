package rimgosu.cbthub.domain.round;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoundInfo {

    private int applicantsNumber;
    private int testTakersNumber;
    private int passersNumber;
    private Date testDate;

}
