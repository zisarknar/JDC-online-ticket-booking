package com.solt.jdc.boot.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bus_type")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusType {

    @Id
    private Integer id;

    @NotBlank(message = "Please enter bus type")
    private String type;
    @JsonIgnore
    @OneToMany(mappedBy = "busType")
    private List<Bus> busList = new ArrayList<>();


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((busList == null) ? 0 : busList.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BusType other = (BusType) obj;
        if (busList == null) {
            if (other.busList != null)
                return false;
        } else if (!busList.equals(other.busList))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BusType [id=" + id + ", type=" + type + ", busList=" + busList + "]";
    }

}
