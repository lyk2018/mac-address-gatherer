package tr.org.linux.kamp.macaddressgatherer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "APP_USERS")
@ToString
class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;
    private String macAddress;
    private String ipAddress;

}
