package br.com.erudio.restwithspringbootudemy.services;

import br.com.erudio.restwithspringbootudemy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userServices implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public userServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = repository.findByUsername(userName);
        if(user!= null){
            return user;
        }else{
            throw new UsernameNotFoundException("Username " + "not found");
        }
    }
}
