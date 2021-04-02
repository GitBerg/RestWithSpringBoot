package br.com.erudio.restwithspringbootudemy.data.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private long id;


    private String firstName;

    private String lastName;

    private String andress;

    private String gender;

    public PersonVO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAndress() {
        return andress;
    }

    public void setAndress(String andress) {
        this.andress = andress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PersonVO personVO = (PersonVO) o;

        return new EqualsBuilder().append(id, personVO.id).append(firstName, personVO.firstName).append(lastName, personVO.lastName).append(andress, personVO.andress).append(gender, personVO.gender).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(firstName).append(lastName).append(andress).append(gender).toHashCode();
    }
}


