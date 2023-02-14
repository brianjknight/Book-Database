package com.bookdatatbase.bdapi.models;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Role {
    private String authorId;
    private String role;

    public Role(String authorId, String role) {
        this.authorId = authorId;
        this.role = role;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(authorId, role1.authorId) && Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, role);
    }

    @Override
    public String toString() {
        return "Role{" +
                "authorId='" + authorId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
