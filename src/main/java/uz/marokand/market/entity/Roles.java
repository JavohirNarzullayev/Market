package uz.marokand.market.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles implements Serializable {
    @Id
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles(String name) {
        this.name = name;
    }

    public Roles(Set<Roles> roles) {
    }

    public Roles() {
        super();
    }
}
