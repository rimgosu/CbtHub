package rimgosu.cbthub.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Date;
import java.util.List;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInfo {

    private String testDescription;
    private String testingOrganization;
    private String testOrgUrl;

    @ElementCollection
    private List<Date> roundDate;

}
