package br.com.erudio.restwithspringbootudemy.converter.mocks;

import br.com.erudio.restwithspringbootudemy.data.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public br.com.erudio.restwithspringbootudemy.data.vo.PersonVO mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> personVOS = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            personVOS.add(mockEntity(i));
        }
        return personVOS;
    }

    public List<br.com.erudio.restwithspringbootudemy.data.vo.PersonVO> mockVOList() {
        List<br.com.erudio.restwithspringbootudemy.data.vo.PersonVO> personVOS = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            personVOS.add(mockVO(i));
        }
        return personVOS;
    }

    private Person mockEntity(Integer number) {
        Person personVO = new Person();
        personVO.setAndress("Addres Test" + number);
        personVO.setFirstName("First Name Test" + number);
        personVO.setGender(((number % 2)==0) ? "Male" : "Female");
        personVO.setId(number.longValue());
        personVO.setLastName("Last Name Test" + number);
        return personVO;
    }

    private br.com.erudio.restwithspringbootudemy.data.vo.PersonVO mockVO(Integer number) {
        br.com.erudio.restwithspringbootudemy.data.vo.PersonVO personVO = new br.com.erudio.restwithspringbootudemy.data.vo.PersonVO();
        personVO.setAndress("Addres Test" + number);
        personVO.setFirstName("First Name Test" + number);
        personVO.setGender(((number % 2)==0) ? "Male" : "Female");
        personVO.setId(number.longValue());
        personVO.setLastName("Last Name Test" + number);
        return personVO;
    }
}
