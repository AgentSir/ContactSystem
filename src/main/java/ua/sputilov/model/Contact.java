package ua.sputilov.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (null == o) return false;
        if (!(o instanceof Contact)) return false;
        if (this == o) return true;

        Contact input = (Contact) o;
        return new EqualsBuilder().append(this.getId(), input.getId()).isEquals();

    }

    @Override
    public int hashCode() {
        if (getId() != null && getId() > 0) {
            return new HashCodeBuilder(11, 37).append(getId()).toHashCode();
        }
        else {
            return super.hashCode();
        }
    }

    @Override
    public String toString() {
        return "{\"id\":" + this.getId() + ",\"name\":\"" + this.getName() + "\"}";
    }
}
