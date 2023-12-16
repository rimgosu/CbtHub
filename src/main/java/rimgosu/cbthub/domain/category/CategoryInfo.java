package rimgosu.cbthub.domain.category;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryInfo {

    private String testDescription;
    private String testingOrganization;
    private String testOrgUrl;

}
