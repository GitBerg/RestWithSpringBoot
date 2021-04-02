package br.com.erudio.restwithspringbootudemy.data.vo.v2;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

public class PersonVOV2 implements Serializable {

    private static final long serialVersionUID = 1L;


    private long id;


    private String firstName;

    private String lastName;

    private String andress;

    private String gender;

    private Date birthday;

    public PersonVOV2() {

    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

        PersonVOV2 that = (PersonVOV2) o;

        return new EqualsBuilder().append(id, that.id).append(firstName, that.firstName).append(lastName, that.lastName).append(andress, that.andress).append(gender, that.gender).append(birthday, that.birthday).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(firstName).append(lastName).append(andress).append(gender).append(birthday).toHashCode();
    }
}
