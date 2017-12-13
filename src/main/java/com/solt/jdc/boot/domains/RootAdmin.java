package com.solt.jdc.boot.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RootAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rootName;
    private String rootPassword;
    private boolean isRoot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RootAdmin rootAdmin = (RootAdmin) o;

        if (id != rootAdmin.id) return false;
        if (isRoot != rootAdmin.isRoot) return false;
        if (rootName != null ? !rootName.equals(rootAdmin.rootName) : rootAdmin.rootName != null) return false;
        return rootPassword != null ? rootPassword.equals(rootAdmin.rootPassword) : rootAdmin.rootPassword == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rootName != null ? rootName.hashCode() : 0);
        result = 31 * result + (rootPassword != null ? rootPassword.hashCode() : 0);
        result = 31 * result + (isRoot ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RootAdmin{" +
                "id=" + id +
                ", rootName='" + rootName + '\'' +
                ", rootPassword='" + rootPassword + '\'' +
                ", isRoot=" + isRoot +
                '}';
    }
}
